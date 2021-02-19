//Id 205686538
package sprites;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import gamesetting.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import interfaces.Collidable;
import interfaces.Sprite;
import java.awt.Color;

/**
 * The type sprites.Paddle.
 */
public class Paddle implements Sprite, Collidable {
    //fileds
    private biuoop.KeyboardSensor keyboard;
    private Block block;
    private int paddleWidth;
    private int paddleVelocity;
    private Point paddleLocation;
    static final Point MIDLLE_POINT = new Point(400, 590);
    static final int PADDLE_HEIGHT = 15;
    static final int WINDOWS_WIDTH = 800;

    /**
     * Instantiates a new sprites.Paddle.
     * constructs
     *
     * @param gu    the game gui
     * @param v     the paddle velocity
     * @param width the paddle width
     */
    public Paddle(GUI gu, int v, int width) {
        this.paddleWidth = width;
        this.paddleVelocity = v;
        this.paddleLocation = new Point(MIDLLE_POINT.getX() - (paddleWidth / 2), MIDLLE_POINT.getY()
                - (PADDLE_HEIGHT / 2));
        this.block = new Block(new Rectangle(paddleLocation, paddleWidth, PADDLE_HEIGHT), Color.YELLOW);
        this.keyboard = gu.getKeyboardSensor();
    }

    /**
     * Move left.
     * moving the paddle left and checking it doesn't get out the borders
     */
    public void moveLeft() {
        paddleLocation.changePoint(-paddleVelocity, 0);
        if (this.paddleInBorders(this)) {
            this.block = new Block(new Rectangle(paddleLocation, paddleWidth, PADDLE_HEIGHT), Color.YELLOW);
        } else {
            paddleLocation.changePoint(paddleVelocity, 0);
        }
    }

    /**
     * Move right.
     * moving the paddle right and checking it doesn't get out the borders
     * @param paddle the paddle
     */
    public void moveRight(Paddle paddle) {
        paddleLocation.changePoint(paddle.paddleVelocity, 0);
        if (this.paddleInBorders(paddle)) {
            paddle.block = new Block(new Rectangle(paddleLocation, paddle.paddleWidth, Paddle.PADDLE_HEIGHT),
                    Color.YELLOW);
        } else {
            paddleLocation.changePoint(-paddle.paddleVelocity, 0);
        }
    }

    /**
     * timePassed.
     * checking if the right key or the left key is pressed
     */
    public void timePassed() {
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight(this);
        }
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
    }
    /**
     * drawOn - draw the paddle on a given DrawSurface.
     *@param d DrawSurface
     */
    public void drawOn(DrawSurface d) {
        this.block.drawOn(d);
    }
    /**
     * getCollisionRectangle.
     *@return  this geometry.Rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.block.getCollisionRectangle();
    }
    /**
     * hit - if the object that collides is the paddle
     * checking in which part and changing the velocity.
     * @param collisionPoint the collision point
     * @param  currentVelocity - the current velocity
     * @param hitter - the ball that hit the block
     *@return  v the new geometry.Velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Point startPoint = new Point(paddleLocation.getX(), paddleLocation.getY());
        Point [] paddlePoint = new Point[6];
        Velocity v;
        //taking down the paddle into 5 parts
        for (int i = 0; i < 6; i++) {
            paddlePoint[i] = new Point(startPoint.getX(), startPoint.getY());
            startPoint.changePoint(paddleWidth / 6, 0);
        }
        //finding the speed by using the formula - v^2 = v_x^2 + v_y^2
        double speed = Math.sqrt(Math.pow(currentVelocity.getVelocitydx(), 2)
                + (Math.pow(currentVelocity.getVelocitydy(), 2)));
        //checking where the ball hit the paddle
        if (Block.ballOnSegment(paddlePoint[0], paddlePoint[1], collisionPoint)) {
            v = Velocity.fromAngleAndSpeed(240, speed);
            return v;
        }
        if (Block.ballOnSegment(paddlePoint[1], paddlePoint[2], collisionPoint)) {
            v = Velocity.fromAngleAndSpeed(210, speed);
            return v;
        }
        if (Block.ballOnSegment(paddlePoint[2], paddlePoint[3], collisionPoint)) {
            return this.block.hit(hitter, collisionPoint, currentVelocity);
        }
        if (Block.ballOnSegment(paddlePoint[3], paddlePoint[4], collisionPoint)) {
            v = Velocity.fromAngleAndSpeed(120, speed);
            return v;
        }
        if (Block.ballOnSegment(paddlePoint[4], paddlePoint[5], collisionPoint)) {
            v = Velocity.fromAngleAndSpeed(150, speed);
            return v;
        }
        return this.block.hit(hitter, collisionPoint, currentVelocity);
    }

    /**
     * Add to game.
     *Add this paddle to the game
     * @param g the game_setting.Game
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }


    /**
     * sprites.Paddle in borders boolean.
     *checking that the paddle inside the windows border
     * @return  boolean - true or false
     * @param paddle the paddle
     */
    public boolean paddleInBorders(Paddle paddle) {
        return ((paddleLocation.getX() + paddle.paddleWidth) < WINDOWS_WIDTH && paddleLocation.getX() > 0);
    }
}