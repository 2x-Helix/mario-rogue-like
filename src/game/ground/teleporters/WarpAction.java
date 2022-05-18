package game.ground.teleporters;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 *
 */
public class WarpAction extends Action {
    // Attributes
    private WarpPipe entrancePipe;

    // Constructors
    public WarpAction(WarpPipe entrancePipe) {
        this.entrancePipe = entrancePipe;
    }

    /**
     * Move actor from current location to destination
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String menu description of action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Location destination = entrancePipe.getDestination();
        WarpPipe exitPipe = entrancePipe.getExitPipe();

        // Kill actor standing at destination
        if (destination.containsAnActor())
            destination.map().removeActor(destination.map().getActorAt(destination));

        // Set return destination of exit pipe to entrance pipe location
        exitPipe.setExit(map.locationOf(actor), entrancePipe);

        map.moveActor(actor, destination);

        return menuDescription(actor);
    }

    /**
     * Message to display to console
     * @param actor The actor performing the action.
     * @return String display message
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " warps to " + entrancePipe.getDirection();
    }
}
