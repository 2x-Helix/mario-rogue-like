package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.AttackAction;
import game.actions.SuicideAction;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.items.Key;
import game.status.Status;

/**
 * This class represents an Enemy Actor, Bowser, that has capability FIRE_ATTACK, which upon attacking another actor, a Fire will be dropped
 * on their location.
 * @author James Huynh
 * @version 1.0
 */
public class Bowser extends Enemy {
    public Bowser() {
        super("Bowser", 'B', 500);
        behaviours.remove(10); // remove WanderBehaviour
        this.addItemToInventory(new Key());
        this.addCapability(Status.FIRE_ATTACK);
    }

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(80, "punches");
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // If marked for reset, remove from map via suicide.
        if (hasCapability(Status.RESET)){
            this.heal(this.getMaxHp());
            //map.moveActor(this, //LOCATION HERE);
        }

        // checks if lastAction was AttackAction
        if(lastAction instanceof AttackAction) {
            // check if behaviours contains AttackBehaviour already
            if(!behaviours.containsKey(2)) {
                // adds FollowBehaviour, targeting the actor the current actor called AttackAction on
                this.behaviours.put(2, new FollowBehaviour(((AttackAction) lastAction).getTarget()));
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
        ActionList list = new ActionList();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            list.add(new AttackAction(this,direction));
        }
        return list;
    }
}
