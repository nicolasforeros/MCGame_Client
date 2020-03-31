/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.autonoma.MC.cliente.networks;

import co.edu.autonoma.MC.cliente.elements.Cliente;
import co.edu.autonoma.MC.cliente.elements.InterpreteMensajesTCP;
import co.edu.autonoma.MC.juego.bases.PPTGame;
import co.edu.autonoma.MC.juego.elements.Jugador;
import java.io.DataInputStream;
import java.io.IOException;

/**
 * Realiza y gesitona la recepcion de mensajes
 *
 * @author Nicolas Forero
 * @author Leandra Builes
 */
public class RedEntradaTCP extends Thread{
    
    private String nombreJugador;
    
    private DataInputStream in;
    private PPTGame juego;
    private InterpreteMensajesTCP interpreteMensajes;
    private Jugador jugadorLocal;
    private Cliente cliente;
    
    public RedEntradaTCP(){
        this.interpreteMensajes = new InterpreteMensajesTCP();
    }
    
    /**
     * espera por un mensaje del servidor y posteriormente lo envia al interprete
     */
    @Override
    public void run(){
        
        System.out.println("RED ENTRADA TCP => empezando hilo");
        this.interpreteMensajes.setJuego(this.juego);
        this.interpreteMensajes.setNombreJugador(this.nombreJugador);
        this.interpreteMensajes.setJugadorLocal(this.jugadorLocal);
        this.interpreteMensajes.setCliente(this.cliente);
        
        while(true){
            
            try {
                String mensajeIn = in.readUTF();
                System.out.println("RED ENTRADA TCP => se recibió el mensaje " + mensajeIn);
                
                this.interpreteMensajes.interpretarMensaje(mensajeIn);
            } catch (IOException ex) {
                System.out.println("RED ENTRADA TCP => error en la recepción de mensajes " + ex.getMessage());
                
                break;
            }
            
        }
        
    }

    public void setIn(DataInputStream in) {
        this.in = in;
    }

    public void setJuego(PPTGame panel) {
        this.juego = panel;
    }
    
    public void setNombreJugador(String nombre){
        this.nombreJugador = nombre;
    }
    
    public void setJugadorLocal(Jugador jugadorLocal){
        this.jugadorLocal = jugadorLocal;
    }
    
    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }
}
