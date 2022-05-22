package game.ground.chests;

import edu.monash.fit2099.engine.items.Item;
import game.items.ItemPool;
import game.items.equipments.BFG;
import game.items.equipments.Cryotov;
import game.items.equipments.Fruit;
import game.items.equipments.Molotov;

import java.util.ArrayList;

/**
 * Chest that drops equipment
 */
public class EquipmentChest extends Chest {
    // Constants
    private static final String CHEST_TYPE = "Equipment Chest";
    private static final int MAX_USES = 2;
    private static final int COST = 200;

    // Constructor
    /**
     * Instantiates an equipment chest
     * Drops equipment
     */
    public EquipmentChest() {
        super(CHEST_TYPE, MAX_USES, COST);
    }

    @Override
    protected ItemPool getItemPool() {
        ArrayList<Item> loot = new ArrayList<>();
        // Add items
        loot.add(new BFG());
        loot.add(new Cryotov());
        loot.add(new Fruit());
        loot.add(new Molotov());
        return new ItemPool(loot);
    }
}
