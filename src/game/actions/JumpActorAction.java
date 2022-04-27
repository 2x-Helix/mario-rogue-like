package game.actions;

import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.Utils;
import game.actors.friendly.Player;
import game.ground.Jumpable;
import game.ground.Wall;

public class JumpActorAction extends MoveActorAction {
    // Constructor for JumpActorAction
    public JumpActorAction(Location moveToLocation, String direction, String hotKey) {
        super(moveToLocation, direction, hotKey);
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        // Check if  Actor is Player before allowing jump action
        if(actor instanceof Player) {
            // Check if target ground is jumpable:
            if(moveToLocation.getGround() instanceof Jumpable) {
                // Cases before allowing player to jump:
                // Case 1: Player has a Super Mushroom status, 100% success:
                if (actor.hasCapability(Status.EASY_JUMP)) {
                    map.moveActor(actor, moveToLocation);
                    return menuDescription(actor);
                }
                // Case 2: The jumpable ground is a wall, 80% success rate, 20 fall damage
                if (moveToLocation.getGround().equals(new Wall())) {
                    if (Utils.nextChance() <= 80) {
                        map.moveActor(actor, moveToLocation);
                        return menuDescription(actor);
                    } else {
                        actor.hurt(20);
                    }
                }
                // Case 3: The jumpable ground is a sprout, 90% success rate, 10 fall damage
                if (moveToLocation.getGround().equals(new Sprout())) {
                    if (Utils.nextChance() <= 90) {
                        map.moveActor(actor, moveToLocation);
                        return menuDescription(actor);
                    } else {
                        actor.hurt(10);
                    }
                }
                // Case 4: The jumpable ground is a sapling, 80% success rate, 20 fall damage
                if (moveToLocation.getGround().equals(new Sapling())) {
                    if (Utils.nextChance() <= 80) {
                        map.moveActor(actor, moveToLocation);
                        return menuDescription(actor);
                    } else {
                        actor.hurt(20);
                    }
                }
                // Case 5: The jumpable ground is a mature tree, 70% success rate,  30 fall damage
                if (moveToLocation.getGround().equals(new Mature())) {
                    if (Utils.nextChance() <= 70) {
                        map.moveActor(actor, moveToLocation);
                        return menuDescription(actor);
                    } else {
                        actor.hurt(30);
                    }
                }
            }
        }
        return actor + " cannot jump " + direction;
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
