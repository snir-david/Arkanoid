//Id205686538
package gamesetting;
/**
 * The type game_setting.Counter.
 */
public class Counter {
    //fields
    private Integer counter;
    /**
     * Instantiates a new game_setting.Counter.
     */
    public Counter() {
        counter = 0;
    }
    /**
     * Increase - add number to current count.
     * @param number the number to add
     */
    public void increase(int number) {
        counter += number;
    }
    /**
     * Decrease - subtract number from current count.
     * @param number the number to subtract
     */

    public void decrease(int number) {
        counter -= number;
    }
    /**
     * Gets value- get current count.
     * @return the value
     */
    public int getValue() {
        return counter;
    }
}