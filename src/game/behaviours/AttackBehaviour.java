package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;
import game.actors.Player;

public class AttackBehaviour implements Behaviour{
    private final Actor target;
    public AttackBehaviour(Actor subject) {
        this.target = subject;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        // check if map contains the target
        if(!map.contains(target) || !map.contains(actor))
            return null;

        // get location of current actor
        Location here = map.locationOf(actor);

        // iterate through all current actor's exits
        for (Exit exit : here.getExits()) {
            // check if an exit contains an actor
            if(exit.getDestination().containsAnActor()) {
                // check if the actor is Player
                if(exit.getDestination().getActor() instanceof Player) {
                    // check if actor can enter the exit's area
                    if(exit.getDestination().canActorEnter(actor)) {
                        // current actor calls AttackAction on the target actor
                        return new AttackAction(target, exit.getName());
                    }
                }
            }
        }
        // no action is called
        return null;
    }
}
