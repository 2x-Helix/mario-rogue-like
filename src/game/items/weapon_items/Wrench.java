package game.items.weapon_items;

import game.items.Purchasable;

/**
 * TODO: Implement this class
 */
public class Wrench extends WeaponItem implements Purchasable{
    
    private static final String NAME = "Wrench";
    private static final char DISPLAY_CHAR = 'W';
    private static final boolean PORTABLE = false;

    /**
     * 
     */
    public Wrench() {
        super(NAME, DISPLAY_CHAR, PORTABLE);
    }

    /**
     * @return the price of this item in Toad's store
     */
    public Integer getPrice() {
        return 200;
    }

}
