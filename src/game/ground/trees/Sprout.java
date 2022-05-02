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
    private static final char DISPLAY_CHAR = '+';
    private static final int TURNS_TO_GROW = 10;
    private static final int SPAWN_CHANCE = 10;  // Chance to spawn Goomba
    private static final int JUMP_CHANCE = 90;
    private static final int FALL_DAMAGE = 10;

    /**
     * Sprout constructor
     */
    public Sprout() {
        super(DISPLAY_CHAR, TURNS_TO_GROW, JUMP_CHANCE, FALL_DAMAGE);
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
        if((location.getActor() == null) && (Utils.nextChance() <= SPAWN_CHANCE)) {
            location.addActor(new Goomba());
        }
    }
}
