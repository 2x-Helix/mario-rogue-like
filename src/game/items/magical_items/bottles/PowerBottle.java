package game.items.magical_items.bottles;

import edu.monash.fit2099.engine.actors.Actor;
import game.items.magical_items.PowerStar;

public class PowerBottle extends Bottle {

    public static String NAME = "Power Bottle";
    public static boolean PORTABLE = true;

    public PowerBottle() {
        super(NAME, PORTABLE);
    }
    @Override
    public void onConsume(Actor actor) {

    }
}
