package game.items.equipments;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.ground.GroundCapabilities;
import game.status.Status;

import java.util.ArrayList;

/**
 * Equipment to set grounds on fire within user's 1 radius
 */
public class Molotov extends Equipment {

    public static final String NAME = "Molotov";
    public static final char DISPLAY_CHAR = ' ';
    public static final boolean PORTABLE = true;

    /***
     * Public constructor for this equipment
     */
    public Molotov() {
        super(NAME, DISPLAY_CHAR, PORTABLE, 10);
    }

    /**
     * set all ground within 1 radius of the user on fire
     * yes, player will take at least 1 turn of fire damage
     * @param actor the actor using this equipment
     * @param map the GameMap actor is on
     * @throws Exception if this equipment is on cool down
     */
    @Override
    public void onUse(Actor actor, GameMap map) throws Exception {
        super.onUse(actor, map);    // put the equipment on cool down

        // get the fire locations
        ArrayList<Location> locationsAffected = new ArrayList<>();
        for (Exit exit : map.locationOf(actor).getExits())
            locationsAffected.add(exit.getDestination());

        // set the locations on fire, if they are not frozen
        for (Location location : locationsAffected) {
            if (!location.getGround().hasCapability(GroundCapabilities.FROZEN))
                location.getGround().addCapability(GroundCapabilities.ON_FIRE);
        }
    }

    @Override
    public String toString() {
        return NAME;
    }

}
