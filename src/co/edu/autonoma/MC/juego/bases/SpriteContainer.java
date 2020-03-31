/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.autonoma.MC.juego.bases;

import java.util.ArrayList;

/**
 *
 * @author educacion
 */
public abstract class SpriteContainer extends Sprite implements GraphicContainer
{
    protected ArrayList<Sprite> sprites;   
    
    public SpriteContainer(int x, int y, int height, int width) {
        super(x, y, height, width);
        
        sprites = new ArrayList<>();
    }
    
    @Override
    public boolean addSprite(Sprite sprite)
    {
        return sprites.add(sprite);
    }
    
    public void removeSprite(int index)
    {
        sprites.remove(index);
    }
    
    @Override
    public void removeSprite(Sprite sprite)
    {
        sprites.remove(sprite);
    }
    
    public int sizeSprites()
    {
        return sprites.size();
    }
}
