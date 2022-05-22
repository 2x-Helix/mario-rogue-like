package game.ground.fountains;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.items.magicalitems.bottles.Bottle;
import game.items.magicalitems.bottles.HealthBottle;
import game.status.Status;

/**
 * Fountain to heal a player
 * @author ChunKau Mok
 * @version 1.0
 */
public class HealthFountain extends Fountain {
    // Constants
    public static final String NAME = "Health Fountain";
    public static final char DISPLAY_CHAR = 'H';

    // Constructors
    /**
     * Fountain player can heal from
     */
    public HealthFountain() {
        super(DISPLAY_CHAR);
        this.addCapability(Status.GREATER_HEAL);
    }

    // Methods
    /**
     * FIXME: violates the DRY principle
     * Called whenever actor fills its bottle
     * @param actor the actor that fills the water bottle
     */
    @Override
    public void onFill(Actor actor) throws Exception {
        // Search for the empty bottle
        Bottle bottle = null;
        for (Item item : actor.getInventory()) {
            if (item.getClass() == Bottle.class) {
                bottle = (Bottle) item;
                break;
            }
        }

        // Doesn't contain bottle, then this function should not be called
        if (bottle == null)
            throw new Exception("Actor does not contain an empty bottle");

        // "Filled" the empty bottle
        actor.removeItemFromInventory(bottle);
        actor.addItemToInventory(new HealthBottle());
        super.onFill(actor);
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
