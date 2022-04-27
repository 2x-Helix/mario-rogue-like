package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * An Action that doesn't do anything.  
 * 
 * Use this to implement waiting or similar actions in game clients.
 */
public class StationaryBehaviour extends Action implements Behaviour {
	@Override
	public Action getAction(Actor actor, GameMap map){
		return new DoNothingAction();
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		return "Raagrh...";
	}
}
