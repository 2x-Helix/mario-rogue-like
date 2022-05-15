package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.ground.fountains.Fountain;

/**
 * A Behaviour that allows enemy to drink water from fountain
 * if they are standing on it
 */
public class DrinkBehaviour implements Behaviour {

    @Override
    public Action getAction(Actor actor, GameMap map) {

        // actor not on fountain
        if ( !(map.locationOf(actor).getGround() instanceof Fountain) )
            return null;

        // FIXME: stinky
        Fountain here = (Fountain) (map.locationOf(actor).getGround());

        // yes drink when no empty; no drink when empty
        try {
            return here.isEmpty()? null : here.getDrinkAction();
        } catch (Exception e) {
            e.printStackTrace();    // should not be the case
            return null;
        }

    }
}
