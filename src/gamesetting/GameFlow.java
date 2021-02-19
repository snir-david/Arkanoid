//ID 205686538
package gamesetting;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import interfaces.LevelInformation;
import screens.EndScreen;
import screens.KeyPressStoppableAnimation;
import java.util.List;

/**
 * The type Game flow.
 */
public class GameFlow {
    //fields
    private Counter score;
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboard;
    private GUI gui;
    private boolean gameOver;

    /**
     * Instantiates a new Game flow.
     * @param ar the AnimationRunner
     * @param ks the KeyboardSensor
     * @param gu the game GUI
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gu) {
        this.score = new Counter();
        this.animationRunner = ar;
        this.keyboard = ks;
        this.gui = gu;
    }

    /**
     * Run levels - according to the List levels.
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        //running every level in the list
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, score, this.keyboard, this.animationRunner, this.gui);
            level.initialize();
            while (!level.shouldStop()) {
                level.run();
            }
            //of no more balls
            if (level.getNumberOfBalls() == 0) {
                gameOver = true;
                break;
            }
        }
        //checking if "space" is pressed for closing the end screen
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboard, "space",
                new EndScreen(keyboard, score, gameOver)));
        gui.close();
    }
}