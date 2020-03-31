/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.autonoma.MC.cliente.elements;

import co.edu.autonoma.MC.cliente.networks.RedEntradaMC;
import co.edu.autonoma.MC.cliente.networks.RedEntradaTCP;
import co.edu.autonoma.MC.cliente.networks.RedSalidaMC;
import co.edu.autonoma.MC.cliente.networks.RedSalidaTCP;
import co.edu.autonoma.MC.juego.bases.PPTGame;
import co.edu.autonoma.MC.juego.elements.EstadoJuego;
import co.edu.autonoma.MC.juego.elements.Jugador;
import co.edu.autonoma.MC.juego.elements.PPT;
import java.awt.Color;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nikof
 */
public class Cliente {
    
    private String nombre;
    
    //Para la conexion con el servidor
    private String ipServidor;
    private int puertoServidor;
    private Socket socketTCP;
    private DataInputStream in;
    private DataOutputStream out;
    private RedEntradaTCP redEntradaTCP;
    private RedSalidaTCP redSalidaTCP;
    private EscritorMensajesTCP escritorTCP;
    
    private PPT ppt;
    
    //Para la conexion con el grupo
    private String ipGrupoMC;
    private int puertoGrupoMC;
    private InetAddress grupo;
    private MulticastSocket socketMC;
    private RedEntradaMC redEntradaMC;
    private RedSalidaMC redSalidaMC;
    private EscritorMensajesMC escritorMC;
    private Jugador jugadorLocal;
    private EstadoJuego estadoJuego;
    
    
    public Cliente(){
        this.nombre = null;
        
        this.ipServidor=null;
        this.puertoServidor=0;
        this.ipGrupoMC=null;
        this.puertoGrupoMC=0;
        
        
        this.redEntradaTCP = new RedEntradaTCP();
        this.redSalidaTCP = new RedSalidaTCP();
        this.escritorTCP = new EscritorMensajesTCP();
        
        
        this.redEntradaMC = new RedEntradaMC();
        this.redSalidaMC = new RedSalidaMC();
        this.escritorMC = new EscritorMensajesMC();
        
        this.estadoJuego = new EstadoJuego();
        
        this.ppt = new PPT();
    }
    
    ///////////////////////////////////////////////////////////////////////
    //CONEXIONES///////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////
        
    /**
     * Realiza la conexión al servidor, segun la ip de ipServidor del jugador
     * 
     * @return true si la conexión fue exitosa, false de lo contrario
     */
    public boolean conectar(){
        
        this.puertoServidor = 9090;
        
        this.puertoGrupoMC = 4446;
        
        this.ipGrupoMC = "230.0.0.1";
        
        this.ipServidor = "127.0.0.1";
        
        if(ipServidor.isEmpty()){
            return false;
        }
        
        try {
            System.out.println("CLIENTE=> Multicast: Conectando al grupo");
            socketMC = new MulticastSocket(this.puertoGrupoMC);
            this.grupo = InetAddress.getByName(this.ipGrupoMC);
            socketMC.joinGroup(this.grupo);
            
            redSalidaMC.setSalida(socketMC, this.grupo, puertoGrupoMC);
        } catch(UnknownHostException ex){
            System.out.println("CLIENTE=> Multicast: Error conectando al grupo " + ex.getMessage());
            return false;
        } catch (IOException ex) {
            System.out.println("CLIENTE=> Multicast: Error de IO" + ex.getMessage());
            return false;
        }
        
        return true;
    }
    
    public boolean conectarAServidor(){
        try{
            System.out.println("CLIENTE=> TCP: Conectando al servidor");

            socketTCP = new Socket(this.ipServidor, this.puertoServidor);

            System.out.println("CLIENTE=> TCP: Extracción de flujo I/O");
            in = new DataInputStream(socketTCP.getInputStream());
            out = new DataOutputStream(socketTCP.getOutputStream());
            
            //Se asigna el flujo de salida a la red de salida, para enviar mensajes
            redSalidaTCP.setOut(out);
        }catch(UnknownHostException ex){
            System.out.println("CLIENTE=> TCP: Error conectando al servidor " + ex.getMessage());
            return false;
        } catch (IOException ex) {
            System.out.println("CLIENTE=> TCP: Error de IO " + ex.getMessage());
            return false;
        }
        return true;
    }
    
    public void cerrarConexiones(){        
        try {
            socketMC.leaveGroup(grupo);
        } catch (IOException ex) {
            System.out.println("CLIENTE => Error saliendo del grupo");
        }
        this.estadoJuego.setChat("cerrar");
        socketMC.close();
    }
    
