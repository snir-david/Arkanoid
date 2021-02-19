//ID 205686538
package screens;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gamesetting.Counter;
import interfaces.Animation;

/**
 * The type End screen.
 */
public class EndScreen implements Animation {
    //fields
    private Counter score;
    private KeyboardSensor keyboard;
    private boolean gameOver;
    private boolean stop;

    /**
     * Instantiates a new End screen.
     *
     * @param k  the KeyboardSensor
     * @param sc the score Counter
     * @param go the game over boolean (no more balls)
     */
//constructor
    public EndScreen(KeyboardSensor k, Counter sc, boolean go) {
        this.keyboard = k;
        this.score = sc;
        this.gameOver = go;
        this.stop = false;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        //if there is no more blocks
        if (!gameOver) {
            d.drawText(50, d.getHeight() / 2, "You Win! Your score is " + score.getValue(), 32);
        } else  { //if there is no more balls
            d.drawText(50, d.getHeight() / 2, "Game Over. Your score is " + score.getValue(), 32);
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
