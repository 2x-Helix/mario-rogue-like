package game.items.weapon_items;

import game.items.ItemManager;

public class Wrench extends WeaponItem {
    
    private static final String NAME = "Wrench";
    private static final char DISPLAY_CHAR = 'W';
    private static final boolean PORTABLE = false;

    /**
     * 
     */
    public Wrench() {
        super(NAME, DISPLAY_CHAR, PORTABLE);
        ItemManager.getInstance().insertPrice(this, 200);   // 200 is the default price of this item
    }

}
