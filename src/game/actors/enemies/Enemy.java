package game.actors.enemies;

import edu.monash.fit2099.engine.actors.Actor;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.WanderBehaviour;
import game.reset.Resettable;
import game.status.Status;

import java.util.Map;
import java.util.TreeMap;

/**
 * An abstract class representing the Enemy actors in the game.
 * @author Matthew Siegenthaler
 * @author James Huynh
 * @version 3.1
 */

public abstract class Enemy extends Actor implements Resettable {
    protected Map<Integer, Behaviour> behaviours = new TreeMap<>(); // priority, behaviour

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Enemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        registerResettable();  // Register as resettable
        this.behaviours.put(1, new AttackBehaviour());
        this.behaviours.put(10, new WanderBehaviour());
    }

    /**
     * Mark enemy for reset, hurt if implementation is drop items on death
     */
    @Override
    public void resetInstance() {
        addCapability(Status.RESET);
    }
}
