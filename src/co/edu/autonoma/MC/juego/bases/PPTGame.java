/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.autonoma.MC.juego.bases;

/**
 *
 * @author nikof
 */
public interface PPTGame {
    
    public static final int TERMINAR=-2; 
    public static final int INICIO=-1; 
    public static final int PIEDRA=0; 
    public static final int PAPEL=1; 
    public static final int TIJERA=2;
    public static final int NUEVA_PARTIDA=3;
    
    public static final int ESTADO_PREPARADO=4;
    public static final int ESTADO_NO_PREPARADO=5;
    public static final int ESTADO_J1OK_J2NO=6;
    public static final int ESTADO_J1NO_J2OK=7;
    public static final int ESTADO_J1OK_J2OK=8;
    
    public void empezarJuego(String nombreYo, String nombreRival);
    
    public void rivalOK();
    
    public void terminarJuego(String nombreGanador, int jugadaYo, int jugadaRival);
    
    public void cerrarPartida();

    public void seleccionarJugada(int jugada);
    
}
