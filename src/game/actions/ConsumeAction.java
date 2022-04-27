package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.magical_items.PowerStar;
import game.status.Status;
import game.status.StatusManager;

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
     * Actor consumes the item from the inventory/the ground
     * 
     * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        StatusManager statusManager = StatusManager.getStatusManager();
        if (this.item.getClass() == PowerStar.class) {
            PowerStar star = (PowerStar) item;          // Stinky, i know
            try {
                for (Enum<?> capability : item.capabilitiesList()) {
                    statusManager.insertStatusDuration(actor, (Status)capability, star.getRemainingDuration());
                }
            } catch (Exception e) {
                System.out.println(e + "; Something is wrong with PowerStar.tick :/");
            }
        }

        for (Enum<?> capability : item.capabilitiesList()) {
            actor.addCapability(capability);
        }
        
        if (actor.getInventory().contains(this.item)) {                 // Actor consumes item from the inventory
            actor.removeItemFromInventory(item);
            return this.menuDescription(actor);
        } else {                                                        // Actor consumes item from the ground, without picking it up
            map.locationOf(actor).removeItem(this.item);
            return this.menuDescription(actor);
        }
    }

    /**
	 * Returns a descriptive string
	 * @param actor The actor performing the action.
	 * @return the text we put on the menu
	 */
    @Override
    public String menuDescription(Actor actor) {
        if (actor.getInventory().contains(this.item)) {
            return actor + " consumes " + item + " in the inventory";
        } else {
            return actor + " consumes " + item + " on the ground";
        }
    }

}
