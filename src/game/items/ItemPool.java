package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.Utils;
import game.items.magical_items.PowerStar;
import game.items.magical_items.SuperMushroom;
import game.items.magical_items.bottles.HealthBottle;
import game.items.magical_items.bottles.PowerBottle;
import game.items.weapon_items.Wrench;

import java.util.ArrayList;

public class ItemPool { // single responsibility, open close
    private ArrayList<Item> itemPool = new ArrayList<Item>(); // initialise itemPool ArrayList.

    /**
     * Constructor, automatically adds certain items to the itemPool ArrayList upon instantiating this class.
     */
    public ItemPool(){
        itemPool.add(new SuperMushroom());
//        itemPool.add(new PowerStar());  // Prevent 1-shot
        itemPool.add(new HealthBottle());
        itemPool.add(new Wrench());
        itemPool.add(new PowerBottle());
    }

    /**
     * Generates a random index from the length of itemPool, and returns a random
     * item in this ArrayList.
     * @return returns an Item
     */
    public Item rollItem() {
        int index = Utils.nextInt(0, itemPool.size()-1);
        return itemPool.get(index);
    }
}
