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
import java.util.Random;

/**
 * The type Level 4.
 */
public class Level4 implements LevelInformation {
    //fields
    static final int NUMBER_OF_BALLS = 3;
    static final int BLOCK_WIDTH = 40;
    static final int BLOCK_HEIGHT = 15;
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
        //setting up 2 balls in the same line
        for (int i = 0; i < NUMBER_OF_BALLS - 1; i++) {
            Point p = new Point(WINDOW_WIDTH / 2 - 50 + i * 100, (2 * WINDOW_HEIGHT) / 3);
            bsp.add(p);
        }
        //setting up the third ball higher than the other 2
        Point p = new Point(WINDOW_WIDTH / 2, (2 * WINDOW_HEIGHT) / 3 - 75);
        bsp.add(p);
        return bsp;    }

    @Override
    public List<Velocity> initialBallVelocities() {
        for (int i = 0; i < NUMBER_OF_BALLS; i++) {
            Velocity v = new Velocity(-5 , -5 + i);
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
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        Color c = new Color(31, 190, 214);
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
        for (int i = 0, counter = 0; i < 8; i++) {
            Point startingPoint = new Point(754, 100 + i * BLOCK_HEIGHT);
            Point rowPoint = startingPoint;
            do { //setting a random color
                float r = rand.nextFloat();
                float g = rand.nextFloat();
                float b = rand.nextFloat();
                oldColor = newBlockColor;
                newBlockColor = new Color(r, g, b);
            } while (oldColor == newBlockColor);
            //setting the columns
            for (int j = 0; j < 20; j++) {
                geometry.Rectangle r = new Rectangle(new Point(rowPoint.getX(), rowPoint.getY()),
                        BLOCK_WIDTH, BLOCK_HEIGHT);
                Block b = new Block(r, newBlockColor);
                blockList.add(b);
                rowPoint.changePoint(-BLOCK_WIDTH, 0);
            }
        }

        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blockList.size();
    }
}
