//ID 205686538
package levels;
import gamesetting.GameLevel;
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
import java.util.Random;

/**
 * The type Level 3.
 */
public class Level3 extends GameLevel implements LevelInformation {
    //fields
    static final int NUMBER_OF_BALLS = 2;
    static final int BLOCK_WIDTH = 40;
    static final int BLOCK_HEIGHT = 15;
    static final int PADDLE_WIDTH = 100;
    static final int WINDOW_WIDTH = 800;
    static final int WINDOW_HEIGHT = 600;
    static final int PADDLE_SPEED = 12;
    private List<Velocity> velocityList = new ArrayList<>();
    private List<Block> blockList = new ArrayList<>();

    @Override
    public int numberOfBalls() {
        return NUMBER_OF_BALLS;
    }
    /**
     * The ball Start Points.
     * @return  List of points where the balls starts
     */
    public List<Point> ballStartPoints() {
        List<Point> bsp = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_BALLS; i++) {
            Point p = new Point(WINDOW_WIDTH / 2 - 100 + i * 200, WINDOW_WIDTH / 2);
            bsp.add(p);
        }
        return bsp;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        for (int i = 0; i < NUMBER_OF_BALLS; i++) {
            Velocity v = new Velocity(-6, -7 + i * 2);
            velocityList.add(v);
        }
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
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        Color c = new Color(0, 102, 0);
        LevelBackground lb = new LevelBackground(c, levelName());
        return lb;
    }
    @Override
    public List<Block> blocks() {
        // create a random-number generator
        Random rand = new Random();
        Color oldColor;
        Color newBlockColor = null;
        //setting the blocks in the game
        //setting the rows
        for (int i = 0, counter = 0; i < 5; i++) {
            Point firstBlockPoint = new Point(754, 100 + i * BLOCK_HEIGHT);
            do { //setting a random color
                float r = rand.nextFloat();
                float g = rand.nextFloat();
                float b = rand.nextFloat();
                oldColor = newBlockColor;
                newBlockColor = new Color(r, g, b);
            } while (oldColor == newBlockColor);
            //setting the columns
            for (int j = 0; j < 10 - i; j++) {
                geometry.Rectangle r = new Rectangle(new Point(firstBlockPoint.getX(), firstBlockPoint.getY()),
                        BLOCK_WIDTH, BLOCK_HEIGHT);
                Block b = new Block(r, newBlockColor);
                blockList.add(b);
                firstBlockPoint.changePoint(-BLOCK_WIDTH, 0);
            }
        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blockList.size();
    }
}
