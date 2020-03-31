/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.autonoma.MC.juego.elements;

/**
 *
 * @author nikof
 */
public class AccionesJugador {
    private boolean movimiento;
    private boolean pelea;
    private String nombreRivalPelea;
    
    public AccionesJugador(){
        movimiento = false;
        pelea = false;
    }

    public String getNombreRivalPelea() {
        return nombreRivalPelea;
    }

    public void setNombreRivalPelea(String nombreRivalPelea) {
        this.nombreRivalPelea = nombreRivalPelea;
    }

    public boolean isMovimiento() {
        return movimiento;
    }

    public void setMovimiento(boolean movio) {
        this.movimiento = movio;
    }

    public boolean isPelea() {
        return pelea;
    }

    public void setPelea(boolean pelea) {
        this.pelea = pelea;
    }
    
    
}
