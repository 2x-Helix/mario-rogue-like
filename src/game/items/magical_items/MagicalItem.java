package game.items.magical_items;

import java.util.ArrayList;

import edu.monash.fit2099.engine.items.Item;
import game.Status;

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
    
}
