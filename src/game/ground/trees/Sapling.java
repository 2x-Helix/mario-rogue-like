package game.ground.trees;

import edu.monash.fit2099.engine.positions.Location;
import game.Utils;
import game.items.Coin;

/**
 * Sapling has 10% chance to spawn a coin and takes 10 turns to grow.
 * @author Matthew Siegenthaler
 * @author ChunKau Mok (Peter)
 * @version 2.0
 */
public final class Sapling extends Tree {

    /**
     * Sapling constructor
     */
    public Sapling() {
        super('t', 10, 80, 20);
    }

    /**
     * Grows into Mature
     * @param location Location of the Tree.
     */
    @Override
    public void grow(Location location) {
        location.setGround(new Mature());
    }

    /**
     * Sapling has 10% chance to spawn $20 coin.
     * @param location Location of the Tree.
     */
    @Override
    public void spawn(Location location) {
        if (Utils.nextChance() <= 10) {
            location.addItem(new Coin(20));
        }
    }
}
