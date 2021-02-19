// ID 205686538
package geometry;

/**
 * A geometry.Point class.
 * The class support - setting a new geometry.Point, checking distance between two points,
 * checking if two points are equal.
 */
public class Point {
    //fields
    private double x;
    private double y;

    /**
     * constructor.
     *
     * @param ptX - the x of the point
     * @param ptY - the y of the point
     */
    public Point(double ptX, double ptY) {
        this.x = ptX;
        this.y = ptY;
    }

    /**
     * constructor - default geometry.Point.
     */
    public Point() {
        this.x = -1;
        this.y = -1;
    }

    /**
     * distance -- return the distance of this point to the other point.
     *
     * @param other get a geometry.Point .
     * @return double - return the total distance in double.
     */
    //
    public double distance(Point other) {
        double xDistance = Math.pow((this.x - other.x), 2);
        double yDistance = Math.pow((this.y - other.y), 2);
        return Math.sqrt(xDistance + yDistance);
    }

    /**
     * equals -- return true is the points are equal, false otherwise.
     *
     * @param other get a geometry.Point .
     * @return boolean - return true or false.
     */
    public boolean equals(Point other) {
        return (other.x == this.x && other.y == this.y);

    }

    /**
     * Return the x and y values of this point.
     *
     * @return x.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Return the y and y values of this point.
     *
     * @return y.
     */
    public double getY() {
        return this.y;
    }

    /**
     * changePoint - change the point.
     *
     * @param ptX - the change in x
     * @param ptY - the change in y
     */
    public void changePoint(double ptX, double ptY) {
        this.x = this.getX() + ptX;
        this.y = this.getY() + ptY;
    }

    @Override
    /**
     * toString so we can print geometry.Point.
     * @return void.
     */
    public String toString() {
        return "geometry.Point{" + "x=" + x + ", y=" + y + '}';
    }

}