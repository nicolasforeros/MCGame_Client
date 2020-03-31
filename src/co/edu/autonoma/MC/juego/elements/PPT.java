/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.autonoma.MC.juego.elements;

import co.edu.autonoma.MC.cliente.elements.Cliente;
import co.edu.autonoma.MC.ui.PPTWindow;

/**
 *
 * @author nikof
 */
public class PPT extends Thread{
    
    private Cliente cliente;
    
    public PPT(){
    }
    
    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }
    
    @Override
    public void run(){
        
        Jugador jugadorLocal = cliente.getJugadorLocal();
        
        while(true){
            jugadorLocal.esperarPelea();
            
            boolean conexion = this.cliente.conectarAServidor();
        
            if(conexion){
                PPTWindow pw = new PPTWindow(cliente);

                pw.setLocationRelativeTo(null);
                pw.setVisible(true);
                pw.iniciarEntrada();
            }
        }
    }
    
}
