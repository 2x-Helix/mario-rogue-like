package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.Utils;

import java.util.ArrayList;

/**
 * @author James Huynh, Matthew Siegenthaler
 * This class is responsible for initialising an ArrayList, adding specific items to its ArrayList, and is able to return a random item
 * from its ArrayList upon command.
 * @version 1.2
 */
public class ItemPool { // single responsibility, upholds open close principle
    private ArrayList<Item> itemPool = new ArrayList<>(); // initialise itemPool ArrayList.

    /**
     * Constructor, automatically adds certain items to the itemPool ArrayList upon instantiating this class.
     */
    public ItemPool(ArrayList<Item> items){
        this.itemPool.addAll(items);
    }

    /**
     * Generates a random index from the length of itemPool, and returns a random
     * item in this ArrayList.
     * @return returns an Item
     */
    public Item rollItem() {
        int index;
        if (itemPool.size() == 1) {
            index = 0;
        } else {
            index = Utils.nextInt(0, itemPool.size() - 1);
        }
        return itemPool.get(index);
    }
}
