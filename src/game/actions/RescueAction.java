package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class RescueAction extends Action {
    private final Actor freed;

    public RescueAction(Actor freed) {
        this.freed = freed;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        return menuDescription(actor);
    }
    @Override
    public String menuDescription(Actor actor) {
        return "Congratulations," + actor + " has freed " + freed + "!";
    }
}
