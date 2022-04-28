package game.items;

import edu.monash.fit2099.engine.items.Item;

/**
 * A class for the currency in this game
 * @author ChunKau Mok (Peter)
 * @version 1.0
 */
public class Coin extends Item{

    public static final String NAME = "Coin";
    public static final char DISPLAY_CHAR = '$';
    public static final boolean PORTABLE = true;

    /**
     * Value of the coin holds
     */
    private Integer value;

    /**
     * Public Constructor for this class
     * @param value Value of the coin holds
     */
    public Coin(Integer value) {
        super(NAME, DISPLAY_CHAR, PORTABLE);
        this.value = value;
    }

    /**
     * @return The value of this coin
     */
    public Integer getValue() {
        return this.value;
    }

}
