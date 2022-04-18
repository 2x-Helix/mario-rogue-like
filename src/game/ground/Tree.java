package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Tree is a Ground that grows after a set period of time and
 * has spawning behaviour each turn.
 * @author Matthew Siegenthaler
 * @version 1.0
 */
public abstract class Tree extends Ground {
    private Integer growthCounter;

    /**
     * Constructor.
     *
     */
    public Tree(char displayChar, Integer growthCounter) {
        super(displayChar);

        if (!(setGrowthCounter(growthCounter))) {
            throw new IllegalArgumentException("growthCounter must be between > 0");
        }
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
        this.growthCounter--;
        this.spawn(location);

        if (growthCounter == 0)
            this.grow(location);
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
}
