package game.items;

import java.util.HashMap;

import edu.monash.fit2099.engine.items.Item;

/**
 * A singleton to manage the prices of items
 * @author ChunKau Mok (Peter)
 * @version 1.0
 */
public class ItemManager {
    
    private static ItemManager manager = null;
    private HashMap<Item, Integer> mapper;

    /**
     * Private constructor for getInstance()
     */
    private ItemManager() {}

    /**
     * Factory method for this class
     * @return the instance of ItemManager
     */
    public static ItemManager getInstance() {
        return manager == null ? new ItemManager() : manager;
    }

    /**
     * Insert a new item with its price
     * @param item the item to be inserted
     * @param price the price of the item
     */
    public void insertPrice(Item item, Integer price) {
        mapper.put(item, price);
    }

    /**
     * Get the price of an existing item
     * @param item the item to be queried
     * @return the price of the item
     * @throws Exception if the item doesn't exist in the manager
     */
    public Integer getPrice(Item item) throws Exception {
        if (!mapper.containsKey(item))
            throw new Exception("Insert item first");

        return mapper.get(item);
    }

    /**
     * Update the price of an existing item
     * @param item the item to be updated
     * @param price the new price of the item
     * @throws Exception if the item doesn't exist in the manager
     */
    public void updatePrice(Item item, Integer price) throws Exception {
        if (!mapper.containsKey(item))
            throw new Exception("Insert item first");

        mapper.replace(item, price);
    }

    /**
     * Remove an existing item
     * Use this only if you know what are you doing, you are making an item un-tradable
     * @param item the item to be removed
     * @throws Exception if the item doesn't exist in the manager
     */
    public void removeItem(Item item) throws Exception {
        if (!mapper.containsKey(item))
            throw new Exception("Insert item first");

        mapper.remove(item);
    }

}
