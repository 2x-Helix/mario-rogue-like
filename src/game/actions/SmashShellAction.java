package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.items.magical_items.SuperMushroom;
import game.items.weapon_items.Wrench;

public class SmashShellAction extends Action {
	private Actor target;

	/**
	 * Constructor
	 * @param target the actor being targeted
	 */
	public SmashShellAction(Actor target) {
		this.target = target;
	}

	/**
	 * Performs SmashShellAction
	 *
	 * @param actor The actor performing the action.
	 * @param map   The map the actor is on.
	 * @return a description of what happened that can be displayed to the user.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		// check if actor carries a Wrench
		if(actor.getWeapon() instanceof Wrench) {
			// check if target is in dormant state
			if(target.hasCapability(Status.DORMANT)) {
				// drop SuperMushroom
				map.locationOf(target).addItem(new SuperMushroom());
				// instantly destroy target
				map.removeActor(target);
				return menuDescription(actor);
			}
		}
		return menuDescription(actor);
	}

	/**
	 * Returns a descriptive string
	 *
	 * @param actor The actor performing the action.
	 * @return the text we put on the menu
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " destroys " + target;
	}
}