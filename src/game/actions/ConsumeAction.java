package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.magical_items.PowerStar;

public class ConsumeAction extends Action{
    
    /**
     * The item to be consumed
     */
    private Item item;

    /**
     * Public constructor for this class
     * @param item the item to be consumed
     */
    public ConsumeAction(Item item) {
        this.item = item;
    }

    /**
     * Actor consumes the items
     * 
     * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        for (Enum<?> capability : item.capabilitiesList()) {
            actor.addCapability(capability);
        }
        if (!(this.item instanceof PowerStar)) {
            actor.removeItemFromInventory(this.item);
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
        return actor + " consumes " + item;
    }

}
