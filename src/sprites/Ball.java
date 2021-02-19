// ID 205686538
package sprites;
import biuoop.DrawSurface;
import gamesetting.CollisionInfo;
import gamesetting.GameLevel;
import gamesetting.GameEnvironment;
import gamesetting.SpriteCollection;
import geometry.Line;
import geometry.Point;
import geometry.Velocity;
import interfaces.HitListener;
import interfaces.HitNotifier;
import interfaces.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * A sprites.Ball class.
 * The class support - setting a new sprites.Ball and drawing it on drawing surface.
 */
public class Ball implements Sprite, HitNotifier {
    //fields
    private Point center;
    private int r;
    private Velocity v;
    private java.awt.Color color;
    private GameEnvironment gameEnvironment;
    private SpriteCollection spriteCollection;
    private List<HitListener> hitListeners = new ArrayList<>();
    /**
     * constructor.
     * @param center - the center point of the ball
     * @param r - the radius of the ball
     * @param color - the color of the ball
     */
    // constructor
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.v = new Velocity(0 , 0);
    }
    /**
     * constructor.
     * @param x - the x of the center point of the ball
     * @param y - the y of the center point of the ball
     * @param r - the radius of the ball
     * @param color - the color of the ball
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        Point p = new Point(x, y);
        this.center = p;
        this.r = r;
        this.color = color;
        this.v = new Velocity(0 , 0);
    }
    // accessors
    /**
     * return the ball x point.
     * @return getX.
     */
    public int getX() {
        return (int) this.center.getX();
    }
    /**
     * return the ball y point.
     * @return getY.
     */
    public int getY() {
        return (int) this.center.getY();
    }
    /**
     * return the ball radius.
     * @return int getSize.
     */
    public int getSize() {
        return this.r;
    }
    /**
     * return the ball color.
     * @return Color getColor.
     */
    public java.awt.Color getColor() {
        return this.color;
    }
    /**
     * draw the ball on the given DrawSurface.
     * @param surface - a drawsurface that the circle will be drawn.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.r);
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.r);
    }

    @Override
    /**
     * tell the ball that time passed on it should move one step.
     */
    public void timePassed() {
        moveOneStep();
    }

    /**
     * geometry.Velocity setters.
     * @param velocity - velocity
     */
    public void setVelocity(Velocity velocity) {
        this.v = velocity;
    }
    /**
     * geometry.Velocity setters.
      *@param dx - dx velocity
     * @param dy - dy velocity
            */
    public void setVelocity(double dx, double dy) {
        Velocity velocity = new Velocity(dx, dy);
        this.v = velocity;
    }
    /**
     * geometry.Velocity getter.
     * @return the velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * moveOneStep - moving the ball around bounded in windows size.
     */
    public void moveOneStep() {
        Line ballLine;
        CollisionInfo collInfo = null;
        Point oldPoint = this.center;
        do {
            if (collInfo != null) {
                this.setVelocity(collInfo.collisionObject().hit(this, collInfo.collisionPoint(), this.v));
                this.center = this.getVelocity().applyToPoint(oldPoint);
            }
            this.center = this.getVelocity().applyToPoint(this.center);
            ballLine = new Line(oldPoint, new Point(this.center.getX(), this.center.getY()));
            collInfo = this.gameEnvironment.getClosestCollision(ballLine);
        } while (collInfo != null);
    }
    /**
     * game_setting.Game Environment setters.
     * @param ge - the game_setting.Game Environment of the ball
     */
    public void setGameEnviroment(GameEnvironment ge) {
        this.gameEnvironment = ge;
    }
    /**
     * game_setting.Game Environment getter.
     * @return gameEnvironment - the game_setting.Game Environment of the ball
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEnvironment;
    }
    /**
     * interfaces.Sprite Collection setters.
     * @param sc - the interfaces.Sprite Collection of the ball
     */
    public void setSprite(SpriteCollection sc) {
        this.spriteCollection = sc;
    }
    /**
     * adding the ball to a game.
     * @param g - the game of the ball
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        this.gameEnvironment = g.gameEnvironment();
    }

    @Override
    public void addHitListener(HitListener br) {
        hitListeners.add(br);
    }

    @Override
    public void removeHitListener(HitListener br) {
        hitListeners.remove(br);
    }

    /**
     * Remove from game.
     * @param game the game
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}