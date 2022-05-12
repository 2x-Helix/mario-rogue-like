package game.actors.friendlies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.EndGameAction;
import game.status.Status;

public class PrincessPeach extends Friendly {
    protected PrincessPeach() {
        super("Princess Peach", 'P', Integer.MAX_VALUE);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if(!this.hasCapability(Status.LOCKED)){
            return new EndGameAction();
        }
        return new DoNothingAction();
    }
}
