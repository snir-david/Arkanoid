//ID 205686538
package levels;
import gamesetting.LevelBackground;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import interfaces.LevelInformation;
import interfaces.Sprite;
import sprites.Block;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level 1.
 */
public class Level1 implements LevelInformation {
    //fields
    static final int NUMBER_OF_BALLS = 1;
    static final int BLOCK_WIDTH = 40;
    static final int BLOCK_HEIGHT = 40;
    static final int PADDLE_WIDTH = 85;
    static final int WINDOW_WIDTH = 800;
    static final int WINDOW_HEIGHT = 600;
    static final int PADDLE_SPEED = 10;
    private List<Velocity> velocityList = new ArrayList<>();
    private List<Block> blockList = new ArrayList<>();

    @Override
    public int numberOfBalls() {
        return NUMBER_OF_BALLS;
    }

    @Override
    public List<Point> ballStartPoints() {
        List<Point> bsp = new ArrayList<>();
        Point p = new Point(WINDOW_WIDTH / 2, WINDOW_HEIGHT - 200);
        bsp.add(p);
        return bsp;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        Velocity v = new Velocity(0, 10);
        velocityList.add(v);
        return velocityList;
    }

    @Override
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        LevelBackground lb = new LevelBackground(Color.BLACK, levelName());
        return lb;
    }

    @Override
    public List<Block> blocks() {
        geometry.Rectangle r = new Rectangle(new Point(380, 200), BLOCK_WIDTH, BLOCK_HEIGHT);
        Block b = new Block(r, Color.RED);
        blockList.add(b);
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blockList.size();
    }
}
