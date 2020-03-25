/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.autonoma.MC.cliente.elements;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author nikof
 */
public class Cliente {
    
    private String nombre;
    private String ipServidor;
    
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    
    private RedEntrada redEntrada;
    private RedSalida redSalida;
    
    private EscritorMensajes escritor;
    
    public Jugador(){
        this.ipServidor=null;
        this.nombre = null;
        
        this.redEntrada = new RedEntrada();
        this.redSalida = new RedSalida();
        this.escritor = new EscritorMensajes();
    }
    
    /**
     * Realiza la conexión al servidor, segun la ip de ipServidor del jugador
     * 
     * @return true si la conexión fue exitosa, false de lo contrario
     */
    public boolean conectarAServidor(){
        
        int serverPort = 9090;
        
        if(ipServidor.isEmpty()){
            return false;
        }

        try{
            System.out.println("JUGADOR=> Conectando al servidor");

            socket = new Socket(this.ipServidor, serverPort);

            System.out.println("JUGADOR=> Extracción de flujo I/O");

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            
            //SE HACE LA RED DE SALIDA
            redSalida.setOut(out);
            
        }catch(UnknownHostException ex){
            System.out.println("JUGADOR=> Error conectando al servidor " + ex.getMessage());
            return false;
        } catch (IOException ex) {
            System.out.println("JUGADOR=> Error de IO" + ex.getMessage());
            return false;
        }
        
        return true;
    }
    
    /**
     * Se inicializa la red para recibir mensajes del juego
     * 
     * @param juego, el cual cambiará conforme pase la partida
     */
    public void iniciarRedEntrada(InterfazJuego juego){
        
        System.out.println("JUGADOR=> Inicializando red de entrada");
        this.redEntrada.setNombreJugador(this.nombre);
        this.redEntrada.setIn(this.in);
        this.redEntrada.setJuego(juego);
        
        System.out.println("JUGADOR=> Iniciando hilo de red de entrada");
        this.redEntrada.start();
    }
    
    public Socket getSocket() {
        return socket;
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
        this.escritor.setNombreJugador(nombre);
    }

    public String getIpServidor() {
        return ipServidor;
    }

    public void setIpServidor(String ipServidor) {
        this.ipServidor = ipServidor;
    }

    /**
     * Juega piedra en la sesion de juego
     */
    public void jugarPiedra() {
        String mensajeEnviar = this.escritor.escribirMensaje(InterfazJuego.PIEDRA);
        
        this.redSalida.enviarMensaje(mensajeEnviar);
    }

    /**
     * Juega papel en la sesion de juego
     */
    public void jugarPapel() {
        String mensajeEnviar = this.escritor.escribirMensaje(InterfazJuego.PAPEL);
        
        this.redSalida.enviarMensaje(mensajeEnviar);    
    }

    /**
     * Juega tijeras en la sesion de juego
     */
    public void jugarTijeras() {
        String mensajeEnviar = this.escritor.escribirMensaje(InterfazJuego.TIJERA);
        
        this.redSalida.enviarMensaje(mensajeEnviar);
    }

    /**
     * Envia al servidor mensaje de que se empieza una nueva partida
     */
    public void enviarMensajeNuevaPartida() {
        String mensajeEnviar = this.escritor.escribirMensaje(InterfazJuego.NUEVA_PARTIDA);
        
        this.redSalida.enviarMensaje(mensajeEnviar);
    }

    /**
     * Envia al servidor mensaje de que se termina la partida y la sesion
     */
    public void enviarMensajeTerminarPartida() {
        String mensajeEnviar = this.escritor.escribirMensaje(InterfazJuego.TERMINAR);
        
        this.redSalida.enviarMensaje(mensajeEnviar);
    }
}
