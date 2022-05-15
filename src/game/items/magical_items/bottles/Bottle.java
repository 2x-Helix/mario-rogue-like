package game.items.magical_items.bottles;

import edu.monash.fit2099.engine.actors.Actor;
import game.items.magical_items.MagicalItem;

public class Bottle extends MagicalItem {

    public static final String NAME = "Bottle";
    public static final char DISPLAY_CHAR = 'b';
    public static final boolean PORTABLE = true;

    public Bottle() {
        super(NAME, DISPLAY_CHAR, PORTABLE);
        this.removeAction(super.getConsumeAction());    // No, you can't drink from an empty bottle
    }

    protected Bottle(String name, boolean portable) {
        super(name, DISPLAY_CHAR, portable);
    }

    @Override
    public void onConsume(Actor actor) {

        // add statuses to actor
        for (Enum<?> status : this.capabilitiesList()) {
            actor.addCapability(status);
        }

        // "empties" the bottle
        actor.addItemToInventory(new Bottle());
        actor.removeItemFromInventory(this);

    }
}
