/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.autonoma.MC.juego.elements;

import co.edu.autonoma.MC.ui.MundoWindow;

/**
 *
 * @author nikof
 */
public class ChatWorld extends Thread{
    
    private MundoWindow mw;
    private EstadoJuego ej;
    
    public ChatWorld(MundoWindow window, EstadoJuego estadoJuego){
        this.mw = window;
        this.ej = estadoJuego;
    }
    
    @Override
    public void run(){
        
        while(true){
            String mensajeChat = this.ej.getChat();
            
            if(mensajeChat.equals("cerrar")){
                System.out.println("CHATWORLD=> cerrando chat");
                break;
            }
            
            this.mw.mostrarMensajeChat(mensajeChat);
        }
        
    }
    
}
