package game.items.magical_items.bottles;

import edu.monash.fit2099.engine.actors.Actor;

public class HealthBottle extends Bottle {

    public static String NAME = "Health Bottle";
    public static boolean PORTABLE = true;

    public HealthBottle() {
        super(NAME, PORTABLE);
    }
    @Override
    public void onConsume(Actor actor) {

    }
}
