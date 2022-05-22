package game.items.magicalitems;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.items.Purchasable;
import game.items.Tickable;
import game.status.Status;

/**
 * PowerStar is one of the magical items that can be consumed by the players
 * @author ChunKau Mok (Peter)
 * @version 1.0
 */
public class PowerStar extends MagicalItem implements Purchasable, Tickable {

    private static final String NAME = "Power Star";
    private static final char DISPLAY_CHAR = '*';
    private static final boolean PORTABLE = true;
    private static final Integer MAX_DURATION = 10;

    private Integer duration;   // active duration

    /**
     * PowerStar constructor
     * Adds capabilities and actions
     */
    public PowerStar() {
        super(NAME, DISPLAY_CHAR, PORTABLE);
        this.duration = MAX_DURATION;                               // 10 turns
        this.addCapability(Status.HIGHER_GROUND);
        this.addCapability(Status.COIN_FROM_DESTROYED_GROUND);
        this.addCapability(Status.IMMUNITY);
        this.addCapability(Status.INSTA_KILL);
    }
    
    /**
     * Inform this item of the passage of time.
     * Reduce the active duration remaining on the actor by 1 turn
     * Remove statuses by this item from actors
     * Remove this item from actor's inventory if remaining duration is 0
     * 
     * This method is called once per turn, if the Item is being carried.
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        this.tick();
        if (duration == 0) {
            for (Enum<?> status : this.capabilitiesList()) {
                actor.removeCapability(status);
            }
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
        this.tick();
        if (this.duration == 0) {
            currentLocation.removeItem(this);
        }
	}

    /**
     * Override the tick() from Tickable
     * Inform this item the passage of time
     */
    @Override
    public void tick() {
        if (duration > 0) {
            duration -= 1;
        }
    }

    /**
     * Call this function when PowerStar are consumed by a player
     */
    @Override
    public void onConsume(Actor actor) {
        for (Enum<?> capability : this.capabilitiesList()) {
            actor.addCapability(capability);
        }
        actor.heal(200);

        // keep this item in the inventory for ticking, but remove all its actions
        this.togglePortability();
        this.removeAction(this.getConsumeAction());
    }

    /**
     * @return the remaining duration of this item before it de-spawn
     */
    public Integer getRemainingDuration() {
        return this.duration;
    }

    /**
     * @return the price of this item
     */
    @Override
    public Integer getPrice() { return 600; }

    /**
     * @return a description of this item, including the remaining duration
     */
    @Override
	public String toString() {
		return PowerStar.NAME + " - " + this.duration.toString() + " turns remaining";
	}

}
