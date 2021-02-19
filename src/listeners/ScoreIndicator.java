//ID 205686538
package listeners;
import biuoop.DrawSurface;
import gamesetting.Counter;
import gamesetting.GameLevel;
import interfaces.Sprite;
import java.awt.Color;

/**
 * The type Score indicator.
 */
public class ScoreIndicator implements Sprite {
    private Counter currentScore;
    private GameLevel game;

    /**
     * Instantiates a new Score indicator.
     * @param score the score
     * @param g     the game
     */
    public ScoreIndicator(Counter score, GameLevel g) {
        this.currentScore = score;
        this.game = g;
        g.addSprite(this);
    }
    /**
     * Score indicator set - choose the number to set the indicator.
     * @param score the score
     */
    public void scoreIndicatorSet(Counter score) {
        this.currentScore = score;
    }

    /**
     * drawOn - drawing the current score on the screen.
     * @param d the DrawSurface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.LIGHT_GRAY);
        d.fillRectangle(0, 0, 800, 20);
        d.setColor(Color.BLACK);
        d.drawText(350, 17 , "Score:" + " " + currentScore.getValue(), 20);
        d.drawText(550, 17, "Level Name: " + game.getLevelInfo().levelName(), 20);
        d.drawText(25, 17, "Remaining Balls: " + game.getNumberOfBalls(), 20);
    }
    @Override
    public void timePassed() {

    }
}
