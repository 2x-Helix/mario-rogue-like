package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;
import game.behaviours.Behaviour;
import game.items.ItemPool;

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
}
