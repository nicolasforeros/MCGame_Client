/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.autonoma.MC.cliente.elements;

import co.edu.autonoma.MC.juego.bases.PPTGame;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Interpreta los mensajes entrantes y efectua una accion en el juego
 *
 * @author Nicolas Forero
 * @author Leandra Builes
 */
public class InterpreteMensajesTCP {
    
    private String nombreJugador;
    private PPTGame juego;
    private JSONParser parser;

    public InterpreteMensajesTCP() {
        parser = new JSONParser();
    }

    public void setJuego(PPTGame juego) {
        this.juego = juego;
    }

    /**
     * Interpreta el mensaje dado, y de acuerdo a este realiza una accion en el
     * juego
     * 
     * @param mensajeIn 
     */
    public void interpretarMensaje(String mensajeIn) {
        JSONObject obj;
        String jugador1 = "";
        String jugador2 = "";
        int jugada1 = 500;
        int jugada2 = 500;
        int estado = 500;
        String ganador = "";
        
        try {
            obj = (JSONObject)parser.parse(mensajeIn);
            
            jugador1 = (String)obj.get("jugador1");
            jugador2 = (String)obj.get("jugador2");
            
            jugada1 = (int) (long)obj.get("jugada1");
            jugada2 = (int) (long)obj.get("jugada2");
            
            estado = (int) (long)obj.get("estado");   
            
            ganador = (String)obj.get("ganador");
        } catch (ParseException ex) {
            System.out.println("Error procesando el mensaje");
            this.juego.cerrarPartida();
            return;
        }
        
        switch(estado){
            case PPTGame.ESTADO_NO_PREPARADO:
                System.out.println("INTERPRETEMEN=> Estado no preparado");
                break;
                
            case PPTGame.ESTADO_PREPARADO:
                System.out.println("INTERPRETEMEN=> Estado preparado");
                String nombreYo="";
                String nombreRival="";
                
                if (jugador1==null)
                    jugador1="";
                
                if (jugador2==null)
                    jugador2="";
                
                if(jugador1.equalsIgnoreCase(this.nombreJugador)){
                    nombreYo = jugador1;
                    nombreRival = jugador2;
                }
                
                if(jugador2.equalsIgnoreCase(this.nombreJugador)){
                    nombreYo = jugador2;
                    nombreRival = jugador1;
                }
                
                this.juego.empezarJuego(nombreYo, nombreRival);
                break;
                
            case PPTGame.ESTADO_J1OK_J2NO:
                System.out.println("INTERPRETEMEN=> Estado j1ok j2no");
                if (jugador1!=null && jugador1.equalsIgnoreCase(this.nombreJugador)) {
                    this.juego.seleccionarJugada(jugada1);
                }else{
                    this.juego.rivalOK();
                }
                break;
                
            case PPTGame.ESTADO_J1NO_J2OK:
                System.out.println("INTERPRETEMEN=> Estado j1no j2ok");
                if (jugador2!=null && jugador2.equalsIgnoreCase(this.nombreJugador)) {
                    this.juego.seleccionarJugada(jugada2);
                }else{
                    this.juego.rivalOK();
                }
                break;
                
            case PPTGame.ESTADO_J1OK_J2OK:
                System.out.println("INTERPRETEMEN=> Estado j1ok j2ok");
                if (jugador1!=null && jugador1.equalsIgnoreCase(this.nombreJugador))
                    this.juego.terminarJuego(ganador,jugada1,jugada2);
                
                if (jugador2!=null && jugador2.equalsIgnoreCase(this.nombreJugador))
                    this.juego.terminarJuego(ganador,jugada2,jugada1);
                break;
        }
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }
}
