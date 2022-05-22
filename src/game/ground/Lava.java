package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.status.Status;

public class Lava extends Ground {
    // Constants
    private static final char DISPLAY_CHAR = 'L';
    private static final int TURN_DMG = 15;

    /**
     * Constructor.
     */
    public Lava() {
        super(DISPLAY_CHAR);
    }

    /**
     * Lava to do damage per turn when actor is on it.
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        Actor target = location.getActor();
        if (target != null && !target.hasCapability(Status.IMMUNITY) && !target.hasCapability(Status.DORMANT)) {
            target.hurt(TURN_DMG);
        }
    }

    /**
     * Enemies cannot enter
     * @param actor the Actor to check
     * @return boolean if Actor can enter.
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return !actor.hasCapability(Status.ENEMY);
    }
}
