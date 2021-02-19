// ID 205686538
package geometry;
import java.util.List;
/**
 * A geometry.Line class.
 * The class support - setting a new line, checking line length, middle point of a line
 * if two lines are equal, if they intersect and find the Intersection point.
 */
public class Line {
    //fields
    private Point startPoint, endPoint;
    /**
     * constructor.
     * @param start - start point of the line
     * @param end - end point of the line
     */
    public Line(Point start, Point end) {
        this.startPoint = start;
        this.endPoint = end;
    }
    /**
     * constructor.
     * @param x1 - x of the start point of the line
     * @param y1 - y of the start point of the line
     * @param x2 - x of the end point of the line
     * @param y2 - y of the end point of the line
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.startPoint = new Point(x1, y1);
        this.endPoint = new Point(x2, y2);
    }
    /**
     * Return the length of the line.
     * @return double.
     */
    //
    public double length() {
        return this.startPoint.distance(this.endPoint);
    }
    /**
     * Returns the middle point of the line.
     * @return the middle point of the line.
     */
    public Point middle() {
        double midX = (this.startPoint.getX() + this.endPoint.getX()) / 2;
        double midY = (this.startPoint.getY() + this.endPoint.getY()) / 2;
        Point midPoint = new Point(midX, midY);
        return midPoint;
    }
    /**
     * Returns the start point of the line.
     * @return geometry.Point.
     */
    public Point start() {
        return this.startPoint;
    }
    /**
     * Returns the end point of the line.
     * @return geometry.Point.
     */
    public Point end() {
        return this.endPoint;
    }
    /**
     * Given three collinear points p, q, r, the function checks if
     * point 'q' lies on line segment 'pr'.
     * works only if thre point are collinear must check orientation before using this method
     *@param p - a geometry.Point
     *@param q - a geometry.Point
     *@param r - a geometry.Point
     * @return boolean - true or false if the point 'q' on the line 'pr'.
     */
    public static boolean onSegment(Point p, Point q, Point r) {
        return (q.getX() <= Math.max(p.getX(), r.getX())
                && q.getX() >= Math.min(p.getX(), r.getX())
                && q.getY() <= Math.max(p.getY(), r.getY())
                && q.getY() >= Math.min(p.getY(), r.getY()));
    }
    /**
     * To find orientation of ordered triplet (p, q, r)
     * getting 3 points and checking slopes - depends on result decide what is
     * orientation of the three points.
     *@param p - a geometry.Point
     *@param q - a geometry.Point
     *@param r - a geometry.Point
     *@return int - return 0 if the points are collinear, 1 if the points are clockwise,
     * 2 if the points are counterclockwise.
     */
    //
    public static int orientation(Point p, Point q, Point r) {
        //using slope equation m = (y1-y2)/(x1-x2) to check slopes
        //checking if 'pq' slope is bigger/smaller/equal to 'qr' slope
        double val = (q.getY() - p.getY()) * (r.getX() - q.getX())
                - (r.getY() - q.getY()) * (q.getX() - p.getX());

        if (val == 0) {
            return 0; // slopes are equals and the points are collinear
        } else  {
            return (val > 0) ? 1 : 2;  // slopes are different clock or counterclock wise
        }
    }
    /**
     * checking if two line intersect by using orientations of the points and checking
     * slopes of the lines.
     * General case - o1,o2 checking  our line orientations with  other line start and end points
     * o3,o4 checking other line orientations with our line start and end points
     * o1!=o2 and o3!=o4 it means that the lines are intersect
     * Special cases - checking if the lines intersect in space (not only in The X axis and the Y axis),
     * by checking if one of the points is on the other line segment.
     *@param other  - a line.
     *@return boolean - return true if the lines are intersect or false if they don't
     */
    public boolean isIntersecting(Line other) {
        // Find the four orientations needed for general and special cases
        // Find the orientations of 'this.line' with 'other.startPoint' and 'other.endPoint'
        int o1 = orientation(this.startPoint, this.endPoint, other.startPoint);
        int o2 = orientation(this.startPoint, this.endPoint, other.endPoint);
        // Find the orientations of other.line with this.startPoint and this.endPoint
        int o3 = orientation(other.startPoint, other.endPoint, this.startPoint);
        int o4 = orientation(other.startPoint, other.endPoint, this.endPoint);

        // General case
        if (o1 != o2 && o3 != o4) {
            return true;
        }
        // Special Cases
        // 'this.startPoint', 'other.startPoint' and 'this.endPoint' are collinear and
        // 'this.endPoint' lies on segment 'this.startPoint'-'other.startPoint'
        if (o1 == 0 && onSegment(this.startPoint, other.startPoint, this.endPoint)) {
            return true;
        }

        // 'this.'startPoint', 'this.endPoint' and 'other.endPoint' are collinear and
        // 'other.endPoint' lies on segment 'this.startPoint'-'this.endPoint'
        if (o2 == 0 && onSegment(this.startPoint, other.endPoint, this.endPoint)) {
            return true;
        }
        // 'this.endPoint', 'other.endPoint' and 'this.startPoint' are collinear and
        // this.startPoint lies on segment 'this.endPoint'-'other.endPoint'
        if (o3 == 0 && onSegment(other.startPoint, this.startPoint, other.endPoint)) {
            return true;
        }
        // this.endPoint, other.endPoint and other.startPoint are collinear and
        // 'other.startPoint' lies on segment t'his.endPoint'-'other.endPoint'
        // Doesn't fall in any of the above cases
        return (o4 == 0 && onSegment(other.startPoint, this.endPoint, other.endPoint));
    }
    /**
     *Returns the intersection point if the lines intersect, and null otherwise
     * First checking if the lines are intersect by using isIntersecting method
     * if they do intersect using slope equation and y=m*x +c equation to find the
     * intersect geometry.Point.
     *@param other  - a line.
     *@return geometry.Point - return the intersect geometry.Point if it exist
     */
    //
    public Point intersectionWith(Line other) {
        double c1, c2 , x, y;
        Point p = null;
        if (this.isIntersecting(other)) {
            //finding slope of this line segment
            double m1 = (this.startPoint.getY() - this.endPoint.getY())
                    / ((this.startPoint.getX() - this.endPoint.getX()));
            //finding slope of other line segment
            double m2 = (other.startPoint.getY() - other.endPoint.getY())
                    / ((other.startPoint.getX() - other.endPoint.getX()));
            //checking if both slope are not parallel to the X axis (slope is divides by zero)
            if (Double.isFinite(m1) && Double.isFinite(m2)) {
                //finding the c1 in the equation in y=m*x+c
                c1 = this.startPoint.getY() - m1 * this.startPoint.getX();
                //finding the c2 in the equation in y=m*x+c
                c2 = other.startPoint.getY() - m2 * other.startPoint.getX();
                //finding the x in the intersection point
               x = (c2 - c1) / (m1 - m2);
                //finding the y in the intersection point
                y = m1 * x + c1;
                p = new Point(x, y);
            } else {
                // if one of the slopes are parallel to one of the axises
                //checking if m1 is parallel to X axis
                if (Double.isFinite(m1)) {
                    //finding the c1 in the equation in y=m*x+c
                    c1 = this.startPoint.getY() - m1 * this.startPoint.getX();
                    x = other.startPoint.getX();
                    y = m1 * x + c1;
                    p = new Point(x, y);
                }
                //checking if m2 is parallel to X axis
                if (Double.isFinite(m2)) {
                    //finding the c2 in the equation in y=m*x+c
                    c2 = other.startPoint.getY() - m2 * other.startPoint.getX();
                    x = this.startPoint.getX();
                    y = m2 * x + c2;
                    p = new Point(x, y);
                }
                //if both slopes are parallel to X axis
                if (Double.isInfinite(m1) && Double.isInfinite(m2)) {
                    //checking if they have intersection point
                    //checking if this.start point equal to other.start point
                    if (this.startPoint.equals(other.startPoint)) {
                        x = this.startPoint.getX();
                        y = this.startPoint.getY();
                        p = new Point(x, y);
                    }
                    //checking if this.end point equal to other.end point
                    if (this.endPoint.equals(other.endPoint)) {
                        x = this.startPoint.getX();
                        y = this.startPoint.getY();
                        p = new Point(x, y);
                    }
                    //checking if this.start point equal to other.end point
                    if (this.startPoint.equals(other.endPoint)) {
                        x = this.startPoint.getX();
                        y = this.startPoint.getY();
                        p = new Point(x, y);
                    }
                    //checking if this.end point equal to other.start point
                    if (this.endPoint.equals(other.startPoint)) {
                        x = this.endPoint.getX();
                        y = this.endPoint.getY();
                        p = new Point(x, y);
                    }
                }
            }
            //entering the (x,y) we found into new point
            return p;
        } else {
            return null;
        }
    }
    /**
     *equals -- return true is the lines are equal, false otherwise.
     *@param other  - a line.
     *@return boolean - return true if the lines are equal or false if they don't
     */
    //
    public boolean equals(Line other) {
        return (other.startPoint == this.startPoint && other.endPoint == this.endPoint);
    }

    /**
     * Closest intersection to start of line point.
     *If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     * @param rect the geometry.Rectangle
     * @return the point of itersection if there is any.
     */
//
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        Line line = new Line(this.startPoint, this.endPoint);
        List<Point> intersectionPoints = rect.intersectionPoints(line);
        if (intersectionPoints.get(0).distance(this.startPoint) > intersectionPoints.get(1).distance(this.startPoint)) {
            return intersectionPoints.get(1);
        }
        return intersectionPoints.get(0);
    }
    @Override
    /**
     *toString so we can print geometry.Line
     *@return void
     */
    public String toString() {
        return "geometry.Line{" + "startPoint=" + startPoint + ", endPoint=" + endPoint + '}';
    }
}