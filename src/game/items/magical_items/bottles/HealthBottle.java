package game.items.magical_items.bottles;

import edu.monash.fit2099.engine.actors.Actor;
import game.status.Status;

public class HealthBottle extends Bottle {

    public static String NAME = "Health Bottle";
    public static boolean PORTABLE = true;

    public HealthBottle() {
        super(NAME, PORTABLE);
        this.addCapability(Status.GREATER_HEAL);
    }

    @Override
    public void onConsume(Actor actor) {
        super.onConsume(actor);
        actor.heal(50);
    }

}
