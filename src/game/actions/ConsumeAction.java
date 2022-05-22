package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Tickable;
import game.items.magicalitems.MagicalItem;

/**
 * Allow consummable items to be consumed
 * @author ChunKau Mok (Peter)
 * @version 1.0
 */
public class ConsumeAction extends Action{
    
    /**
     * The item to be consumed
     */
    private final MagicalItem consumable;

    /**
     * Public constructor for this class
     * @param consumable the item to be consumed
     */
    public ConsumeAction(MagicalItem consumable) {
        this.consumable = consumable;
    }

    /**
     * Actor consumes the item from the inventory/the ground
     * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        // provides statuses to actor
        consumable.onConsume(actor);

        // consumes from ground, remove item from the ground
        if (map.locationOf(actor).getItems().contains(consumable)) {
            map.locationOf(actor).removeItem(consumable);
        }

        // add item to inventory for ticking
        if (consumable instanceof Tickable) {
            actor.addItemToInventory(consumable);
        }

        return this.menuDescription(actor);
    }

    /**
	 * Returns a descriptive string
	 * @param actor The actor performing the action.
	 * @return the text we put on the menu
	 */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes " + consumable;
    }

}
