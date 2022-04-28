package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.JumpActorAction;
import game.items.Coin;
import game.status.Status;

/**
 * A ground type to represent ground that Players need to Jump to move onto it
 * @author ChunKau Mok (Peter)
 * @version 1.0
 */
public class HighGround extends Ground {

    protected Integer fallDamage, successThreshhold;    // successThreshhold = chance to jump * 100 : 0.8 -> 80

    /**
     * Constructor for its child
     * @param displayChar is the char to be displayed in the terminal
     */
    protected HighGround(char displayChar, Integer successThreshhold, Integer fallDamage) {
        super(displayChar);
        this.successThreshhold = successThreshhold;
        this.fallDamage = fallDamage;
    }

	/**
	 * Returns an Action list for HighGround ground type
	 * @param actor the Actor acting
	 * @param location the current Location
	 * @param direction the direction of the Ground from the Actor
	 * @return a new ActionList with only JumpAction in it
	 */
    @Override
	public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actionList = new ActionList();
        actionList.add(new JumpActorAction(location, direction, null));
		return actionList;
	}

	/**
	 * Actors can enter having the Status HIGH_GROUND from PowerStar
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return actor.hasCapability(Status.HIGHER_GROUND);
	}

    /**
     * Called whenever actor "walk" on this ground
     * Actor should have Status.HIGHER_GROUND to do so
     * @param location is where this ground at
     * @param actor is whom walks on this ground
     */
    public void onWalk(Location location, Actor actor) {
        // Convert to Dirt
        if (actor.hasCapability(Status.HIGHER_GROUND)) {    // canActorEnter == true omitted
            location.setGround(new Dirt());
            
            // Drop $5
            if (actor.hasCapability(Status.COIN_FROM_DESTROYED_GROUND)) {
                location.addItem(new Coin(5));
            }
        }
    }

    /**
     * Called whenever actor tries to jump on this ground
     * @param location is where this ground at
     * @param actor is whom tries to jump on this ground
     */
    public boolean onJump(Actor actor) {
        return actor.hasCapability(Status.EASY_JUMP);
    }

    /**
     * Basically override to execute onWalk when its necessary
     */
    @Override
    public void tick(Location location) {
        if (location.containsAnActor()) {
            this.onWalk(location, location.getActor());
        }
    }

    /**
     * @return the fall damage of this high ground
     */
    public Integer getFallDamage() {
        return this.fallDamage;
    }

}
