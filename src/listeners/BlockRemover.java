//ID 205686538
package listeners;

import gamesetting.Counter;
import gamesetting.GameLevel;
import interfaces.HitListener;
import sprites.Ball;
import sprites.Block;

/**
 * The type sprites.Block remover - a listeners.BlockRemover is in charge of removing blocks from the game,
 * as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    //fields
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * Instantiates a new sprites.Block remover.
     * @param game          the game
     * @param removedBlocks the removed blocks counter
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
            this.game = game;
            this.remainingBlocks = removedBlocks;
    }
    /**
     * The hitEvent - Blocks that are hit should be removed
     * from the game.
     * @param beingHit the block that hitted
     * @param hitter the ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        //removing the listener
        beingHit.removeHitListener(this);
        //removing the block
        beingHit.removeFromGame(game);
        //decreasing the counter
        remainingBlocks.decrease(1);
    }
}