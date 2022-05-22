package game.items.magicalitems.bottles;

import game.status.Status;

/**
 * Bottle with effect to increase intrinsic damage
 * @author ChunKau Mok
 * @version 1.0
 */
public class PowerBottle extends Bottle {
    // Constants
    public static String NAME = "Power Bottle";
    public static boolean PORTABLE = true;

    // Constructor
    /**
     * Bottle with effect to increase intrinsic damage.
     */
    public PowerBottle() {
        super(NAME, PORTABLE);
        this.addCapability(Status.POWERFUL);
    }
}
