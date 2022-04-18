package game.ground.trees;

import edu.monash.fit2099.engine.positions.Location;
import game.Utils;
import game.actors.Goomba;

/**
 * Sprout has 10% chance to spawn Goombas and takes 10 turns to grow.
 * @author Matthew Siegenthaler
 * @version 1.0
 */
public final class Sprout extends Tree {
    public Sprout() {
        super('+', 10);
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
     * @return
     */
    @Override
    public boolean spawn(Location location) {
        if((location.getActor() == null) && (Utils.nextChance() <= 10)) {
            location.addActor(new Goomba());
            return true;
        } else {
            return false;
        }
    }
}
