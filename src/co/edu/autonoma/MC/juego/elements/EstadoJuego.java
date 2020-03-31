/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.autonoma.MC.juego.elements;

import co.edu.autonoma.MC.juego.bases.GraphicContainer;
import java.awt.Color;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.java2d.cmm.ColorTransform;

/**
 *
 * @author nikof
 */
public class EstadoJuego {
    private String nombreJugador;
    private Jugador jugadorLocal;
    private String mensajeChat;
    
    private ArrayList<Jugador> rivales;
    private boolean jugadorCreado;
    
    public EstadoJuego() {
        rivales = new ArrayList<>();
        jugadorCreado=false;
    }
    
    public void pelear(String nombreJugador, String nombreRival){
        
        ArrayList<Jugador> jugadoresPeleando = new ArrayList<>();
        boolean localPelea = false;
        
        if(jugadorLocal.getUsername().equals(nombreJugador) ||
                jugadorLocal.getUsername().equals(nombreRival)){
            this.jugadorLocal.setPeleando(true);
            jugadoresPeleando.add(jugadorLocal);
            localPelea = true;
        }
        
        for (int i = 0; i < rivales.size(); i++) {
            Jugador rival = rivales.get(i);
            
            if(rival.getUsername().equals(nombreRival) || 
                    rival.getUsername().equals(nombreJugador)){
                rival.setPeleando(true);
                jugadoresPeleando.add(rival);
                
                if(jugadoresPeleando.size()==2){
                    break;
                }
            }
        }
        
        if(localPelea){
            jugadorLocal.iniciarPelea();
        }
    }
    
    public boolean verificarRival(String nombreRival){
        for (int i = 0; i < rivales.size(); i++) {
            Jugador rival = rivales.get(i);
            
            if(rival.getUsername().equals(nombreRival)){
                return true;
            }
        }
        
        return false;
    }
    
    public void aumentarVidaJugador(String nombreJugador){
        
        if(jugadorLocal.getUsername().equals(nombreJugador)){
            jugadorLocal.grow();
            return;
        }
        
        for (int i = 0; i < rivales.size(); i++) {
            Jugador rival = rivales.get(i);
            
            if(rival.getUsername().equals(nombreJugador)){
                rival.grow();
                break;
            }
        }
    }
    
    public void disminuirVidaJugador(String nombreJugador){
        
        if(jugadorLocal.getUsername().equals(nombreJugador)){
            jugadorLocal.shrink();
            return;
        }
        
        for (int i = 0; i < rivales.size(); i++) {
            Jugador rival = rivales.get(i);
            
            if(rival.getUsername().equals(nombreJugador)){
                rival.shrink();
                break;
            }
        }
    }
    
    public void addRival(int x, int y, int size, Color color, String nombreRival, boolean peleando){
        Jugador nuevoRival = new Jugador(x, y, size, color, nombreRival);
        
        nuevoRival.setPeleando(peleando);
        
        rivales.add(nuevoRival);
        
        GraphicContainer mundo = jugadorLocal.getGraphicContainer();
        if(mundo==null) System.out.println("Mundo es null");
        mundo.addSprite(nuevoRival);
        nuevoRival.setGraphicContainer(mundo);
    }
    
    public void removeRival(String nombreRival){
        
        for (int i = 0; i < rivales.size(); i++) {
            Jugador rival = rivales.get(i);
            
            if(rival.getUsername().equals(nombreRival)){
                rivales.remove(i);
                break;
            }
        }
        
    }
    
    public ArrayList<Jugador> getRivales(){
        
        return rivales;
    }
    
    public void actualizarRival(int x, int y, int size, String nombreRival, boolean peleando){
        Jugador rivalBorrar=null;
        
        for(Jugador rival : rivales) {
            if(rival.getUsername().equals(nombreRival)){

                rival.updatePlayer(x, y, size, peleando);
                if(size<5){
                    rivalBorrar = rival;
                    System.out.println("ESTADOJUEGO => se eliminara un rival");
                    GraphicContainer mundo = jugadorLocal.getGraphicContainer();
                    mundo.removeSprite(rival);
                }
            }
        }
        
        rivales.remove(rivalBorrar);
        System.out.println("ESTADOJUEGO => quedan " + rivales.size() + "rivales");
    }
    
    public synchronized void crearJugadorLocal(int x, int y, Color color, String username){
        
        System.out.println("ESTADOJUEGO => Creando el jugador local: " + username);
        
        this.jugadorLocal = new Jugador(x, y, color, username);
        jugadorCreado=true;
        notify();
    }
    
    public synchronized void crearJugadorLocal(){
        System.out.println("ESTADOJUEGO=> Creando jugador null");
        this.jugadorLocal = null;
        
        notify();
    }
    
    public synchronized Jugador getJugadorLocal(){
        
        if(jugadorLocal==null){
            try {
                wait();
            } catch (InterruptedException ex) {
                System.out.println("ESTADO JUEGO => interrupcion obteniendo el jugador: " + ex.getMessage());
            }
        }
        
        return this.jugadorLocal;
    }
    
    public synchronized void setChat(String chat){
        this.mensajeChat = chat;
        
        notifyAll();
    }
    
    public synchronized String getChat(){
        try {
            wait();
        } catch (InterruptedException ex) {
            System.out.println("ESTADO JUEGO => interrupcion obteniendo el mensaje de chat: " + ex.getMessage());
        }
        
        return mensajeChat;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public int getXJugador() {
        return this.jugadorLocal.getX();
    }

    public int getYJugador() {
        return this.jugadorLocal.getY();
    }
    
    public int getSizeJugador(){
        return this.jugadorLocal.getHeight();
    }
    
    public boolean isPeleandoJugador(){
        return this.jugadorLocal.isPeleando();
    }
    
    public boolean isJugadorCreado(){
        return this.jugadorCreado;
    }
        
}
