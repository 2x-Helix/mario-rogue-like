package game.ground.trees;

import edu.monash.fit2099.engine.positions.*;
import game.Utils;
import game.actors.Koopa;
import game.ground.Dirt;
import game.ground.GroundCapabilities;
import java.util.ArrayList;
import java.util.List;

/**
 * Mature tree has a 15% chance to spawn Koopa,
 * 20% chance to wither each turn,
 * and tries to grow a sprout every 5 turns.
 * @author Matthew Siegenthaler
 * @version 1.0
 */
public final class Mature extends Tree {
    /**
     * Mature constructor
     */
    public Mature() {
        super('T', 5);
    }

    /**
     * Calls the growth and spawn methods,
     * Mature has a 20% chance to wither to dirt.
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);               // Calls grow() and spawn()
        if (Utils.nextChance() <= 20) {     // Chance to wither to dirt
            location.setGround(new Dirt());
        }
    }

    /**
     * Every 5 turns try to grow a Sprout
     * @param location Location of the Tree.
     */
    @Override
    public void grow(Location location) {
        // Find all adjacent fertile locations
        List<Location> fertileLocations = new ArrayList<Location>();
        for (Exit exit : location.getExits()) {
            Location destination = exit.getDestination();
            if (destination.getGround().hasCapability(GroundCapabilities.FERTILE)) {
                fertileLocations.add(destination);
            }
        }
        // Select fertile tile to be set as sprout
        if (fertileLocations.size() > 0) {
            int randIndex = Utils.nextInt(0, fertileLocations.size());
            Location sproutLocation = fertileLocations.get(randIndex);
            sproutLocation.setGround(new Sprout());
        }
        // Reset growthCounter
        setGrowthCounter(5);
    }

    /**
     * Mature has a 15% chance to spawn a Koopa.
     * @param location Location of the Tree.
     */
    @Override
    public void spawn(Location location) {
        if ((location.getActor() == null) && (Utils.nextChance() <= 15)) {
            location.addActor(new Koopa());
        }
    }
}
