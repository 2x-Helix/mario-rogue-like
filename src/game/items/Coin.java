package game.items;

import edu.monash.fit2099.engine.items.Item;

/**
 * A class for the currency in this game
 */
public class Coin extends Item{

    /**
     * public to skip all the getter
     * static as the values are the same for all object in this class
     * final as the values don't change
     */
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
