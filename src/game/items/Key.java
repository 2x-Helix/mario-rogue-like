package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.status.Status;

/**
 * This class represents an item that allows the actor to execute RescueAction.
 */
public class Key extends Item {
    /***
     * Constructor for the item Key.
     */
    public Key() {
        super("Key", '!', true);
        this.addCapability(Status.CAN_UNLOCK);
    }
}
