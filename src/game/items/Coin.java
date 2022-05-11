package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.friendlies.Player;
import game.status.Status;
import game.reset.Resettable;

/**
 * A class for the currency in this game
 * @author ChunKau Mok (Peter)
 * @version 1.0
 */
public class Coin extends Item implements Resettable {

    public static final String NAME = "Coin";
    public static final char DISPLAY_CHAR = '$';
    public static final boolean PORTABLE = true; 

    /**
     * Value of the coin holds
     */
    private final Integer value;

    /**
     * Public Constructor for this class
     * @param value Value of the coin holds
     */
    public Coin(Integer value) {
        super(NAME, DISPLAY_CHAR, PORTABLE);
        this.value = value;
        registerResettable();  // Add instance to ResetManager
    }

    /**
     * @return The value of this coin
     */
    public Integer getValue() {
        return this.value;
    }

    /**
     * Inform this item of the passage of time.
     * Reduce the active duration remaining on the actor by 1 turn
     * Remove this item from actor's inventory if remaining duration is 0
     * 
     * This method is called once per turn, if the Item is being carried.
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        // Remove coin from location upon reset
        if (hasCapability(Status.RESET)) {
            currentLocation.removeItem(this);
            return;
        }

        // Add coins to wallet upon game update
        Player player = (Player) actor;
        player.getWallet().addCredit(this.value);
        actor.removeItemFromInventory(this);
    }

    @Override
    public void resetInstance() {
        addCapability(Status.RESET);
    }
}
