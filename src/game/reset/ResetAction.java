package game.reset;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Resets all resettable objects on the map.
 * @author Matthew Siegenthaler
 * @version 1.0
 */
public class ResetAction extends Action {
    /**
     * Calls ResetManager to perform the reset on all resettable items.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String menu description describing the action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        ResetManager.getInstance().run();  // Executes reset actions for all
        return menuDescription(actor);
    }

    /**
     * Returns a description of the action.
     * @param actor The actor performing the action.
     * @return String description of the action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " resets the game";
    }

    /**
     * Hotkey for action
     * @return hotkey 'r'
     */
    @Override
    public String hotkey() {
        return "r";
    }
}
