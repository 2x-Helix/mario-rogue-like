package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Allows actors to give items to each other
 */
public class GiveAction extends Action {

    // The giver of the item
    private final Actor giver;

    // The item to be given
    private final Item item;

    /**
     * Public constructor for this action
     * @param actor the giver of the item
     * @param item the item to be given
     */
    public GiveAction(Item item, Actor actor) {
        this.giver = actor;
        this.item = item;
    }

    /**
     * Called whenever someone gives another an item for free
     * @param actor The receiver of the item
     * @param map The map the actor is on.
     * @return a description of this action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        giver.removeItemFromInventory(item);
        actor.addItemToInventory(item);
        return menuDescription(actor);
    }

    /**
     * @param actor The receiver of the item
     * @return a description of this action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " receives a " + item + " from " + giver;
    }
}
