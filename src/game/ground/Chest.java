package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.OpenChestAction;
import game.items.ItemPool;
import game.reset.Resettable;
import game.status.Status;

/**
 * This class Chest represents a Ground Chest, that, upon proximity to Player, offers a random item generated from its ItemPool.
 * Allows actor to call OpenChestAction() to obtain a random item.
 */
public class Chest extends Ground implements Resettable {

    private ItemPool loot = new ItemPool();
    private int durability = 2; // set chest durability to 2

    /**
     * Constructor for Chest, and sets the displayChar to 'C'.
     */
    public Chest() {
        super('C');
        registerResettable();
    }

    /**
     * This method returns the ItemPool of the chest.
     * @return an instance of ItemPool.
     */
    public ItemPool getItemPool() {
        return loot;
    }

    /**
     * This method returns an integer, durability of the Chest instance
     * @return returns an integer durability
     */
    public int getDurability() {
        return durability;
    }

    /**
     * This method decreases the attribute durability by 1 integer.
     */
    public void decreaseDurability() {
        this.durability -= 1;
    }

    @Override
    public void tick(Location location) {
        if (this.hasCapability(Status.RESET)) { // if Chest has capability RESET, Chest will be converted into Dirt.
            location.setGround(new Dirt());
        }
    }

    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        if(actor.getDisplayChar() == 'm' || actor.getDisplayChar() == 'M') { // only offer Player OpenChestAction
            actions.add(new OpenChestAction(this));
        }
        return actions;
    }

    @Override
    public void resetInstance() {
        this.addCapability(Status.RESET);
    }
}
