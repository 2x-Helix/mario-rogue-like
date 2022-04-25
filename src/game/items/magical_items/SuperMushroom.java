package game.items.magical_items;

import edu.monash.fit2099.engine.items.DropItemAction;
import game.Status;
import game.actions.ConsumeAction;

/**
 * SuperMushroom is one of the magical items that can be consumed by the players
 * @author ChunKau Mok (Peter)
 * @version 1.0
 */
public class SuperMushroom extends MagicalItem{
    
    private static final String NAME = "Super Mushroom";
    private static final char DISPLAY_CHAR = '^';
    private static final boolean PORTABLE = true;

    /**
     * SuperMushroom constructor
     * Adds capabilities and actions
     */
    public SuperMushroom() {
        super(NAME, DISPLAY_CHAR, PORTABLE);
        this.addCapability(Status.INCREASED_MAX_HP);
        this.addCapability(Status.EASY_JUMP);
        this.addAction(new ConsumeAction(this));
        this.addAction(new DropItemAction(this));
    }

    /**
     * Create and return an action to consume this item
     */
    public ConsumeAction getConsumeAction() {
        return new ConsumeAction(this);
    }

}
