package game.items.equipments;

import edu.monash.fit2099.engine.items.Item;
import game.items.Tickable;

public class Equipment extends Item implements Tickable {

    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public Equipment(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }

    @Override
    public void tick() {

    }
}
