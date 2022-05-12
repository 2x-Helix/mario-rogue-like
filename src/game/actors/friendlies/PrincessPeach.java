package game.actors.friendlies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.RescueAction;
import game.status.Status;

public class PrincessPeach extends Friendly {
    protected PrincessPeach() {
        super("Princess Peach", 'P', Integer.MAX_VALUE);
        this.addCapability(Status.LOCKED);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);  // Get parents action list
        if(this.hasCapability(Status.LOCKED) && otherActor.hasCapability(Status.HAS_KEY)){
            actions.add(new RescueAction(this));  // If otherActor has a key, can use UnlockAction on Peach
        }
        return actions;
    }
}
