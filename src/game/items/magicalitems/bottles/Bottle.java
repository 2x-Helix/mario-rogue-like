package game.items.magicalitems.bottles;

import edu.monash.fit2099.engine.actors.Actor;
import game.items.magicalitems.MagicalItem;

/**
 * Item which may store the effects of a fountain
 * @author ChunKau Mok
 * @version 1.0
 */
public class Bottle extends MagicalItem {
    // Constants
    public static final String NAME = "Bottle";
    public static final char DISPLAY_CHAR = 'b';
    public static final boolean PORTABLE = true;

    // Constructors
    /**
     * Item which may store the effects of a fountain
     */
    public Bottle() {
        super(NAME, DISPLAY_CHAR, PORTABLE);
        this.removeAction(super.getConsumeAction());    // No, you can't drink from an empty bottle
    }

    /**
     * Item which may store the effects of a fountain
     * @param name Name of the potion
     * @param portable is the potion portable
     */
    protected Bottle(String name, boolean portable) {
        super(name, DISPLAY_CHAR, portable);
    }

    // Methods
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
