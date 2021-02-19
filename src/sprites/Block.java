//ID 205686538
package sprites;
import biuoop.DrawSurface;
import gamesetting.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import interfaces.Collidable;
import interfaces.HitListener;
import interfaces.HitNotifier;
import interfaces.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The calls of sprites.Block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    //fields
    private Rectangle block;
    private java.awt.Color color;
    private List<HitListener> hitListeners = new ArrayList<>();
    /**
     * Instantiates a new sprites.Block.
     *constructor
     * @param r the geometry.Rectangle
     */
    public Block(Rectangle r) {
        this.block = r;
        this.color = Color.BLACK;
    }
    /**
     * Instantiates a new sprites.Block.
     *constructor
     * @param r the geometry.Rectangle
     * @param c the Color
     */
    public Block(Rectangle r, java.awt.Color c) {
        this.block = r;
        this.color = c;
    }
    /**
     * Instantiates a new sprites.Block.
     *constructor
     * @param p the geometry.Point upperLeft
     * @param height the height
     * @param width  the width
     */
    public Block(Point p, double height, double width) {
        Rectangle b = new Rectangle(p, height, width);
        this.block = b;
    }
    @Override
    /**
     * Return the "collision shape" of the object.
     */
    public Rectangle getCollisionRectangle() {
        return this.block;
    }
    @Override
    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     * @param collisionPoint
     * @param currentVelocity
     * @param hitter - the ball that hit the block
     * @return v - the new velocity
     */
     public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // checking if the collision point on the height lines of the rectangle
        if (ballOnSegment(this.block.getRectanglePoints()[0], this.block.getRectanglePoints()[1], collisionPoint)
                || ballOnSegment(this.block.getRectanglePoints()[2], this.block.getRectanglePoints()[3],
                collisionPoint)) {
            Velocity v = new Velocity(-currentVelocity.getVelocitydx(), currentVelocity.getVelocitydy());
            this.notifyHit(hitter);
            return v;
        }
        // checking if the collision point on the width lines of the rectangle
        if (ballOnSegment(this.block.getRectanglePoints()[0], this.block.getRectanglePoints()[2], collisionPoint)
                || ballOnSegment(this.block.getRectanglePoints()[1], this.block.getRectanglePoints()[3],
                collisionPoint)) {
            Velocity v = new Velocity(currentVelocity.getVelocitydx(), -currentVelocity.getVelocitydy());
            this.notifyHit(hitter);
            return v;
        }
        this.notifyHit(hitter);
        return currentVelocity;
    }

    /**
     * checking if the collision point if the ball is on segment with the block lines.
     * @param startPoint     the start point
     * @param endPoint       the end point
     * @param collisionPoint the collision point
     * @return the boolean - true if the ball on segment
     */
    public static boolean ballOnSegment(Point startPoint, Point endPoint, Point collisionPoint) {
        // checking that the orientation of three points are collinear
        int pointsOrientation = Line.orientation(startPoint, endPoint, collisionPoint);
        // checking that the three points are on segment
        boolean onSegment = Line.onSegment(startPoint, collisionPoint, endPoint);
        // if it is collinear and onSegment return true
        return (pointsOrientation == 0 && onSegment) ? true : false;
    }
    /**
     * draw the block and its borders.
     * @param d     the DrawSurface where the block will draw on
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.block.getUpperLeft().getX(), (int) this.block.getUpperLeft().getY(),
                (int) this.block.getWidth(), (int) this.block.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.block.getUpperLeft().getX(), (int) this.block.getUpperLeft().getY(),
                (int) this.block.getWidth(), (int) this.block.getHeight());
    }

    @Override
    public void timePassed() {
    }

    /**
     * Add the block to game.
     * @param g the game_setting.Game that the block in
     */
// adding the block to the game_setting.GameEnvironment and game_setting.SpriteCollection
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * Remove from game.
     * @param game the game
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);

    }

    /**
     * Notify hit - for all listeners.
     * @param hitter the hitter
     */
    public void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
