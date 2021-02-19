//Id 205686538
package geometry;
import java.util.ArrayList;
import java.util.List;

/**
 * The type geometry.Rectangle.
 */
public class Rectangle {
    //fields
    private Point p;
    private double width, height;
    /**
     * Instantiates a new geometry.Rectangle.
     * @param upperLeft the upper left point
     * @param width     the width
     * @param height    the height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.p = upperLeft;
        this.height = height;
        this.width = width;
    }

    /**
     * Intersection points - list of intersection Points with the rectangle.
     * @param line the line
     * @return List<geometry.Point>
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> intersectionPoints = new ArrayList<Point>();
        Line[] rectLines;
        rectLines = this.getRectangleLine();
        //checking if the rectangle lines intersect with the line given
        for (Line l:rectLines) {
            if (l.intersectionWith(line) != null) {
                Point intersectionPoint = new Point(l.intersectionWith(line).getX(), l.intersectionWith(line).getY());
                intersectionPoints.add(intersectionPoint);
            }
        }
          return  intersectionPoints;
    }

    /**
     * Get width double.
     * @return the double
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Get height double.
     * @return the double
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Get upper left point.
     * @return the point
     */
    public Point getUpperLeft() {
        return this.p;
    }
    /**
     * get geometry.Rectangle 4 Points.
     * Returns the four corners of the rectangle
     * @return rectPoints geometry.Point[]
     */
    public  Point[] getRectanglePoints() {
        Point[] rectPoints = new Point[4];
        //upper left point
        rectPoints[0] = new Point(this.p.getX(), this.p.getY());
        //bottom left corner
        rectPoints[1] = new Point(this.p.getX(), this.p.getY() + this.height);
        //upper right point
        rectPoints[2] = new Point(this.p.getX() + this.width, this.p.getY());
        //bottom right corner
        rectPoints[3] = new Point(this.p.getX() + this.width, this.p.getY() + this.height);
        return rectPoints;
    }
    /**
     * Returns the four lines of the rectangle.
     * @return rectLines line [ ]
     */
    public Line[] getRectangleLine() {
        Line[] rectLines = new Line[4];
        //upper horizontal line
        rectLines[0] = new Line(this.getRectanglePoints()[0], this.getRectanglePoints()[1]);
        //left vertical line
        rectLines[1] = new Line(this.getRectanglePoints()[0], this.getRectanglePoints()[2]);
        //bottom horizontal line
        rectLines[2] = new Line(this.getRectanglePoints()[3], this.getRectanglePoints()[1]);
        //right vertical line
        rectLines[3] = new Line(this.getRectanglePoints()[3], this.getRectanglePoints()[2]);
        return rectLines;
    }
}
