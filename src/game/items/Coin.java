package game.items;

import edu.monash.fit2099.engine.items.Item;

/**
 * @TODO: Implement this class
 */
public class Coin extends Item{

    private static final String NAME = "Coin";
    private static final char DISPLAY_CHAR = '$';
    private static final boolean PORTABLE = true;

    public Coin() {
        super(NAME, DISPLAY_CHAR, PORTABLE);
    }

}
