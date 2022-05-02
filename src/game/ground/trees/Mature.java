package game.ground.trees;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.Utils;
import game.actors.enemies.Koopa;
import game.ground.Dirt;
import game.ground.GroundCapabilities;

/**
 * Mature tree has a 15% chance to spawn Koopa,
 * 20% chance to wither each turn,
 * and tries to grow a sprout every 5 turns.
 * @author Matthew Siegenthaler
 * @author ChunKau Mok (Peter)
 * @version 1.0
 */
public final class Mature extends Tree {

    private static final char DISPLAY_CHAR = 'T';
    private static final int TURNS_TO_GROW_SPROUT = 5;
    private static final int SPAWN_CHANCE = 15;  // Chance to spawn Koopa
    private static final int WITHER_CHANCE = 20;
    private static final int JUMP_CHANCE = 70;
    private static final int FALL_DAMAGE = 30;

    /**
     * Mature constructor
     */
    public Mature() {
        super(DISPLAY_CHAR, TURNS_TO_GROW_SPROUT, JUMP_CHANCE, FALL_DAMAGE);
    }

    /**
     * Calls the growth and spawn methods,
     * Mature has a 20% chance to wither to dirt.
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);               // Calls grow() and spawn()
        if (Utils.nextChance() <= WITHER_CHANCE) {     // Chance to wither to dirt
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
        List<Location> fertileLocations = new ArrayList<>();
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
        setGrowthCounter(TURNS_TO_GROW_SPROUT);
    }

    /**
     * Mature has a 15% chance to spawn a Koopa.
     * @param location Location of the Tree.
     */
    @Override
    public void spawn(Location location) {
        if ((location.getActor() == null) && (Utils.nextChance() <= SPAWN_CHANCE)) {
            location.addActor(new Koopa());
        }
    }
}
