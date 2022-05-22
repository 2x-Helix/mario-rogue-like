package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.status.Status;

public class RescueAction extends Action {
    private final Actor freed;

    public RescueAction(Actor freed) {
        this.freed = freed;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        freed.removeCapability(Status.LOCKED);
        map.removeActor(actor);

        return "Congratulations, " + actor + " is victorious, " + freed + " has been saved!!!";
    }
    @Override
    public String menuDescription(Actor actor) {
        return actor + " frees " + freed;
    }
}
