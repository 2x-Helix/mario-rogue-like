package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.AttackAction;
import game.behaviours.Behaviour;
import game.items.ItemPool;
import game.status.Status;

public class DisguisedChest extends Enemy {
    ItemPool itemPool = new ItemPool(); // Create new itemPool
    /**
     * Constructor.
     */
    public DisguisedChest() {
        super("DisguisedChest", 'C', 100);
        behaviours.remove(10); // remove WanderBehaviour
        this.addItemToInventory(itemPool.rollItem()); // roll a random item
    }

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(50, "bites");
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if(lastAction instanceof AttackAction) {
            this.setDisplayChar('G'); // After DisguisedChest attacks, it changes into its undisguised form, 'G'.
        }

        // get behaviour actions
        for(Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        // do nothing at this turn
        return new DoNothingAction();
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) { // Check if otherActor is Friendly
            actions.add(new AttackAction(this, direction)); // Allow otherActor to call AttackAction
        }
        return actions;
    }
}
