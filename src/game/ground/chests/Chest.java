package game.ground.chests;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.OpenChestAction;
import game.ground.Dirt;
import game.items.ItemPool;
import game.reset.Resettable;
import game.status.Status;

/**
 * This class Chest represents a Ground Chest, that, upon proximity to Player, offers a random item generated from its ItemPool.
 * Allows actor to call OpenChestAction() to obtain a random item.
 * @author James Huynh, Matthew Siegenthaler
 * @version 1.1
 */
public abstract class Chest extends Ground implements Resettable {
    // Attributes
    private String chestType;  // Name of chest
    private int maxDurability;  // Max charges
    private int durability;
    private int cost;

    // Constructors
    /**
     * Constructor for Chest, and sets the displayChar to 'C'.
     */
    public Chest(String chestType, int maxDurability, int cost) {
        super('c');
        this.chestType = chestType;
        this.maxDurability = maxDurability;
        this.cost = cost;
        resetDurability();
        registerResettable();
    }

    // Getter/setters
    /**
     * This method returns the ItemPool of the chest.
     * @return an instance of ItemPool.
     */
    protected abstract ItemPool getItemPool();

    /**
     * Returns the cost for opening the chest
     * @return int cost for opening the chest.
     */
    public int getCost() { return cost;}

    /**
     * Getter for chest type
     * @return String of the type of chest.
     */
    public String getChestType() { return chestType; }


    // Methods
    public Item getItem() {
        Item item = getItemPool().rollItem();
        decreaseDurability();
        return item;
    }

    /**
     * This method decreases the attribute durability by 1 integer.
     */
    public void decreaseDurability() {
        this.durability -= 1;
    }

    /**
     * Replenished chest uses to initial amount.
     */
    public void resetDurability() {
        this.durability = maxDurability;
    }

    @Override
    public void tick(Location location) {
        // Remove chest if no more items
        if (durability <= 0) {
            location.setGround(new Dirt()); // mark chest for reset
        }
    }

    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        if(actor.toString().equals("Player")) { // only offer Player OpenChestAction
            actions.add(new OpenChestAction(this));
        }
        return actions;
    }

    @Override
    public void resetInstance() {
        // Replenish chest uses
        resetDurability();
    }
}
