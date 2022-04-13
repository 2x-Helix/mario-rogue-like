package game.items.magical_items;

import edu.monash.fit2099.engine.items.Item;
/**
 * TODO: Implement this class
 */
public abstract class MagicalItem extends Item{
    
    /**
     * 
     * @param name
     * @param displayChar
     * @param portable
     */
    protected MagicalItem(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }
    
}
