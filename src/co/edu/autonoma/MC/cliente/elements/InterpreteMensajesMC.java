/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.autonoma.MC.cliente.elements;

import co.edu.autonoma.MC.juego.elements.EstadoJuego;
import java.awt.Color;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author nikof
 */
public class InterpreteMensajesMC {
    private EstadoJuego estadoJuego;
    private JSONParser parser;
    private JSONObject obj;
    
    public InterpreteMensajesMC(){
        parser = new JSONParser();
    }

    public void setEstadoJuego(EstadoJuego estadoJuego) {
        this.estadoJuego = estadoJuego;
    }
    
    public void interpretarMensaje(String mensaje){
        String accion = "";
        String nombreJugador = "";
        // mas atributos que pueden llegar
        
        try {
            obj = (JSONObject)parser.parse(mensaje);
        }catch(ParseException ex){
            System.out.println("INTERPRETE MC => Error interpretando el mensaje: " + ex.getMessage());
            return;
        }
        
        accion = (String) obj.get("accion");
        nombreJugador = (String) obj.get("jugador");
        
        if(accion.equals("addJugadorError")){
            System.out.println("INTERPRETE MC => jugador erroneo");
            
            if(nombreJugador.equals(estadoJuego.getNombreJugador()) && !(estadoJuego.isJugadorCreado()) ){
                estadoJuego.crearJugadorLocal();
            }
        }
        
        if(accion.equals("addJugador")){
            System.out.println("INTERPRETE MC => Añadiendo un jugador");
            
            int x = (int) (long)obj.get("x");
            int y = (int) (long)obj.get("y");
            int colorI = (int) (long)obj.get("color");
            int size = (int) (long)obj.get("size");
            
            Color color = new Color(colorI);
            
            if(nombreJugador.equals(estadoJuego.getNombreJugador())){
                estadoJuego.crearJugadorLocal(x, y, color, nombreJugador);
            }else{
                estadoJuego.addRival(x, y, size, color, nombreJugador, false);
            }
        }
        
        if(accion.equals("actualizacion")){
            System.out.println("INTERPRETE MC => Actualizando un jugador");

            int x = (int) (long)obj.get("x");
            int y = (int) (long)obj.get("y");
            int size = (int) (long)obj.get("size");
            boolean peleando = (boolean) obj.get("peleando");
            
            if(!nombreJugador.equals(estadoJuego.getNombreJugador())){
                estadoJuego.actualizarRival(x, y, size, nombreJugador, peleando);
            }
        }
        
        if(accion.equals("estadoGeneral")){
            System.out.println("INTERPRETE MC => Obteniendo el estado general");
            
            JSONArray listaRivales = (JSONArray) obj.get("jugadores");
            
            listaRivales.forEach((objRival) -> {
                JSONObject rivalJSON = (JSONObject) objRival;
                
                String nombreRival = (String) rivalJSON.get("jugador");
                
                if(estadoJuego.getNombreJugador().equals(nombreRival)){
                    return;
                }
                
                if(!estadoJuego.verificarRival(nombreRival)){
                    
                    int x = (int) (long)rivalJSON.get("x");
                    int y = (int) (long)rivalJSON.get("y");
                    int colorI = (int) (long)rivalJSON.get("color");
                    int size = (int) (long)rivalJSON.get("size");
                    Color color = new Color(colorI);
                    boolean peleando = (boolean) rivalJSON.get("peleando");
                    
                    estadoJuego.addRival(x, y, size, color, nombreRival, peleando);
                }
            });
        }
        
        if(accion.equals("pelea")){
            String nombreRival = (String)obj.get("rival");
            
            estadoJuego.pelear(nombreJugador, nombreRival);
        }
        
        if(accion.equals("chat")){
            String mensajeChat = (String) obj.get("chat");
            
            estadoJuego.setChat(nombreJugador + ":  " + mensajeChat);
            
            String[] msjs = mensajeChat.split("::");
            
            if(msjs.length==2 && msjs[0].equals("regalarvida")){
                estadoJuego.aumentarVidaJugador(msjs[1]);
                estadoJuego.disminuirVidaJugador(nombreJugador);
            }
        }
        
        
       
    }
}
