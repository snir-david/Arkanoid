//ID 205686538
package gamesetting;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Animation;
import interfaces.Collidable;
import interfaces.HitListener;
import interfaces.HitNotifier;
import interfaces.Sprite;
import interfaces.LevelInformation;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.ScoreIndicator;
import screens.CountdownAnimation;
import screens.KeyPressStoppableAnimation;
import screens.PauseScreen;
import sprites.Ball;
import sprites.Block;
import sprites.Paddle;
import sprites.ScoreTrackingListener;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type game_setting.Game.
 */
public class GameLevel implements HitNotifier, Animation {
    //fields
    private int exit;
    private List<Ball> ballsArr;
    private List<HitListener> hitListeners;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private LevelInformation levelInfo;
    private GUI gui;
    private Paddle paddle;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter score;
    private ScoreIndicator scoreIndicator;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;

    /**
     * Instantiates a new game_setting.Game.
     * constructors
     */
    public GameLevel() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Instantiates a new Game level.
     * @param levelInformation the level information
     * @param sc               the score counter
     * @param ks               the KeyboardSensor
     * @param ar               the AnimationRunner
     * @param gu               the game GUI
     */
    public GameLevel(LevelInformation levelInformation, Counter sc, KeyboardSensor ks, AnimationRunner ar, GUI gu) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.hitListeners = new ArrayList<>();
        this.levelInfo = levelInformation;
        this.score = sc;
        this.keyboard = ks;
        this.runner = ar;
        this.gui = gu;
    }

    /**
     * Add collidable.
     *adding collidable item to the game_setting.GameEnvironment list
     * @param c the interfaces.Collidable
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Add sprite.
     *adding sprite item to the SpritesCollection list
     * @param s the interfaces.Sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * game_setting.Game environment getter.
     * @param c the collidable that going to be removed
     */
    public void removeCollidable(Collidable c) {
        this.environment.getCollidables().remove(c);
    }

    /**
     * Remove sprite.
     * @param s the sprite that going to be removed
     */
    public void removeSprite(Sprite s) {
        this.sprites.getSprites().remove(s);
    }

    /**
     * game_setting.Game environment.
     * @return the game environment
     */
    public GameEnvironment gameEnvironment() {
        return this.environment;
    }

    /**
     * Gets sprites.
     * @return the sprites
     */
    public SpriteCollection getSprites() {
        return this.sprites;
    }

    /**
     * Gets gui.
     * @return the gui
     */
    public int getNumberOfBalls() {
        return this.remainingBalls.getValue();
    }
    /**
     * Gets Level Information.
     * @return the levelInfo
     */
    public LevelInformation getLevelInfo() {
        return this.levelInfo;
    }

    /**
     * Initialize the game.
     * Initialize a new game: create the Blocks and sprites.Ball (and sprites.Paddle)
     * and add them to the game.
     */
    public void initialize() {
        this.exit = 0; //exit using for shouldStop condition
        this.remainingBlocks = new Counter();
        this.remainingBalls = new Counter();
        this.keyboard = this.gui.getKeyboardSensor();
        //setting borders, blocks, balls and paddle  - and adding all to the game (sprite and collidable)
        addSprite(levelInfo.getBackground());
        setBlocks(levelInfo.blocks());
        setPaddle(gui);
        setBalls(levelInfo.numberOfBalls());
        setBorders();
        //initialize the scoreIndicator with the score game_setting.Counter
        this.scoreIndicator = new ScoreIndicator(score, this);
    }
    /**
     * Sets the balls.
     * @param numberOfBalls the number of balls
     */
    public void setBalls(int numberOfBalls) {
        List<Ball> ballsArray = new ArrayList<>();
        this.ballsArr = ballsArray;
        //setting the number of the balls that in the game
        for (int i = 0; i < numberOfBalls; i++) {
            Ball b = new Ball(levelInfo.ballStartPoints().get(i), 5, Color.WHITE);
            this.ballsArr.add(b);
            this.ballsArr.get(i).setVelocity(levelInfo.initialBallVelocities().get(i));
            this.ballsArr.get(i).addToGame(this);
            this.remainingBalls.increase(1);
        }
    }

    /**
     * Sets borders of the windows.
     */
    public void setBorders() {
        Block[] borders = new Block[4];
        //setting the Borders of the windows
        Rectangle rectangle1 = new Rectangle((new Point(0, 20)), WINDOW_WIDTH, 5);
        borders[0] = new Block(rectangle1, Color.DARK_GRAY);
        Rectangle rectangle2 = new Rectangle(new Point(0, 0), 5, WINDOW_HEIGHT);
        borders[1] = new Block(rectangle2, Color.DARK_GRAY);
        Rectangle rectangle3 = new Rectangle(new Point(WINDOW_WIDTH - 5, 0), 5, WINDOW_HEIGHT);
        borders[2] = new Block(rectangle3, java.awt.Color.DARK_GRAY);
        Rectangle rectangle4 = new Rectangle(new Point(0, WINDOW_HEIGHT), WINDOW_WIDTH, 100);
        borders[3] = new Block(rectangle4, java.awt.Color.DARK_GRAY);
        //adding the death-region to the ballRemover listener
        BallRemover br = new BallRemover(this, remainingBalls);
        this.addHitListener(br);
        borders[3].addHitListener(br);
        for (int i = 0; i < 4; i++) {
            borders[i].addToGame(this);
        }
    }

    /**
     * Sets blocks.
     * @param blockList getting the blocks in list and setting them up
     */
    public void setBlocks(List<Block> blockList) {
        for (Block b :blockList) {
            b.addToGame(this);
            remainingBlocks.increase(1);
            //adding block as Hit listeners
            BlockRemover hl = new BlockRemover(this, remainingBlocks);
            HitListener stl = new ScoreTrackingListener(score);
            this.addHitListener(hl);
            this.addHitListener(stl);
            b.addHitListener(hl);
            b.addHitListener(stl);
        }
    }

    /**
     * Sets paddle.
     *
     * @param g the game gui
     */
    public void setPaddle(GUI g) {
        //set the paddle and adding to the game
        this.paddle = new Paddle(g, levelInfo.paddleSpeed(), levelInfo.paddleWidth());
        this.paddle.addToGame(this);
    }
    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
       this.runner.run(new CountdownAnimation(3, 3, this.sprites));
       this.running = true;
       this.runner.run(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
            hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
            hitListeners.remove(hl);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        //changing the score
        scoreIndicator.scoreIndicatorSet(score);
        //draw all spirtes from list
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        //checking for "p" for puase the game
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, "space", new PauseScreen(this.keyboard)));
        }
        //increasing 100 when blocks are finished in the current level
        if (remainingBlocks.getValue() == 0 && exit == 0) {
            score.increase(100);
        }
        // if no more balls - Game Over
        if (remainingBalls.getValue() <= 0) {
            exit = 11;
        }
    }

    @Override
    public boolean shouldStop() {
        //increasing exit (giving the time to see last block disappears)
        if (remainingBlocks.getValue() == 0) {
            exit++;
        }
        //stopping when exit > 10 - giving enough time to see the last block disappearing
        if (exit > 10) {
             this.running = false;
             return true;
         } else {
             return false;
         }
    }
}