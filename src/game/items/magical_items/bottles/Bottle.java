package game.items.magical_items.bottles;

import game.items.magical_items.MagicalItem;

public abstract class Bottle extends MagicalItem {

    public static final char DISPLAY_CHAR = 'b';

    protected Bottle(String name, boolean portable) {
        super(name, DISPLAY_CHAR, portable);

    }

}
