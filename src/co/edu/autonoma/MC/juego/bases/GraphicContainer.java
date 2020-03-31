/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.autonoma.MC.juego.bases;

import java.awt.Rectangle;

/**
 *
 * @author educacion
 */
public interface GraphicContainer {
    public void refresh();
    public Rectangle getBoundaries();
    public boolean addSprite(Sprite sprite);
    public void removeSprite(Sprite sprite);
}