    /**
     * Se inicializa la red para recibir mensajes del juego
     * 
     */
    public void iniciarEntradaMulticast(){
        
        System.out.println("JUGADOR=> Multicast: Inicializando red de entrada");
        //this.redEntradaMC.setNombreJugador(this.nombre);
        this.redEntradaMC.setSocketMC(this.socketMC);
        //this.redEntradaMC.setJuegoMC(juegoMC);
        this.redEntradaMC.setEstadoJuego(this.estadoJuego);
        
        System.out.println("JUGADOR=> Multicast: Iniciando hilo de red de entrada");
        this.redEntradaMC.start();
    }
    
    /**
     * Se inicializa la red para recibir mensajes del juego
     * 
     * @param juegoPPT, el cual cambiará conforme pase la partida
     */
    public void iniciarEntradaTCP(PPTGame juegoPPT){
        this.redEntradaTCP = new RedEntradaTCP();
        
        System.out.println("JUGADOR=> TCP: Inicializando red de entrada");
        this.redEntradaTCP.setNombreJugador(this.nombre);
        this.redEntradaTCP.setJugadorLocal(this.jugadorLocal);
        this.redEntradaTCP.setIn(this.in);
        this.redEntradaTCP.setJuego(juegoPPT);
        this.redEntradaTCP.setCliente(this);
        
        System.out.println("JUGADOR=> TCP: Iniciando hilo de red de entrada");
        this.redEntradaTCP.start();
    }
    
    ///////////////////////////////////////////////////////////////////////
    //GETTERS Y SETTERS///////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////

    public EstadoJuego getEstadoJuego() {
        return estadoJuego;
    }
    
    public Socket getSocket() {
        return socketTCP;
    }

    public DataInputStream getIn() {
        return in;
    }

    public DataOutputStream getOut() {
        return out;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
        this.escritorMC.setNombreJugador(nombre);
        this.escritorTCP.setNombreJugador(nombre);
        this.estadoJuego.setNombreJugador(nombre);
    }

    public String getIpServidor() {
        return ipServidor;
    }

    public void setIpServidor(String ipServidor) {
        this.ipServidor = ipServidor;
    }

    public int getPuertoServidor() {
        return puertoServidor;
    }

    public void setPuertoServidor(int puertoServidor) {
        this.puertoServidor = puertoServidor;
    }

    public String getIpGrupoMC() {
        return ipGrupoMC;
    }

    public void setIpGrupoMC(String ipGrupoMC) {
        this.ipGrupoMC = ipGrupoMC;
    }

    public int getPuertoGrupoMC() {
        return puertoGrupoMC;
    }

    public void setPuertoGrupoMC(int puertoGrupoMC) {
        this.puertoGrupoMC = puertoGrupoMC;
    }
    
    public Jugador getJugadorLocal(){
        return this.jugadorLocal;
    }
    
    ///////////////////////////////////////////////////////////////////////
    //ACCIONES DEL WORLD///////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////
    
    public Jugador crearJugador(){
        System.out.println("CLIENTE => Creando jugador local");
        
        System.out.println("CLIENTE => Autenticando nombre del jugador");        
//        String mensajeNombre = "NuevoJugador::"+this.nombre;
//        try {
//            DatagramPacket dp = new DatagramPacket(mensajeNombre.getBytes(), mensajeNombre.length(),this.grupo, this.puertoGrupoMC);
//            this.socketMC.send(dp);
//        } catch (IOException ex) {
//            System.out.println("CLIENTE => Error enviando el nombre del jugador: " + ex.getMessage());
//            return null;
//        }

        this.enviarMensajeNuevoJugador();
        
        
        System.out.println("CLIENTE => Recibiendo posicion inicial del jugador local");
//        String mensajePos;
//        try {
//            //recibir un mensaje del servidor con la posicion y el color
//            
//            ////mensajePos = in.readUTF(); //"200::x::y::color"
//            
//            byte[] buffer = new byte[1000];
//            DatagramPacket recv = new DatagramPacket(buffer, buffer.length);
//            socketMC.receive(recv);
//
//            mensajePos = new String(recv.getData(),0,recv.getLength());
//        } catch (IOException ex) {
//            System.out.println("CLIENTE => Error recibiendo la posicion del jugador: " + ex.getMessage());
//            return null;
//        }
//        String[] posiciones = mensajePos.split("::");
//        
//        if(posiciones[0].equals("200")){
//            x = Integer.parseInt(posiciones[1]);
//            y = Integer.parseInt(posiciones[2]);
//            color = new Color(Integer.parseInt(posiciones[3]));
//        }else{
//            if(posiciones[0].equals("501")){
//                System.out.println("CLIENTE => No se puede crear el jugador, no hay espacio suficiente en el mundo");
//            }
//            if(posiciones[0].equals("502")){
//                System.out.println("CLIENTE => No se puede crear el jugador, nombre de jugador existente");
//            }
//            return null;
//        }

        this.jugadorLocal = this.estadoJuego.getJugadorLocal();
        
        return this.jugadorLocal;
//        
//        x = estadoJuego.getXJugador();
//        y = estadoJuego.getYJugador();
//        color = estadoJuego.getColorJugador();
//        
//        this.jugadorLocal = new Jugador(x, y, color);
//        return this.jugadorLocal;
    }
    
