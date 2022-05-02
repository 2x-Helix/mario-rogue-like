package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;

/**
 * A class that automatically calls AttackAction on the actor's surroundings if there is another actor.
 * @author James Huynh
 * @version 1.0
 */


public class AttackBehaviour implements Behaviour {

    @Override
    public Action getAction(Actor actor, GameMap map) {
        // get location of current actor
        Location here = map.locationOf(actor);

        // iterate through all current actor's exits
        for (Exit exit : here.getExits()) {
            // check if an exit contains an actor
            if(exit.getDestination().containsAnActor()) {
                // current actor calls AttackAction on the other actor
                return new AttackAction(exit.getDestination().getActor(), exit.getName());
            }
        }
        // no action is called
        return null;
    }
}
