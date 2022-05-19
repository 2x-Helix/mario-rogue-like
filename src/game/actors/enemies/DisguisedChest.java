package game.actors.enemies;

import game.items.ItemPool;

public class DisguisedChest extends Enemy {
    ItemPool itemPool = new ItemPool(); // Create new itemPool
    /**
     * Constructor.
     */
    public DisguisedChest() {
        super("DisguisedChest", 'G', 100);
        behaviours.remove(10); // remove WanderBehaviour
        this.addItemToInventory(itemPool.rollItem()); // roll a random item
    }
}
