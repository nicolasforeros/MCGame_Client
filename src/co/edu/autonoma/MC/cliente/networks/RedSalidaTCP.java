/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.autonoma.MC.cliente.networks;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Realiza la salida de mensajes al servidor
 *
 * @author Nicolas Forero
 * @author Leandra Builes
 */
public class RedSalidaTCP {
    private DataOutputStream out;
    
    public void setOut(DataOutputStream out) {
        this.out = out;
    }

    public void enviarMensaje(String mensajeEnviar) {
        try {
            out.writeUTF(mensajeEnviar);
            
            out.flush();
        } catch (IOException ex) {
            System.out.println("RED SALIDA TCP=> Error enviando el mensaje al servidor");
        }
    }
}
