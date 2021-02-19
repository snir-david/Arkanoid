//ID 205686538
package gamesetting;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import interfaces.Animation;

/**
 * The type Animation runner.
 */
public class AnimationRunner {
    //fields
    private GUI gui;
    private int framesPerSecond;

    /**
     * Instantiates a new Animation runner.
     * @param framesPS the frames ps
     * @param gameGui  the game gui
     */
    public AnimationRunner(int framesPS, GUI gameGui) {
        this.framesPerSecond = framesPS;
        this.gui = gameGui;
    }

    /**
     * Run the animation.
     * @param animation the animation
     */
    public void run(Animation animation) {
        Sleeper sleeper = new Sleeper();
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            //OneFrame
            animation.doOneFrame(d);
            gui.show(d);
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}