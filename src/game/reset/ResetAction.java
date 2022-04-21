package game.reset;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class ResetAction extends Action {
    public ResetAction() {

    }

    @Override
    public String execute(Actor actor, GameMap map) {

        ResetManager.getInstance().run();  // Executes reset actions for all
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " resets the game";
    }

    @Override
    public String hotkey() {
        return "r";
    }
}