    public void enviarMensajeGameOver(){
        String mensajeEnviar = this.escritorMC.escribirMensajeActualizacion(0,0,0,false);
        this.redSalidaMC.enviarMensaje(mensajeEnviar);
    }
    
    public boolean enviarMensajeActualizarJugador(){
        String mensajeEnviar;
        int nx;
        int ny;
        int size;
        boolean peleando;
    
        nx = this.estadoJuego.getXJugador();
        ny = this.estadoJuego.getYJugador();
        size = this.estadoJuego.getSizeJugador();
        peleando = this.estadoJuego.isPeleandoJugador();
        
        mensajeEnviar = this.escritorMC.escribirMensajeActualizacion(nx,ny,size,peleando);
        this.redSalidaMC.enviarMensaje(mensajeEnviar);
        
        return size >= 5;
    }
    
    public void enviarMensajeNuevoJugador(){
        String mensajeEnviar = this.escritorMC.escribirMensajeNuevoJugador(this.nombre);
        this.redSalidaMC.enviarMensaje(mensajeEnviar);
    }
    
    public void enviarMensajePelea(String nombreRival){
        String mensajeEnviar = this.escritorMC.escribirMensajePelea(nombreRival);
        this.redSalidaMC.enviarMensaje(mensajeEnviar);
    }
    
    public void enviarMensajeChat(String mensajeChat){
        int size = this.estadoJuego.getSizeJugador();
        String mensajeEnviar = this.escritorMC.escribirMensajeChat(mensajeChat, size);
        this.redSalidaMC.enviarMensaje(mensajeEnviar);
    }
    
    public void setPeleando(boolean peleando){
        this.jugadorLocal.setPeleando(peleando);
        this.enviarMensajeActualizarJugador();
    }
    
    ///////////////////////////////////////////////////////////////////////
    //ACCIONES DEL PPT///////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////

    public void empezarPPT(){
        ppt.setCliente(this);
        ppt.start();
    }
    
    /**
     * Juega piedra en la sesion de juego
     */
    public void jugarPiedra() {
        String mensajeEnviar = this.escritorTCP.escribirMensaje(PPTGame.PIEDRA);
        
        this.redSalidaTCP.enviarMensaje(mensajeEnviar);
    }

    /**
     * Juega papel en la sesion de juego
     */
    public void jugarPapel() {
        String mensajeEnviar = this.escritorTCP.escribirMensaje(PPTGame.PAPEL);
        
        this.redSalidaTCP.enviarMensaje(mensajeEnviar);    
    }

    /**
     * Juega tijeras en la sesion de juego
     */
    public void jugarTijeras() {
        String mensajeEnviar = this.escritorTCP.escribirMensaje(PPTGame.TIJERA);
        
        this.redSalidaTCP.enviarMensaje(mensajeEnviar);
    }

    /**
     * Envia al servidor mensaje de que se empieza una nueva partida
     */
    public void enviarMensajeNuevaPartida() {
        String mensajeEnviar = this.escritorTCP.escribirMensaje(PPTGame.NUEVA_PARTIDA);
        
        this.redSalidaTCP.enviarMensaje(mensajeEnviar);
    }

    /**
     * Envia al servidor mensaje de que se termina la partida y la sesion
     */
    public void enviarMensajeTerminarPartida() {
        String mensajeEnviar = this.escritorTCP.escribirMensaje(PPTGame.TERMINAR);
        
        this.redSalidaTCP.enviarMensaje(mensajeEnviar);
    }
}
