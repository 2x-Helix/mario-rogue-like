package game.ground.fountains;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.items.magical_items.bottles.Bottle;
import game.items.magical_items.bottles.PowerBottle;
import game.status.Status;

public class PowerFountain extends Fountain {

    public static final String NAME = "Power Fountain";
    public static final char DISPLAY_CHAR = 'A';

    public PowerFountain() {
        super(DISPLAY_CHAR);
        this.addCapability(Status.POWERFUL);
    }

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
        actor.addItemToInventory(new PowerBottle());
        super.onFill(actor);
    }

    @Override
    public String toString() {
        return NAME;
    }
}
