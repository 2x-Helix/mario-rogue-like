package game.items.weapon_items;

/**
 * @TODO: Implement this class
 */
public class Wrench extends WeaponItem {
    
    private static final String NAME = "Wrench";
    private static final char DISPLAY_CHAR = 'W';   // Peter: not sure 
    private static final boolean PORTABLE = false;

    public Wrench() {
        super(NAME, DISPLAY_CHAR, PORTABLE);
    }

}
