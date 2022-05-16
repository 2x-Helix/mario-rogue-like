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
        if (this.hasCapability(Status.POWERFUL))
            return new IntrinsicWeapon(45, "punches");
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

            // checks if lastAction was AttackAction and behaviours doesn't contain FollowBehaviour
            if(lastAction instanceof AttackAction && !behaviours.containsKey(2)) {
                // adds FollowBehaviour, targeting the actor the current actor called AttackAction on
                this.behaviours.put(2, new FollowBehaviour(((AttackAction) lastAction).getTarget()));
            }

        } else {
            behaviours.clear();                         // Koopa is dormant
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
            // check if otherActor has CAN_SMASH capability
            if(otherActor.hasCapability(Status.CAN_SMASH)) {
                // allow SmashShellAction
                list.add(new SmashShellAction(this));
            }
        }
        return list;
    }
}
