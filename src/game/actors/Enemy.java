package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is an abstract class for the Enemy actors
 * @author James Huynh
 * @version 1.0
 */
public abstract class Enemy extends Actor{
    private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour

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

    /**
     * Figure out what to do next.
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // checks if lastAction was AttackAction
        if(lastAction instanceof AttackAction) {
            // check if behaviours contains AttackBehaviour already
            if(!behaviours.containsKey(1)) {
                // adds FollowBehaviour, targeting the actor the current actor called AttackAction on
                this.behaviours.put(1, new FollowBehaviour(((AttackAction) lastAction).getTarget()));
            }
        }

        for(Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList list = super.allowableActions(otherActor, direction, map);
        list.add(new AttackAction(this, direction));
        // iterate through list
        for (Action action : list) {
            // checks if action is an AttackAction
            if(action instanceof AttackAction) {
                // check if behaviours list contains FollowBehaviour
                if(!behaviours.containsKey(1)) {
                    // adds FollowBehaviour, targeting the target Actor
                    behaviours.put(1, new FollowBehaviour(otherActor));
                }
            }
        }
        return list;
    }
}
