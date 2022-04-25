package game.items.magical_items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.ConsumeAction;

/**
 * PowerStar is one of the magical items that can be consumed by the players
 * @author ChunKau Mok (Peter)
 * @version 1.0
 */
public class PowerStar extends MagicalItem {

    private static final String NAME = "Power Star";
    private static final char DISPLAY_CHAR = '*';
    private static final boolean PORTABLE = true;

    private Integer duration;   // active duration

    /**
     * PowerStar constructor
     * Adds capabilities and actions
     */
    public PowerStar() {
        super(NAME, DISPLAY_CHAR, PORTABLE);
        this.duration = 10;     // 10 turns
        this.addCapability(Status.HIGHER_GROUND);
        this.addCapability(Status.IMMUNITY);
        this.addCapability(Status.INSTA_KILL);
        this.addAction(new ConsumeAction(this));
        this.addAction(new DropItemAction(this));
    }

    /**
     * Create and return an action to consume this item
     */
    public ConsumeAction getConsumeAction() {
        return new ConsumeAction(this);
    }

    /**
     * Inform this item of the passage of time.
     * Reduce the active duration remaining on the actor by 1 turn
     * Remove this item from actor's inventory if remaining duration is 0
     * 
     * This method is called once per turn, if the Item is being carried.
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        if (this.duration > 0) {
            this.duration -= 1;
        } else {
            actor.removeItemFromInventory(this);
        }
    }

    /**
     * Inform an Item on the ground of the passage of time. 
     * Reduce the active duration remaining on the ground by 1 turn
     * Remove this item from the ground if remaining duration is 0
     * 
     * This method is called once per turn, if the item rests upon the ground.
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
	public void tick(Location currentLocation) {
        if (this.duration > 0) {
            this.duration -= 1;
        } else {
            currentLocation.removeItem(this);
        }
	}

    @Override
	public String toString() {
		return PowerStar.NAME + " - " + this.duration.toString() + " turns remaining";
	}

}

