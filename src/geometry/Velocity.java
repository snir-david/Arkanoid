// ID 205686538
package geometry;
/**
 * A geometry.Velocity class.
 * geometry.Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    //fields
    private double x, y;
    /**
     * constructor.
     * @param dx - the dx of the point
     * @param dy - the dy of the point
     */
    public Velocity(double dx, double dy) {
        this.x = dx;
        this.y = dy;
    }
    /**
     * constructor.
     * @param velocity - the velocity.
     */
    public Velocity(Velocity velocity) {
       this.x = velocity.getVelocitydx();
       this.y = velocity.getVelocitydy();
    }
    /**
     * Take a point with position (x,y) and return a new point
     * with position (x+dx, y+dy).
     * @param p - point that will change to the next point
     * @return geometry.Point - return a new point that the ball will advance to.
     */
    public Point applyToPoint(Point p) {
        Point newPoint = new Point((p.getX() + this.x), (p.getY() + this.y));
        return newPoint;
    }
    /**
     * Take a velocity with angle and speed, convert it to (dx,dy).
     * @param angle - the angle of that the ball will advance to
     * @param speed - the speed that the ball will advance in
     * @return geometry.Velocity - return the velocity in (dx,dy).
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dy = speed * (Math.cos(Math.toRadians(angle)));
        double dx = speed * (Math.sin(Math.toRadians(angle)));
        Velocity v = new Velocity(dx, dy);
        return v;

    }
    /**
     * geometry.Velocity setters.
     * @param velocity - velocity
     */
    public void setVelocity(Velocity velocity) {
        this.x = velocity.getVelocitydx();
        this.y = velocity.getVelocitydy();
    }
    /**
     * geometry.Velocity setters.
     *@param dx - dx velocity
     * @param dy - dy velocity
     */
    public void setVelocity(double dx, double dy) {
        this.x = dx;
        this.y = dy;
    }
    /**
     * geometry.Velocity getter.
     * @return the velocity dx.
     */
    public double getVelocitydx() {
        return this.x;
    }
    /**
     * geometry.Velocity getter.
     * @return the velocity dy.
     */
    public double getVelocitydy() {
        return this.y;
    }
}