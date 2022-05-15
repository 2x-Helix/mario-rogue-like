package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.actors.enemies.Enemy;

/**
 * A class that represents the floor inside a building.
 */
public class Floor extends Ground implements Flammable {
	public Floor() {
		super('_');
	}

	// Restricts enemies from entering this ground
	@Override
	public boolean canActorEnter(Actor actor) {
		if(actor instanceof Enemy){
			return false;
		}
		return true;
	}
}
