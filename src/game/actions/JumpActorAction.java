package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.ground.HighGround;

/**
 * Allows players to jump
 * @author James Huynh
 * @author ChunKau Mok
 * @version 2.0
 */
public class JumpActorAction extends Action {

    private final HighGround highGround;
    private final Location moveToLocation;
    private final String direction;

    /**
     * Constructor for JumpActorAction
     * @param highGround is the ground actor tries to jump to
     */
    public JumpActorAction(HighGround highGround, Location moveToLocation, String direction) {
        this.highGround = highGround;
        this.moveToLocation = moveToLocation;
        this.direction = direction;
    }

    /**
     * Execute whenever player tries to jump
     * @return a menu description of the result of the jump action
     */
    public String execute(Actor actor, GameMap map) {

        if (highGround.onJump(actor)) {
            map.moveActor(actor, moveToLocation);
            String coords = "(" + moveToLocation.x() + ", " + moveToLocation.y() + ")";
            return this.menuDescription(actor) + " to " + coords;
        } else {
            return actor + " fails to jump, take " + highGround.getFallDamage().toString() + " fall damage.";
        }
    }

    /**
     * Returns a description of this movement suitable to display in the menu.
     *
     * @param actor The actor performing the action.
     * @return a String, e.g. "Player jumps east"
     */
    public String menuDescription(Actor actor) {
        return actor + " jumps " + direction;
    }

}
