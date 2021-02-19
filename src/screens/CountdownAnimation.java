//Id 205686538
package screens;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import gamesetting.SpriteCollection;
import interfaces.Animation;
import java.awt.Color;


//

/**
 * The type Countdown animation.
 *  The CountdownAnimation will display the given gameScreen,
 * for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will
 * appear on the screen for (numOfSeconds / countFrom) seconds, before
 * it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {
    //fields
    private double numberOfSec;
    private int counter;
    private int countingFrom;
    private SpriteCollection screenSprites;
    private boolean stop;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numberOfSec = numOfSeconds;
        this.countingFrom = countFrom;
        this.counter = countFrom + 1;
        this.screenSprites = gameScreen;
        this.stop = false;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        Sleeper sleeper = new Sleeper();
        this.screenSprites.drawAllOn(d);
        counter--;
        //setting the countdown
        if (counter > 0) {
            d.setColor(Color.ORANGE);
            d.drawText(380, 200, Integer.toString(counter), 50);
            if (counter < numberOfSec) {
                sleeper.sleepFor((countingFrom * 1000) / (long) numberOfSec);
            }
            } else  if (counter > -2) { //setting the "GO"
                d.setColor(Color.RED);
                d.drawText(380, 200, "GO", 50);
                sleeper.sleepFor((countingFrom * 1000) / (long) numberOfSec);
            } else {
                this.stop = true;
            }
    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}