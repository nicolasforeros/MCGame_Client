/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.autonoma.MC.cliente.elements;

import java.io.IOException;
import java.io.StringWriter;
import org.json.simple.JSONObject;

/**
 * Escribe los mensajes que saldran hacia el servidor
 *
 * @author Nicolas Forero
 * @author Leandra Builes
 */
public class EscritorMensajesTCP {
    String nombreJugador;
    JSONObject obj;

    public EscritorMensajesTCP() {
        this.nombreJugador = null;
        
        this.obj = new JSONObject();
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
        this.obj.put("jugador", this.nombreJugador);
    }

    /**
     * Segun una jugada dada, devuelve el mensaje que sigue los protocolos de
     * comunicacion del juego que será enviado al servidor
     * 
     * @param jugada
     * @return el mensaje que será enviado
     */
    String escribirMensaje(int jugada) {
        this.obj.put("jugada", jugada);
        
        StringWriter out = new StringWriter();
        
        try {
            obj.writeJSONString(out);
        } catch (IOException ex) {
            System.out.println("ESCRITOR MENSAJE=> error realizando el mensaje: " + ex.getMessage());
        }

        String jsonText = out.toString();
        
        return jsonText;
    }
    
}
