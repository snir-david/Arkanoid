//ID 205686538
import biuoop.GUI;
import biuoop.KeyboardSensor;
import gamesetting.AnimationRunner;
import gamesetting.GameFlow;
import geometry.Point;
import geometry.Rectangle;
import interfaces.LevelInformation;
import levels.Level1;
import levels.Level2;
import levels.Level3;
import levels.Level4;
import sprites.Block;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Ass 6 game run the game with 4 levels as default or the level that called in the args.
 */
public class Ass6Game {
    //field
    private static final int FRAME_PER_SECOND = 60;
    static final int WINDOW_WIDTH = 800;
    static final int WINDOW_HEIGHT = 600;
    /**
     * main method call to the game, initialize and run it.
     * @param args the input arguments
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", WINDOW_WIDTH, WINDOW_HEIGHT);
        KeyboardSensor keyboard = gui.getKeyboardSensor();
        List<LevelInformation> levels = new ArrayList<>();
        AnimationRunner ar = new AnimationRunner(FRAME_PER_SECOND, gui);
        List<Integer> levelsNumbers = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            try {
                levelsNumbers.add(Integer.parseInt(args[i]));
            } catch (Exception e) {
                i++;
            }
        }
        for (Integer lv : levelsNumbers) {
            LevelInformation level;
            if (lv == 1) {
                level = new Level1();
                levels.add(level);
            } else if (lv == 2) {
                level = new Level2();
                levels.add(level);
            } else if (lv == 3) {
                level = new Level3();
                levels.add(level);
            } else if (lv == 4) {
                level = new Level4();
                levels.add(level);
            } else {
                continue;
            }
        }
        if (levelsNumbers.size() == 0) {
            LevelInformation lv1 = new Level1();
            LevelInformation lv2 = new Level2();
            LevelInformation lv3 = new Level3();
            LevelInformation lv4 = new Level4();
            levels.addAll(Arrays.asList(lv1, lv2, lv3, lv4));
        }
        GameFlow gm = new GameFlow(ar, keyboard, gui);
        gm.runLevels(levels);
    }

}
