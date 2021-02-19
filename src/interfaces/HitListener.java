//ID 205686538
package interfaces;
import sprites.Ball;
import sprites.Block;

/**
 * The interface Hit listener.
 */
public interface HitListener {
    /**
     * Hit event- This method is called whenever the beingHit object is hit.
     *The hitter parameter is the sprites.Ball that's doing the hitting.
     * @param beingHit the being hit
     * @param hitter   the hitter
     */
    void hitEvent(Block beingHit, Ball hitter);
}