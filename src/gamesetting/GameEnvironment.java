//ID 205686538
package gamesetting;
import geometry.Line;
import interfaces.Collidable;
import java.util.ArrayList;
import java.util.List;

/**
 * The type game_setting.Game environment.
 */
public class GameEnvironment {
    //fields
    private List<Collidable> collidables;
    /**
     * Instantiates a new game_setting.Game environment.
     * constructors
     */
    public GameEnvironment() {
        List<Collidable> colli = new ArrayList<>();
        this.collidables = colli;
    }

    /**
     * Add collidable.
     *add the given collidable to the environment.
     * @param c the interfaces.Collidable object
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * Gets closest collision.
     *Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     * @param trajectory the trajectory
     * @return the closest collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        CollisionInfo collInfo = new CollisionInfo(trajectory, this.getCollidables());
        if (collInfo.collisionPoint() != null) {
            return collInfo;
        } else {
            return null;
        }
    }

    /**
     * Gets collidables.
     * @return the collidables List<interfaces.Collidable>
     */
    public List<Collidable> getCollidables() {
        return this.collidables;
    }
}
