package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Utils;
import game.actions.AttackAction;
import game.actions.SuicideAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.DrinkBehaviour;
import game.behaviours.WanderBehaviour;
import game.reset.Resettable;
import game.status.Status;

import java.util.HashMap;
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
        this.behaviours.put(3, new DrinkBehaviour());
        this.behaviours.put(10, new WanderBehaviour());
        this.addCapability(Status.ENEMY);  // Specifies as enemy
    }

    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // If marked for reset, remove from map,
        if (hasCapability(Status.RESET)){
            return new SuicideAction();
        } else {
            return new DoNothingAction();
        }
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);
        // Allow player to attack enemy
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }

    /**
     * Mark enemy for reset, hurt if implementation is drop items on death
     */
    @Override
    public void resetInstance() {
        addCapability(Status.RESET);
    }
}
