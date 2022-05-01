package game.actors;

import edu.monash.fit2099.engine.actors.Actor;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.WanderBehaviour;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is an abstract class for the Enemy actors
 * @author James Huynh
 * @version 2.0
 */
public abstract class Enemy extends Actor{
    protected Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour

    /**
     *
     * @param name
     * @param displayChar
     * @param hitPoints
     */
    protected Enemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.behaviours.put(0, new AttackBehaviour());
        this.behaviours.put(10, new WanderBehaviour());

    }
}
