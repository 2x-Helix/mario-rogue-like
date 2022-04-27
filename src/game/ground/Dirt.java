package game.ground;

import edu.monash.fit2099.engine.positions.Ground;

/**
 * A class that represents bare dirt.
 * @version 1.1
 */
public class Dirt extends Ground {
	/**
	 * Dirt constructor. Sets the ground as FERTILE.
	 */
	public Dirt() {
		super('.');
		addCapability(GroundCapabilities.FERTILE);
	}
}
