package game.items.magical_items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.status.Status;
import game.status.StatusManager;

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
        this.duration = 10;                                 // 10 turns
        this.addCapability(Status.HIGHER_GROUND);
        this.addCapability(Status.COIN_FROM_DESTROYED_GROUND);
        this.addCapability(Status.IMMUNITY);
        this.addCapability(Status.INSTA_KILL);
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

    /**
     * Call this function when PowerStar are consumed by a player
     */
    @Override
    public void onConsume(Actor actor) {

        // add all statuses, with duration or not
        for (Enum<?> capability : this.capabilitiesList()) {
            actor.addCapability(capability);
        }

        // add statuses with duration to be managed 
        StatusManager statusManager = StatusManager.getInstance();
        try {
            for (Enum<?> capability : this.capabilitiesList()) {
                statusManager.insertDuration(actor, (Status)capability, this.getRemainingDuration());
            }
        } catch (Exception e) {
            System.out.println(e + "; Something is wrong with PowerStar.tick :/");
        }

        actor.heal(200);
    }

    /**
     * @return the remaining duration of this item before it de-spawn
     */
    public Integer getRemainingDuration() {
        return this.duration;
    }

    /**
     * @return a description of this item, including the remaining duration
     */
    @Override
	public String toString() {
		return PowerStar.NAME + " - " + this.duration.toString() + " turns remaining";
	}

}
