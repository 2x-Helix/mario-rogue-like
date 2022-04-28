package game.ground.trees;

import edu.monash.fit2099.engine.positions.Location;
import game.Utils;
import game.actors.enemies.Goomba;

/**
 * Sprout has 10% chance to spawn Goombas and takes 10 turns to grow.
 * @author Matthew Siegenthaler
 * @author ChunKau Mok (Peter)
 * @version 2.0
 */
public final class Sprout extends Tree {
    /**
     * Sprout constructor
     */
    public Sprout() {
        super('+', 10, 90, 10);
    }

    /**
     * Sprout grows into Sapling
     * @param location Location of the Tree.
     */
    @Override
    public void grow(Location location) {
        location.setGround(new Sapling());
    }

    /**
     * 10% to spawn a Goomba on location every tick.
     * @param location Location of the Tree.
     */
    @Override
    public void spawn(Location location) {
        if((location.getActor() == null) && (Utils.nextChance() <= 10)) {
            location.addActor(new Goomba());
        }
    }
}
