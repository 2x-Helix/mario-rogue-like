package game.items.magicalitems;

import edu.monash.fit2099.engine.actors.Actor;
import game.items.Purchasable;
import game.status.Status;

/**
 * SuperMushroom is one of the magical items that can be consumed by the players
 * @author ChunKau Mok (Peter)
 * @version 1.0
 */
public class SuperMushroom extends MagicalItem implements Purchasable {
    
    private static final String NAME = "Super Mushroom";
    private static final char DISPLAY_CHAR = '^';
    private static final boolean PORTABLE = true;

    /**
     * SuperMushroom constructor
     * Adds capabilities and actions
     */
    public SuperMushroom() {
        super(NAME, DISPLAY_CHAR, PORTABLE);
        this.addCapability(Status.TALL);
        this.addCapability(Status.INCREASED_MAX_HP);
        this.addCapability(Status.EASY_JUMP);
    }

    /**
     * Call this function when SuperMushroom are consumed
     */
    @Override
    public void onConsume(Actor actor) {
        for (Enum<?> capability : this.capabilitiesList()) {
            actor.addCapability(capability);
        }
        actor.increaseMaxHp(50);
        actor.removeItemFromInventory(this);    // no need to keep for ticking
    }

    /**
     * @return the price of this item
     */
    @Override
    public Integer getPrice() { return 400; }

    /**
     * @return a description of this item, including the remaining duration
     */
    @Override
    public String toString() {
        return SuperMushroom.NAME;
    }


}
