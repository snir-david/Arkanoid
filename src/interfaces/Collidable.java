//Id 205686538
package interfaces;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import sprites.Ball;

/**
 * The interface  of interfaces.Collidable.
 */
public interface Collidable {
    /**
     * Gets collision rectangle.
     * @return the collision rectangle
     */
    Rectangle getCollisionRectangle();
    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     * @param collisionPoint the collision point
     * @param currentVelocity the current velocity
     * @param hitter - the ball that hit the block
     * @return the new geometry.Velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
