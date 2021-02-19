package gamesetting;
import biuoop.DrawSurface;
import interfaces.Sprite;

import java.util.ArrayList;
import java.util.List;
/**
 * The type interfaces.Sprite collection.
 */
public class SpriteCollection {
    //fields
    private List<Sprite> sprites;
    /**
     * Instantiates a new interfaces.Sprite collection.
     * constructor
     */
    public SpriteCollection() {
        List<Sprite> s = new ArrayList<>();
        this.sprites = s;
    }
    /**
     * Instantiates a new interfaces.Sprite collection.
     *constructor
     * @param s the List<interfaces.Sprite>
     */
    public SpriteCollection(List<Sprite> s) {
        this.sprites = s;
    }

    /**
     * Instantiates a new interfaces.Sprite collection.
     *constructor and adding the sprite object
     * @param s the List<interfaces.Sprite>
     * @param sprt the interfaces.Sprite
     */
    public SpriteCollection(List<Sprite> s, Sprite sprt) {
        this.sprites = s;
        sprites.add(sprt);
    }

    /**
     * Add sprite.
     * @param s the interfaces.Sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }
    /**
     * Notify all time passed.
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spritesCopy = new ArrayList<>(this.sprites);
        for (Sprite s:spritesCopy) {
            s.timePassed();
        }
    }
    /**
     * Draw all on.
     *call drawOn(d) on all sprites.
     * @param d the DrawSurface
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s:this.sprites) {
            s.drawOn(d);
        }
    }
    /**
     * Gets sprites.
     * @return the sprites - List<interfaces.Sprite>
     */
    public List<Sprite> getSprites() {
        return this.sprites;
    }
}