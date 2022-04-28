package game.actions;

import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.ground.HighGround;

/**
 * Allows players to jump
 * @author James Huynh
 * @author ChunKau Mok
 * @version 2.0
 */
public class JumpActorAction extends MoveActorAction {
    
    /**
     * Constructor for JumpActorAction
     * @param moveToLocation is the location actor tries to jump to
     * @param direction is the direction the actor jumping
     * @param hotKey is the hot key for this action
     */
    public JumpActorAction(Location moveToLocation, String direction, String hotKey) {
        super(moveToLocation, direction, hotKey);
    }

    /**
     * Execute whenever player tries to jump
     * @return a menu description of the result of the jump action
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        // FIXME: Stinky
        if (moveToLocation.getGround() instanceof HighGround) {
            HighGround downCast = (HighGround) moveToLocation.getGround();
            if (downCast.onJump(actor)) {
                map.moveActor(actor, moveToLocation);
                return this.menuDescription(actor);
            } else {
                return actor + " fails to jump, takes " + downCast.getFallDamage().toString() + "fall damage.";
            }
        } else {
            return this.menuDescription(actor);
        }

    }

    /**
     * Returns a description of this movement suitable to display in the menu.
     *
     * @param actor The actor performing the action.
     * @return a String, e.g. "Player jumps east"
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " jumps " + direction;
    }

}
