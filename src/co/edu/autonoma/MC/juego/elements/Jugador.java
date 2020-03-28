/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.autonoma.MC.juego.elements;

import co.edu.autonoma.MC.juego.bases.SpriteMobile;
import java.awt.Graphics;

/**
 *
 * @author nikof
 */
public class Jugador extends SpriteMobile{
    
    public static int INITIAL_WIDTH=4;
    public static int INITIAL_HEIGTH=4;
    
    public Jugador(int x, int y){
        super(x,y,Jugador.INITIAL_HEIGTH,Jugador.INITIAL_WIDTH);
    }

    @Override
    public boolean move(int direction) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void paint(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
