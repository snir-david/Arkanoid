//Id205686538
package gamesetting;
import geometry.Line;
import geometry.Point;
import interfaces.Collidable;
import sprites.Paddle;

import java.util.ArrayList;
import java.util.List;

/**
 * The Collision info.
 * checking the collision and finding the object and the point of the collision
 * @param <closestObject> the type parameter
 */
public class CollisionInfo<closestObject> {
    //field
    private Collidable c;
    private List<Collidable> collidables;
    private Line line;

    /**
     *Instantiates a new Collision info.
     *constructor
     * @param l      the line we will check the collision with
     * @param collid the collidable list
     */
//constructor
    public CollisionInfo(Line l, List<Collidable> collid) {
        this.line = l;
        this.collidables = collid;
    }

    /**
     * Collision point.
     *checking which point is the collision point if there is any.
     * @return closestCollsionPoint the point of the collision
     */
// the point at which the collision occurs.
    public Point collisionPoint() {
        List<Collidable> collisionObjects = new ArrayList<>();
        List<Point> collisionPoint = new ArrayList<>();
        Point closestCollsionPoint = null;
        //checking if there is any colldiables, if there are adding them to the lists
        isCollide(collisionObjects, collisionPoint);
        //if there is collidables points, find the closest point and object
        if (collisionPoint.size() != 0) {
            closestCollsionPoint = closestPoint(collisionObjects, collisionPoint);
        }
        return closestCollsionPoint;
    }
    /**
     * Is collide.
     *checking if objects are collide with this.line
     * @param collisionObjects the collision objects
     * @param collisionPoint   the collision points
     */
    public void isCollide(List<Collidable> collisionObjects, List<Point> collisionPoint) {
        Point collPoint;
        // for every collidables in collidables List
        for (Collidable coll: this.collidables) {
            if (coll instanceof Paddle) {
                collPoint = coll.getCollisionRectangle().getRectangleLine()[1].intersectionWith(line);
                if (collPoint != null) {
                    collisionObjects.add(coll); //adding collision object to a list
                    collisionPoint.add(collPoint); //adding collision point to a list
                }
            } else {
                // for every line in collidable block - check if intersect with ball trajectory
                for (Line l : coll.getCollisionRectangle().getRectangleLine()) {
                    collPoint = l.intersectionWith(line);
                    if (collPoint != null) {
                        collisionObjects.add(coll); //adding collision object to a list
                        collisionPoint.add(collPoint); //adding collision point to a list
                    }
                }
            }
        }
    }

    /**
     * Closest point point.
     *checking from the list of the collision point which is the closest to the start of the line
     * @param collisionObjects the collision objects
     * @param collisionPoint   the collision point
     * @return the point
     */
    public Point closestPoint(List<Collidable> collisionObjects, List<Point> collisionPoint) {
        Collidable coll;
        Point closestCollsionPoint;
        int objectIdCounter = 0;
        //setting the first collidable and point in the list into the variables
        closestCollsionPoint = collisionPoint.get(0);
        coll = collisionObjects.get(0);
        //checking which collidables is the closest by checking the distance between collision point
            // and the point the ball will move to (trajectory)
            for (Point collsPt : collisionPoint) {
                if (line.start().distance(collsPt) <= line.start().distance(closestCollsionPoint)) {
                    closestCollsionPoint = collsPt;
                    coll = collisionObjects.get(objectIdCounter);
                }
                objectIdCounter++; //counter for finding collidable ID in the list
            }
        this.c = coll;
        return closestCollsionPoint;
    }

    /**
     * Collision object collidable.
     *getting the collidable object
     * @return the collidable
     */
// the collidable object involved in the collision.
    public Collidable collisionObject() {
        return this.c;
    }
}
