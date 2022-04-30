package game.items;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.status.Status;
import game.reset.ResetManager;
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
    private Integer value;

    /**
     * Public Constructor for this class
     * @param value Value of the coin holds
     */
    public Coin(Integer value) {
        super(NAME, DISPLAY_CHAR, PORTABLE);
        this.value = value;
        registerInstance();  // Add instance to ResetManager
    }

    /**
     * @return The value of this coin
     */
    public Integer getValue() {
        return this.value;
    }

    /**
     *
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        // Remove coin from location upon reset
        if (hasCapability(Status.RESET)) {
            currentLocation.removeItem(this);
            return;
        }
    }

    @Override
    public void resetInstance() {
        addCapability(Status.RESET);
    }
}
