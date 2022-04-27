package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 *
 */
public class AttackAction extends Action {

	/**
	 * Perform the Action.
	 *
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a description of what happened that can be displayed to the user.
	 */
	@Override
	public String execute(Actor actor, GameMap map){
		return null;
	}
	
	/**
	 * Returns a descriptive string
	 * @param actor The actor performing the action.
	 * @return the text we put on the menu
	 */
	@Override
	public String menuDescription(Actor actor){
		return null;
	}
	
	/**
	 * Returns the key used in the menu to trigger this Action.
	 *
	 * There's no central management system for this, so you need to be careful not to use the same one twice.
	 * See https://en.wikipedia.org/wiki/Connascence
	 *
	 * @return The key we use for this Action in the menu, or null to have it assigned for you.
	 */
	public String hotkey() {
		return null;
	}
	
	/**
	 * This provides a mechanism for Actions to take more than one turn.
	 * For example, an action can change its state and return itself, or return the next Action in a series.
	 * By default, this returns null, indicating that the Action will complete in one turn.
	 * 
	 * @return null
	 */
	public AttackAction getNextAction() {
		return null;
	}
}
