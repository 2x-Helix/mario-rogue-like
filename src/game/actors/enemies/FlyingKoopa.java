package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.SuicideAction;
import game.status.Status;

public class FlyingKoopa extends Enemy implements Shell {
    public FlyingKoopa() {
        super("Flying Koopa", 'F', 150);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // If marked for reset, remove from map via suicide.
        if (hasCapability(Status.RESET)){
            return new SuicideAction();
        }
        return null;
    }
}
