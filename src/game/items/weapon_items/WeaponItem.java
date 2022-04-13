package game.items.weapon_items;

import edu.monash.fit2099.engine.items.Item;

/**
 * TODO: Implement this class
 */
public abstract class WeaponItem extends Item {

    /**
     * 
     * @param name
     * @param displayChar
     * @param portable
     */
    protected WeaponItem(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }

}
