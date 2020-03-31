/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.autonoma.MC.juego.elements;

import co.edu.autonoma.MC.juego.bases.Sprite;
import co.edu.autonoma.MC.juego.bases.SpriteContainer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

/**
 *
 * @author nikof
 */
public class Mundo extends SpriteContainer{
    
    private Jugador jugadorLocal;
    private EstadoJuego estadoJuego;
    private AccionesJugador acciones;

    public Mundo(int x, int y, int height, int width) {
        super(x, y, height, width);
        
        this.color = Color.LIGHT_GRAY;
        
        this.acciones = new AccionesJugador();
    }
    
    public AccionesJugador keyPressed(int code)
    {
        
        if(code == KeyEvent.VK_UP |
           code == KeyEvent.VK_DOWN |
           code == KeyEvent.VK_LEFT |
           code == KeyEvent.VK_RIGHT)
        {
            if(jugadorLocal.move(code))
            {
                acciones.setMovimiento(true);
                
                this.verificarPelea();
            }
        }
        
        return acciones;
    }
    
    public void verificarPelea(){
        for(int i=0; i<sprites.size(); i++)
        {
            if(sprites.get(i) instanceof Jugador)
            {
                Jugador rival = (Jugador) sprites.get(i);
                
                if(jugadorLocal.checkCollision(rival) && 
                        !jugadorLocal.isPeleando() &&
                        !rival.isPeleando())
                {
                    acciones.setNombreRivalPelea(rival.getUsername());
                    acciones.setPelea(true);
                    return;
                }
            }
        }
        
        acciones.setNombreRivalPelea(null);
        acciones.setPelea(false);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);

        // Painting the online players (sprites in general)
        sprites.forEach((sprite) -> {
            sprite.paint(g);
        });
        
        jugadorLocal.paint(g);
    }

    @Override
    public void refresh() {
        if(gameContainer != null)
            gameContainer.refresh();
    }

    @Override
    public Rectangle getBoundaries() {
        return new Rectangle(x, y, width, height);
    }

    public Jugador getJugadorLocal() {
        return jugadorLocal;
    }

    public void setJugadorLocal(Jugador jugadorLocal) {
        this.jugadorLocal = jugadorLocal;
    }

    public void setEstadoJuego(EstadoJuego estadoJuego) {
        this.estadoJuego = estadoJuego;
        this.jugadorLocal = estadoJuego.getJugadorLocal();
        this.jugadorLocal.setGraphicContainer(this);
    }
}
