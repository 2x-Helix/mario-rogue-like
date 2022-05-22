package game.items.equipments;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.ground.GroundCapabilities;
import game.status.Status;

import java.util.ArrayList;

/**
 * Equipment to freeze grounds within user's 1 radius
 */
public class Cryotov extends Equipment {

    public static final String NAME = "Cryotov";
    public static final char DISPLAY_CHAR = ' ';
    public static final boolean PORTABLE = true;

    /***
     * Public constructor for this equipment
     */
    public Cryotov() {
        super(NAME, DISPLAY_CHAR, PORTABLE, 10);
    }

    /**
     * Freeze all ground within 1 radius of the user, including the ground user is standing on
     * @param actor the actor using this equipment
     * @param map the GameMap actor is on
     * @throws Exception if this equipment is on cool down
     */
    @Override
    public void onUse(Actor actor, GameMap map) throws Exception {
        super.onUse(actor, map);    // put the equipment on cool down

        // get the freezing locations
        ArrayList<Location> locationsAffected = new ArrayList<>();
        locationsAffected.add(map.locationOf(actor));
        for (Exit exit : map.locationOf(actor).getExits())
            locationsAffected.add(exit.getDestination());

        // freeze the locations;
        for (Location location : locationsAffected) {
            location.getGround().addCapability(GroundCapabilities.FROZEN);
            location.getGround().removeCapability(GroundCapabilities.ON_FIRE);
        }
    }

    @Override
    public String toString() {
        return NAME;
    }

}
