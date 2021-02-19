//ID 205686538
package interfaces;
import geometry.Point;
import geometry.Velocity;
import sprites.Block;
import java.util.List;

/**
 * The interface Level information.
 */
public interface LevelInformation {
    /**
     * Number of balls .
     *
     * @return the int
     */
    int numberOfBalls();
    /**
     * Ball start points list.
     *The initial velocity of each ball
     * Note that initialBallVelocities().size() == numberOfBalls()
     * @return the list
     */
    List<Point> ballStartPoints();
    /**
     * Initial ball velocities list.
     *
     * @return the list
     */
    List<Velocity> initialBallVelocities();
    /**
     * Paddle speed .
     *
     * @return int
     */
    int paddleSpeed();

    /**
     * Paddle width.
     *
     * @return int
     */
    int paddleWidth();

    /**
     * Level name string.
     *the level name will be displayed at the top of the screen.
     * @return the string
     */

    String levelName();

    /**
     * Gets background.
     *Returns a sprite with the background of the level
     * @return the background
     */

    Sprite getBackground();

    /**
     * Blocks list.
     *The Blocks that make up this level, each block contains
     * its size, color and location.
     * @return the list
     */
    List<Block> blocks();

    /**
     * Number of blocks to remove.
     *Number of blocks that should be removed
     * before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     * @return the int
     */
    int numberOfBlocksToRemove();
}