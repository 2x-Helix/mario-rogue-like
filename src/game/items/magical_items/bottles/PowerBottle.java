package game.items.magical_items.bottles;

import game.status.Status;

public class PowerBottle extends Bottle {

    public static String NAME = "Power Bottle";
    public static boolean PORTABLE = true;

    public PowerBottle() {
        super(NAME, PORTABLE);
        this.addCapability(Status.POWERFUL);
    }

}
