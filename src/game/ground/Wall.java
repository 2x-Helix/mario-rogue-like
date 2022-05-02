package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import game.Utils;
import game.status.Status;

/**
 * A type of HighGround
 * @author ChunKau Mok (Peter)
 * @version 2.0
 */
public class Wall extends HighGround {

	/**
	 * Public constructor for this object
	 */
	public Wall() {
		super('#', 80, 20);
	}
	
	/**
	 * @return if this can block thrown objects
	 */
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
}
