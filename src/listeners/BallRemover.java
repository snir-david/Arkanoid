//ID 205686538
package listeners;
import gamesetting.Counter;
import gamesetting.GameLevel;
import interfaces.HitListener;
import sprites.Ball;
import sprites.Block;

/**
 * The type sprites.Ball remover.
 */
public class BallRemover implements HitListener {
    //fields
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * Instantiates a new sprites.Ball remover.
     * @param game         the game
     * @param removedBalls the removed balls counter
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeHitListener(this);
        hitter.removeFromGame(game);
        remainingBalls.decrease(1);
    }
}
