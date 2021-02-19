//ID 205686538
package gamesetting;
import biuoop.DrawSurface;
import interfaces.Sprite;
import java.awt.Color;

/**
 * The type Level background.
 */
public class LevelBackground implements Sprite {
    //fields
    private Color color;
    private String levelName;

    /**
     * Instantiates a new Level background.
     *
     * @param c the color
     * @param s the level Name
     */
    public LevelBackground(Color c, String s) {
        this.color = c;
        this.levelName = s;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.BLUE);
        switch (levelName) {
            case "Direct Hit":
            for (int i = 180; i > 50; i -= 50) {
                d.drawCircle(400, 220, i);
            }
            break;
            case "Wide Easy":
                d.setColor(Color.GREEN);
                d.fillRectangle(50, 500, 10, 100);
                d.fillRectangle(750, 500, 10, 100);
                //setting the first flower
                d.setColor(Color.PINK);
                for (int i = 0; i <= 1; i++) {
                    d.fillCircle(55 - i * 30, 410 + i * 30, 30);
                }
                d.fillCircle(85, 440, 30);
                d.fillCircle(55, 470, 30);
                d.setColor(Color.ORANGE);
                d.fillCircle(55, 440, 25);
                //setting the second flower
                d.setColor(Color.PINK);
                for (int i = 0; i <= 1; i++) {
                    d.fillCircle(755 - i * 30, 410 + i * 30, 30);
                }
                d.fillCircle(785, 440, 30);
                d.fillCircle(755, 470, 30);
                d.setColor(Color.ORANGE);
                d.fillCircle(755, 440, 25);
                //setting the sun
                d.setColor(Color.YELLOW);
                d.fillCircle(25, 25, 75);
                for (int i = 1; i < 15; i++) {
                    d.drawLine(25, 25, 125 - 10 * i, 125);
                }
                for (int i = 1; i < 20; i++) {
                    d.drawLine(25, 25, 125 + 10 * i, 125);
                }
                for (int i = 0; i < 25; i++) {
                    d.drawLine(25 + i, 25 - i, 125 + 10 * i , 125 - i * 10);
                }
                d.drawLine(25, 25, 125, 125);
                break;
            case "Green 3":
                //setting up the building
                d.setColor(Color.LIGHT_GRAY);
                d.fillRectangle(50, 250, 100, 350);
                d.setColor(Color.BLACK);
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 20; j++) {
                        d.drawLine(50 + i * 20 + j, 250, 50 + i * 20  + j, 600);
                    }
                }
                d.setColor(Color.DARK_GRAY);
                d.fillRectangle(90, 175, 20, 75);
                d.setColor(Color.GRAY);
                d.fillRectangle(95, 125, 10, 50);
                //setting the antenna
                d.setColor(Color.ORANGE);
                d.fillCircle(100, 115, 10);
                d.setColor(Color.BLUE);
                d.fillCircle(100, 115, 5);
                d.setColor(Color.BLACK);
                d.drawRectangle(90, 175, 20, 75);
                d.drawRectangle(95, 125, 10, 50);
                d.drawCircle(100, 115, 10);
                break;
            case "Final Four":
              //setting clouds
                d.setColor(Color.GRAY);
                for (int i = 0; i < 3; i++) {
                    d.fillCircle(100 + i * 30 , 450, 30);
                    d.fillCircle(700 + i * 30, 500, 30);
                }
                d.setColor(Color.DARK_GRAY);
                for (int i = 0; i < 3; i++) {
                    d.fillCircle(70 + i * 30 , 420, 30);
                    d.fillCircle(670 + i * 30, 470, 30);
                }
                d.setColor(Color.LIGHT_GRAY);
                for (int i = 0; i < 3; i++) {
                    d.fillCircle(40 + i * 30 , 390, 30);
                    d.fillCircle(640 + i * 30, 440, 30);
                }
                //setting the rain
                for (int i = 0; i < 4; i++) {
                    d.drawLine(50 + i * 30, 390 + i * 30, 80 + i * 30 , 600);
                    d.drawLine(650 + i * 30, 440 + i * 30, 680 + i * 30 , 600);
                }
                break;
            default: break;
        }
    }

    @Override
    public void timePassed() {
    }
}
