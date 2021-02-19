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
 * The type Level 2.
 */
public class Level2 implements LevelInformation {
    //fields
    static final int NUMBER_OF_BALLS = 10;
    static final int BLOCK_WIDTH = 40;
    static final int BLOCK_HEIGHT = 20;
    static final int PADDLE_WIDTH = 500;
    static final int WINDOW_WIDTH = 800;
    static final int WINDOW_HEIGHT = 600;
    static final int PADDLE_SPEED = 15;
    private List<Velocity> velocityList = new ArrayList<>();
    private List<Block> blockList = new ArrayList<>();

    @Override
    public int numberOfBalls() {
        return NUMBER_OF_BALLS;
    }

    @Override
    public List<Point> ballStartPoints() {
        List<Point> bsp = new ArrayList<>();
        for (int i = 1; i <= NUMBER_OF_BALLS / 2; i++) {
                Point p = new Point(WINDOW_WIDTH / 3 + i * 20, WINDOW_HEIGHT / 2 - i * 20);
                bsp.add(p);
        }
        for (int i = 0; i < NUMBER_OF_BALLS / 2; i++) {
            Point p = new Point(WINDOW_WIDTH / 3 + 200 + i * 20, WINDOW_HEIGHT / 2 - 100 + i * 20);
            bsp.add(p);
        }
        return bsp;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        for (int i = 0; i < numberOfBalls(); i++) {
            Velocity v = new Velocity(5 , 5);
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
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        LevelBackground lb = new LevelBackground(Color.WHITE, levelName());
        return lb;
    }

    @Override
    public List<Block> blocks() {
        // create a random-number generator
        Random rand = new Random();
        Color oldColor;
        Color newBlockColor = null;
        //setting up 20 block - 10 pairs
        for (int i = 0; i < 20;) {
            do {
                //setting a random color
                float r = rand.nextFloat();
                float g = rand.nextFloat();
                float b = rand.nextFloat();
                oldColor = newBlockColor;
                newBlockColor = new Color(r, g, b);
            } while (oldColor == newBlockColor);
            //setting up a pair of blocks (of the same color)
            for (int j = 0; j < 2; j++, i++) {
                    Rectangle r = new Rectangle(new Point(i * BLOCK_WIDTH, WINDOW_HEIGHT / 4), BLOCK_WIDTH,
                            BLOCK_HEIGHT);
                    Block b = new Block(r, newBlockColor);
                    blockList.add(b);
            }
        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blockList.size();
    }
}
