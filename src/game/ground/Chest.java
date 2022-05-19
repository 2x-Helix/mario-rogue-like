package game.ground;

import edu.monash.fit2099.engine.positions.Ground;
import game.items.ItemPool;

public class Chest extends Ground {

    private ItemPool loot = new ItemPool();

    /**
     * Constructor.
     */
    public Chest() {
        super('C');
    }

    public ItemPool getItemPool() {
        return loot;
    }


}
