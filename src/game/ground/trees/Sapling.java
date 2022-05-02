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

    private static final char DISPLAY_CHAR = 't';
    private static final int TURNS_TO_GROW = 10;
    private static final int SPAWN_CHANCE = 10;  // Chance to spawn Coin
    private static final int JUMP_CHANCE = 80;
    private static final int FALL_DAMAGE = 20;

    /**
     * Sapling constructor
     */
    public Sapling() {
        super(DISPLAY_CHAR, TURNS_TO_GROW, JUMP_CHANCE, FALL_DAMAGE);
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
        if (Utils.nextChance() <= SPAWN_CHANCE) {
            location.addItem(new Coin(20));
        }
    }
}
