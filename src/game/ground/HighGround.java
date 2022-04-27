package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.items.Coin;
import game.status.Status;

/**
 * 
 */
public class HighGround extends Ground {

    /**
     * 
     * @param displayChar is the char to be displayed in the terminal
     */
    public HighGround(char displayChar) {
        super(displayChar);
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
     * @param location is where this Tree at
     * @param actor is whom walks on this ground
     */
    public void onWalk(Location location, Actor actor) {
        // Convert to Dirt
        if (actor.hasCapability(Status.HIGHER_GROUND)) {
            location.setGround(new Dirt());
            
            // Drop $5
            if (actor.hasCapability(Status.COIN_FROM_DESTROYED_GROUND)) {
                location.addItem(new Coin(5));
            }
        }
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

}
