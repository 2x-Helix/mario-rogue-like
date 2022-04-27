package game.items.magical_items;

import edu.monash.fit2099.engine.items.Item;
import game.actions.ConsumeAction;

/**
 * TODO: Implement this class
 */
public abstract class MagicalItem extends Item{
    
    /**
     * Protected contructor of this class
     * @param name
     * @param displayChar
     * @param portable
     */
    protected MagicalItem(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }

    /**
     * Create and return an action to consume this item
     */
    protected ConsumeAction getConsumeAction() {
        return new ConsumeAction(this);
    }
    
}
