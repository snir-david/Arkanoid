//ID 205686538
package interfaces;
import biuoop.DrawSurface;

/**
 * The interface Animation.
 */
public interface Animation {
    /**
     * Do one frame.
     *
     * @param d the DrawSurface
     */
    void doOneFrame(DrawSurface d);

    /**
     * Should stop boolean - when the animation need to stop.
     *
     * @return boolean
     */
    boolean shouldStop();
}