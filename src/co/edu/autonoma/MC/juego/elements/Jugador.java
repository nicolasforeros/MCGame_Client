/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.autonoma.MC.juego.elements;

import co.edu.autonoma.MC.juego.bases.SpriteMobile;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nikof
 */
public class Jugador extends SpriteMobile{
    
    public static int INITIAL_WIDTH=40;
    public static int INITIAL_HEIGTH=40;
    public static final int GROW_SIZE = 4;
    
    private String username;
    private boolean peleando;
    
    public Jugador(int x, int y, Color color, String username){
        super(x,y,Jugador.INITIAL_HEIGTH,Jugador.INITIAL_WIDTH);
        this.color = color;
        this.step = 4;
        this.username = username;
    }
    
    public Jugador(int x, int y, int size, Color color, String username){
        super(x,y,size,size);
        this.color = color;
        this.step = 4;
        this.username = username;
    }
    
    public synchronized void iniciarPelea(){
        notify();
    }
    
    public synchronized void esperarPelea(){
        try {
            wait();
        } catch (InterruptedException ex) {
            System.out.println("JUGADOR => Esperando a una pelea");
        }
    }
    
    @Override
    public boolean move(int direction)
    {
        int nx = x;
        int ny = y;
        
        switch(direction)
        {
            case KeyEvent.VK_UP:
                ny -= step;
            break;

            case KeyEvent.VK_DOWN:
                ny += step;
            break;

            case KeyEvent.VK_LEFT:
                nx -= step;
            break;

            case KeyEvent.VK_RIGHT:
                nx += step;
            break;
        }
        
        if(!isOutOfGraphicContainer(nx, ny, width, height))
        {
            x = nx;
            y = ny;

            if(gameContainer != null)
                gameContainer.refresh();
            
            return true;
        }
        
        return false;
    }

    //crecer
    public void grow()
    {
        x -= GROW_SIZE/2;
        y -= GROW_SIZE/2;
        width += GROW_SIZE;
        height += GROW_SIZE;
        
        gameContainer.refresh();
    }
    
    //disminuir
    public void shrink()
    {
        x += GROW_SIZE/2;
        y += GROW_SIZE/2;
        width -= GROW_SIZE;
        height -= GROW_SIZE;
        
        gameContainer.refresh();
    }
    
    public void updatePlayer(int x, int y, int size, boolean peleando){
        this.x = x;
        this.y = y;
        
        this.height = size;
        this.width = size;
        
        this.peleando = peleando;
        
        gameContainer.refresh();
    }
    
    @Override
    public void paint(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
        g.setColor(Color.BLACK);
        g.drawString(username, x+3, y+(height/2));
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isPeleando() {
        return peleando;
    }

    public void setPeleando(boolean peleando) {
        this.peleando = peleando;
    }
}
