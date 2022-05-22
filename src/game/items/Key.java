package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.status.Status;

/**
 * This class represents an item, Key, that gives the holder the capability, CAN_UNLOCK, meaning, the actor is able to unlock objects or locked subjects in the game.
 * @author James Huynh
 * @version 1.1
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
