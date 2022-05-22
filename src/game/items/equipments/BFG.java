package game.items.equipments;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.Enemy;
import game.status.Status;

import java.util.ArrayList;

/**
 * Equipment to eliminate all enemies within actor's 1 unit "radius"
 */
public class BFG extends Equipment {

    public static final String NAME = "BFG";
    public static final char DISPLAY_CHAR = ' ';
    public static final boolean PORTABLE = true;

    /***
     * Public constructor for this equipment
     */
    public BFG() {
        super(NAME, DISPLAY_CHAR, PORTABLE, 10);
    }

    /**
     * Kills all enemies within 1 radius of the user
     * @param actor the actor using this equipment
     * @param map the GameMap actor is on
     * @throws Exception if this equipment is on cool down
     */
    @Override
    public void onUse(Actor actor, GameMap map) throws Exception {
        super.onUse(actor, map);    // put the equipment on cool down

        // get the killing locations
        ArrayList<Location> locationsAffected = new ArrayList<>();
        for (Exit exit : map.locationOf(actor).getExits())
            locationsAffected.add(exit.getDestination());

        // TODO: killed enemies *should* drops items, but we can just say it doesnt and dont do it
        // kills the enemies
        Actor actorToBeKilled;
        for (Location location : locationsAffected) {
            actorToBeKilled = map.getActorAt(location);
            if (actorToBeKilled instanceof Enemy)
                map.removeActor(actorToBeKilled);
        }
    }

    @Override
    public String toString() {
        return NAME;
    }

}
