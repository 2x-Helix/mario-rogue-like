package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.Utils;
import game.status.Status;
import game.status.StatusManager;

/**
 * Special Action for attacking other Actors.
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

		Weapon weapon = actor.getWeapon();

		if (!(Utils.nextChance() <= weapon.chanceToHit())) {
			return actor + " misses " + target + ".";
		}

		int damage = weapon.damage();
		if (actor.hasCapability(Status.INSTA_KILL)) {			// Mario consumes POWERSTAR and can insta kill enemies(target)
			damage = Integer.MAX_VALUE;
		} else if (target.hasCapability(Status.IMMUNITY)) {		// Mario(target) consumes POWERSTAR and have immunity against enemies'(actor) attacks
			damage = 0;
		}
		
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
		target.hurt(damage);
		if (!target.isConscious()) {
			ActionList dropActions = new ActionList();
			// drop all items
			for (Item item : target.getInventory())
				dropActions.add(item.getDropAction(actor));
			for (Action drop : dropActions)
				drop.execute(target, map);
			// remove actor
			map.removeActor(target);
			result += System.lineSeparator() + target + " is killed.";
		} else {
			try {

				if (target.capabilitiesList().contains(Status.TALL)) {
					target.capabilitiesList().remove(Status.TALL);
					StatusManager.getStatusManager().removeStatus(target, Status.TALL);
				}

				if (target.capabilitiesList().contains(Status.EASY_JUMP)) {
					target.capabilitiesList().remove(Status.EASY_JUMP);
					StatusManager.getStatusManager().removeStatus(target, Status.EASY_JUMP);
				}

			} catch (Exception e) {
				e.getStackTrace();
			}

		}

		return result;
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target + " at " + direction;
	}
}
