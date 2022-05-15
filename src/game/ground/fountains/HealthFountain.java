package game.ground.fountains;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.items.magical_items.bottles.Bottle;
import game.items.magical_items.bottles.HealthBottle;
import game.items.magical_items.bottles.PowerBottle;
import game.status.Status;

public class HealthFountain extends Fountain {

    public static final String NAME = "Health Fountain";
    public static final char DISPLAY_CHAR = 'H';

    public HealthFountain() {
        super(DISPLAY_CHAR);
        this.addCapability(Status.GREATER_HEAL);
    }

    /**
     * Called whenever actor fills its bottle
     * @param actor the actor that fills the water bottle
     */
    @Override
    public void onFill(Actor actor) {

    }

    /**
     * Called whenever actor(enemy) drink water
     * @param actor the actor that drinks the water
     */
    @Override
    public void onDrink(Actor actor) {
        super.onDrink(actor);
        actor.heal(50);
    }

    @Override
    public String toString() {
        return NAME;
    }
}
