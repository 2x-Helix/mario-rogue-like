package game.actions;

import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.Utils;
import game.status.Status;

/**
 * Special Action for attacking other Actors.
 * modified by: James Huynh
 * @version 2.0
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;

	/**
	 * The direction of incoming attack.
	 */
	protected String direction;

	/**
	 * Constructor.
	 *
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target, String direction) {
		this.target = target;
		this.direction = direction;
	}

	@Override
	public String execute(Actor actor, GameMap map) {

		Weapon weapon = actor.getWeapon();

		if (!(Utils.nextChance() <= weapon.chanceToHit())) {
			return actor + " misses " + target + ".";
		}

		int damage;
		if (actor.hasCapability(Status.INSTA_KILL)) {            // Mario consumes POWERSTAR and can insta kill enemies(target)
			damage = Integer.MAX_VALUE;
		} else if (target.hasCapability(Status.IMMUNITY)) {        // Mario(target) consumes POWERSTAR and have immunity against enemies'(actor) attacks
			damage = 0;
		} else {
			damage = weapon.damage();
		}

		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
		target.hurt(damage);

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
			else {
				// if target is indestructible but not conscious, then it is dormant
				target.addCapability(Status.DORMANT);
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
