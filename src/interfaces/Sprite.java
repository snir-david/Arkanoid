//ID 205686538
package interfaces;
import biuoop.DrawSurface;
/**
 * The interface interfaces.Sprite.
 */
public interface Sprite {
    /**
     * Draw on.
     *draw the sprite to the screen
     * @param d the DrawSurface
     */
    void drawOn(DrawSurface d);
    /**
     * Time passed.
     * notify the sprite that time has passed
     */
    void timePassed();
}