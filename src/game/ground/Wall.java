package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import game.Utils;
import game.status.Status;

public class Wall extends HighGround {

	public Wall() {
		super('#', 80, 20);
	}
	
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}

	@Override
    public boolean onJump(Actor actor) {
		if (actor.hasCapability(Status.EASY_JUMP) || Utils.nextChance() <= this.successThreshhold) {
			return true;
		} else {
			actor.hurt(this.fallDamage);
			return false;
		}
	}

}
