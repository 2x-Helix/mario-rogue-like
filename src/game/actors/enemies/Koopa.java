package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.AttackAction;
import game.actions.SmashShellAction;
import game.actions.SuicideAction;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.items.weapon_items.Wrench;
import game.status.Status;

/**
 * Class implementing the Enemy - Koopa actor.
 * @author James Huynh
 * @version 3.0
 */

public class Koopa extends Enemy {
    // Constructor
    public Koopa() {
        super("Koopa", 'K', 100);
        this.addCapability(Status.INDESTRUCTIBLE);
    }

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(30, "punches");
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // If marked for reset, remove from map via suicide.
        if (hasCapability(Status.RESET)){
            return new SuicideAction();
        }

        // check if Koopa is not conscious
        if(!this.isConscious()) {
            this.addCapability(Status.DORMANT);
            this.setDisplayChar('D');
        }

        // check if Koopa IS NOT dormant
        if(!this.hasCapability(Status.DORMANT)) {
            // checks if lastAction was AttackAction
            if(lastAction instanceof AttackAction) {
                // check if behaviours contains AttackBehaviour already
                if(!behaviours.containsKey(2)) {
                    // adds FollowBehaviour, targeting the actor the current actor called AttackAction on
                    this.behaviours.put(2, new FollowBehaviour(((AttackAction) lastAction).getTarget()));
                }
            }
        }
        // Koopa is dormant
        else {
            behaviours.remove(1); // remove AttackBehaviour
            behaviours.remove(2); // remove FollowBehaviour
            behaviours.remove(10); // remove WanderBehaviour
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
        ActionList list = new ActionList();
        // if Koopa is not in DORMANT state
        if(!this.hasCapability(Status.DORMANT)) {
            // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
            if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                // make AttackAction available to otherActor
                list.add(new AttackAction(this,direction));
            }
        }
        // check if Koopa is in DORMANT state
        else {
            // check if otherActor has a Wrench
            if(otherActor.getWeapon() instanceof Wrench) {
                // allow SmashShellAction
                list.add(new SmashShellAction(this));
            }
        }
        return list;
    }

    /**
     * Mark enemy for reset, hurt if implementation is drop items on death
     */
    @Override
    public void resetInstance() {
        addCapability(Status.RESET);
        removeCapability(Status.INDESTRUCTIBLE); // Removes INDESTRUCTIBLE status
        hurt(getMaxHp());  // Kill enemy (response within playTurn)
    }
}
