package game.items.magical_items.bottles;

import edu.monash.fit2099.engine.actors.Actor;
import game.status.Status;

/**
 * Bottle with effect to heal the player
 * @author ChunKau Mok
 * @version 1.0
 */
public class HealthBottle extends Bottle {
    // Constants
    public static String NAME = "Health Bottle";
    public static boolean PORTABLE = true;

    // Constructors
    public HealthBottle() {
        super(NAME, PORTABLE);
        this.addCapability(Status.GREATER_HEAL);
    }

    // Methods
    @Override
    public void onConsume(Actor actor) {
        super.onConsume(actor);
        actor.heal(50);
    }
}
