/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.autonoma.MC.cliente.networks;

import co.edu.autonoma.MC.cliente.elements.InterpreteMensajesMC;
import co.edu.autonoma.MC.juego.elements.EstadoJuego;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;

/**
 * Realiza y gesitona la recepcion de mensajes
 *
 * @author Nicolas Forero
 * @author Leandra Builes
 */
public class RedEntradaMC extends Thread{
    
    private String nombreJugador;
    private MulticastSocket socketMC;
    private InterpreteMensajesMC interpreteMensajes;
    private EstadoJuego estadoJuego;
    
    private DatagramPacket recv;
    
    public RedEntradaMC(){
        this.interpreteMensajes = new InterpreteMensajesMC();
    }
    
    /**
     * espera por un mensaje del servidor y posteriormente lo envia al interprete
     */
    @Override
    public void run(){
        
        System.out.println("RED ENTRADA MC => empezando hilo");
        this.interpreteMensajes.setEstadoJuego(this.estadoJuego);
        
        byte[] buffer = new byte[2000];
        String temp;
        
        while(true){
            
            try {
                recv = new DatagramPacket(buffer, buffer.length);
                socketMC.receive(recv);
                
                temp = new String(recv.getData(),0,recv.getLength());
                System.out.println("RED ENTRADA MC => se recibió el mensaje " + temp);
                
                this.interpreteMensajes.interpretarMensaje(temp);
            } catch (IOException ex) {
                System.out.println("RED ENTRADA MC => error en la recepción de mensajes " + ex.getMessage());
                socketMC.close();
                break;
            }
            
        }
        
    }

    public void setSocketMC(MulticastSocket socketMC) {
        this.socketMC = socketMC;
    }
    
    public void setNombreJugador(String nombre){
        this.nombreJugador = nombre;
    }

    public void setEstadoJuego(EstadoJuego estadoJuego) {
        this.estadoJuego = estadoJuego;
    }
    
}
