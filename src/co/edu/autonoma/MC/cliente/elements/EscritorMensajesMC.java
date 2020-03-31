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
public class EscritorMensajesMC {
    String nombreJugador;
    JSONObject obj;

    public EscritorMensajesMC() {
        this.nombreJugador = null;
        
        this.obj = new JSONObject();
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
        this.obj.put("jugador", this.nombreJugador);
    }

//    /**
//     * Segun una jugada dada, devuelve el mensaje que sigue los protocolos de
//     * comunicacion del juego que será enviado al servidor
//     * 
//     * @param jugada
//     * @return el mensaje que será enviado
//     */
//    String escribirMensaje(int jugada) {
//        this.obj.put("jugada", jugada);
//        
//        StringWriter out = new StringWriter();
//        
//        try {
//            obj.writeJSONString(out);
//        } catch (IOException ex) {
//            System.out.println("ESCRITOR MENSAJE MC=> error realizando el mensaje: " + ex.getMessage());
//        }
//
//        String jsonText = out.toString();
//        
//        return jsonText;
//    }

    public String escribirMensajeActualizacion(int nx, int ny, int size, boolean peleando) {
        this.obj.put("accion", "actualizacion");
        this.obj.put("x", nx);
        this.obj.put("y", ny);
        this.obj.put("size", size);
        this.obj.put("peleando", peleando);
        
        
        StringWriter out = new StringWriter();
        
        try {
            obj.writeJSONString(out);
        } catch (IOException ex) {
            System.out.println("ESCRITOR MENSAJE MC=> error realizando el mensaje de actualizacion: " + ex.getMessage());
        }

        String jsonText = out.toString();
        
        return jsonText;
    }
    
    public String escribirMensajeNuevoJugador(String nombre){
        this.nombreJugador = nombre;
        this.obj.put("jugador", this.nombreJugador);
        this.obj.put("accion", "nuevoJugador");
        
        StringWriter out = new StringWriter();
        
        try {
            obj.writeJSONString(out);
        } catch (IOException ex) {
            System.out.println("ESCRITOR MENSAJE MC=> error realizando el mensaje de mover: " + ex.getMessage());
        }

        String jsonText = out.toString();
        
        return jsonText;
        
    }
    
    public String escribirMensajePelea(String nombreRival){
        this.obj.put("accion", "pelea");
        this.obj.put("rival", nombreRival);
        
        StringWriter out = new StringWriter();
        
        try {
            obj.writeJSONString(out);
        } catch (IOException ex) {
            System.out.println("ESCRITOR MENSAJE MC=> error realizando el mensaje de mover: " + ex.getMessage());
        }

        String jsonText = out.toString();
        
        return jsonText;
    }
    
    public String escribirMensajeChat(String mensajeChat, int size){
        this.obj.put("accion","chat");
        this.obj.put("chat",mensajeChat);
        this.obj.put("size",size);
        
        StringWriter out = new StringWriter();
        
        try {
            obj.writeJSONString(out);
        } catch (IOException ex) {
            System.out.println("ESCRITOR MENSAJE MC=> error realizando el mensaje de chat: " + ex.getMessage());
        }
        
        String jsonText = out.toString();
        System.out.println("ESCRITORMENSAJESMC => mensaje chat: " + jsonText);
        return jsonText;
    }
    
}
