package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.Utils;
import game.ground.Fire;
import game.status.Status;

/**
 * Special Action for attacking other Actors.
 * modified by: James Huynh, ChunKau Mok
 * @version 3.0
 */
public class AttackAction extends Action {

	/**
	 * The defender to be attacked
	 */
	protected Actor target;

	/**
	 * The direction of incoming attack.
	 */
	protected String direction;

	/**
	 * Constructor.
	 * 
	 * @param target the defender
	 */
	public AttackAction(Actor target, String direction) {
		this.target = target;
		this.direction = direction;
	}

	/**
	 * @param actor the attacker
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		// get weapon
		Weapon weapon = actor.getWeapon();
		// chance to miss target
		if (!(Utils.nextChance() <= weapon.chanceToHit())) {
			return actor + " misses " + target + ".";
		}
		// POWERSTAR effect
		int damage = weapon.damage();
		if (actor.hasCapability(Status.INSTA_KILL)) {			// Mario consumes POWERSTAR and can insta kill enemies(target)
			damage = Integer.MAX_VALUE;
		} else if (target.hasCapability(Status.IMMUNITY)) {        // Mario(target) consumes POWERSTAR and have immunity against enemies'(actor) attacks
			damage = 0;
		}
		// display result
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";

		// damage actor
		target.hurt(damage); // decrease target's hp
		// FIRE_ATTACK effect
		if(actor.hasCapability(Status.FIRE_ATTACK)) {
			map.locationOf(target).setGround(new Fire(map.locationOf(target).getGround())); // Sets the ground to Fire, taking parameter of the old ground to remember
		}
		// target is not conscious
		if (!target.isConscious()) {
			// check if target does not have indestructible status
			if (!target.hasCapability(Status.INDESTRUCTIBLE)) {
				ActionList dropActions = new ActionList();
				// drop all items
				for (Item item : target.getInventory())
					dropActions.add(item.getDropAction(actor));
				for (Action drop : dropActions)
					drop.execute(target, map);
				// remove actor
				map.removeActor(target);
				result += System.lineSeparator() + target + " is killed.";
			}
		}
		return result;
	}

	// gets the target actor; the actor being attacked.
	public Actor getTarget() {
		return target;
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target + " at " + direction;
	}
}
