package game.items.magical_items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Allow consummable items to be consumed
 * @author ChunKau Mok (Peter)
 * @version 1.0
 */
public class ConsumeAction extends Action{
    
    /**
     * The item to be consumed
     */
    private MagicalItem consumable;

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
        consumable.onConsume(actor);

        // Remove item from the ground or inventory
        if (actor.getInventory().contains(this.consumable)) {
            actor.removeItemFromInventory(consumable);
        } else {
            map.locationOf(actor).removeItem(this.consumable);
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
        if (actor.getInventory().contains(this.consumable)) {
            return actor + " consumes " + consumable + " in the inventory";
        } else {
            return actor + " consumes " + consumable + " on the ground";
        }
    }

}
