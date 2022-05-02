package game.ground.trees;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.Utils;
import game.ground.Dirt;
import game.ground.HighGround;
import game.reset.Resettable;
import game.status.Status;

/**
 * Tree is a Ground that grows after a set period of time and
 * has spawning behaviour each turn.
 * @author Matthew Siegenthaler
 * @version 1.0
 */
public abstract class Tree extends HighGround implements Resettable {
    private Integer growthCounter;

    /**
     * Tree constructor
     * @param displayChar Display character of tree.
     * @param growthCounter Number of turns tree grows for.
     * @throws IllegalArgumentException growthCounter must be > 0.
     */
    protected Tree(char displayChar, Integer growthCounter, Integer successThreshold, Integer fallDamage) throws IllegalArgumentException {
        super(displayChar, successThreshold, fallDamage);

        if (!(setGrowthCounter(growthCounter))) {
            throw new IllegalArgumentException("growthCounter must be between > 0");
        }
        registerResettable();
    }

    /* Setters */

    /**
     * Setter method for growth counter.
     * @param newGrowthCounter Number of turns (>0) Tree requires to grow for.
     * @return Boolean if growthCounter was set.
     */
    public Boolean setGrowthCounter(Integer newGrowthCounter) {
        if (newGrowthCounter > 0) {
            this.growthCounter = newGrowthCounter;
            return true;
        } else {
            return false;
        }
    }

    /* Methods */

    /**
     * Tree will reduce it growth counter, try to spawn, and grow if end of growth cycle.
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        // If reset, set Tree to dirt
        if (this.hasCapability(Status.RESET)){
            location.setGround(new Dirt());
        } else {
            // Grow tree
            this.growthCounter--;
            this.spawn(location);

            if (growthCounter == 0)
                this.grow(location);
        }
    }

    /**
     * Called once growth counter of Tree is 0.
     * @param location Location of the Tree.
     */
    public abstract void grow(Location location);

    /**
     * Tree spawning behaviour each turn
     * @param location Location of the Tree.
     */
    public abstract void spawn(Location location);

    /**
     * Actors cannot enter tree
     * @param actor the Actor to check
     * @return Boolean false (Actors can never enter)
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    /**
     * Tree block throw items
     * @return boolean true (always blocks)
     */
    @Override
    public boolean blocksThrownObjects() {
        return true;
    }

    /**
     * Tree has a 50% chance of turning to Dirt upon reset.
     */
    @Override
    public void resetInstance() {
        if (Utils.nextChance() <= 50) {
            this.addCapability(Status.RESET);
        }
    }
}
