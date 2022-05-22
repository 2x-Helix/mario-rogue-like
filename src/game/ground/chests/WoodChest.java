package game.ground.chests;

import edu.monash.fit2099.engine.items.Item;
import game.items.ItemPool;
import game.items.magicalitems.SuperMushroom;
import game.items.weaponitems.Wrench;

import java.util.ArrayList;

/**
 * The lowest tier chest, drops super mushroom or a wrench.
 * @author Matthew Siegenthaler
 * @version 1.0
 */
public class WoodChest extends Chest{
    // Constants
    private static final String CHEST_TYPE = "Wooden Chest";
    private static final int MAX_USES = 1;
    private static final int COST = 50;

    // Constructor
    /**
     * Instantiates a wooden chest
     */
    public WoodChest() {
        super(CHEST_TYPE, MAX_USES, COST);
    }

    @Override
    protected ItemPool getItemPool() {
        ArrayList<Item> loot = new ArrayList<>();
        // Add items
        loot.add(new Wrench());
        loot.add(new SuperMushroom());
        return new ItemPool(loot);
    }
}
