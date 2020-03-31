/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.autonoma.MC.cliente.networks;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * Realiza la salida de mensajes al servidor
 *
 * @author Nicolas Forero
 * @author Leandra Builes
 */
public class RedSalidaMC {
    private MulticastSocket socketMC;
    private InetAddress grupo;
    private int puertoMC;

    public void setSalida(MulticastSocket socketMC, InetAddress grupo, int puertoMC) {
        this.socketMC = socketMC;
        this.grupo = grupo;
        this.puertoMC = puertoMC;
    }
    
    public void enviarMensaje(String mensajeEnviar) {
        
        System.out.println("RED SALIDA MC => Enviando mensaje: " + mensajeEnviar);
        
        try {
            DatagramPacket dp = new DatagramPacket(mensajeEnviar.getBytes(), mensajeEnviar.length(),grupo, puertoMC);

            socketMC.send(dp);
        } catch (IOException ex) {
            System.out.println("RED SALIDA MC=> Error enviando el mensaje al servidor");
        }
    }
}
