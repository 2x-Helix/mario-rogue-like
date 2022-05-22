package game.ground.teleporters;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.WarpAction;
import game.actors.enemies.PiranhaPlant;
import game.ground.HighGround;
import game.reset.Resettable;
import game.status.Status;

/**
 * Allows an actor to teleport to another WarpPipe
 * @author Matthew Siegenthaler
 * @version 1.0
 */
public class WarpPipe extends HighGround implements Resettable {
    // Constants
    private static final char DISPLAY_CHAR = 'C';
    private static final int JUMP_SUCCESS_RATE = 100;
    private static final int FALL_DAMAGE = 0;

    // Attributes
    private Location destination;
    private String direction;
    private WarpPipe exitPipe;

    // Constructors
    /**
     * Warp pipe with no specified destination
     */
    public WarpPipe() {
        super(DISPLAY_CHAR, JUMP_SUCCESS_RATE, FALL_DAMAGE);
        registerResettable();
        this.addCapability(Status.RESET);  // Spawn PiranhaPlant on turn 1
    }

    /**
     * Warp pipe with specified destination
     */
    public WarpPipe(Location destination, WarpPipe exitPipe, String direction) {
        super(DISPLAY_CHAR, JUMP_SUCCESS_RATE, FALL_DAMAGE);
        setExit(destination, exitPipe);  //  direction
        this.direction = direction;
        registerResettable();
        this.addCapability(Status.RESET);  // Spawn PiranhaPlant on turn 1
    }

    /**
     * Private warp pipe method to be used in factory method
     * @param pipe WarpPipe to clone.
     */
    private WarpPipe(WarpPipe pipe) {
        super(DISPLAY_CHAR, JUMP_SUCCESS_RATE, FALL_DAMAGE);
        setExit(pipe.getDestination(), pipe.getExitPipe());  //  direction
        this.direction = pipe.getDirection();
        registerResettable();
        this.addCapability(Status.RESET);  // Spawn PiranhaPlant on turn 1
    }

    /**
     * Gets the Location of the exit destination.
     * @return Location exit of the warp pipe
     */
    public Location getDestination() {
        return destination;
    }

    /**
     * Getter for ground WarpPipe of exit.
     * @return WarpPipe exit of the current warp pipe.
     */
    public WarpPipe getExitPipe() {
        return exitPipe;
    }

    /**
     * Getter for direction of exit
     * @return String of direction of exit of WarpPipe
     */
    public String getDirection() {
        return direction;
    }


    /**
     * Setter for destination and direction
     * @param destination Location an actor teleports to
     */
    public void setExit(Location destination, WarpPipe exitPipe) {
        this.destination = destination;
        this.exitPipe = exitPipe;
    }

    // Methods
    /**
     * Allows player to warp
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return List of actions an actor can perform
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = super.allowableActions(actor, location, direction);

        // If actor on WarpPipe, allow them to warp
        if (actor.equals(location.getActor()) && destination != null) {
            actions.add(new WarpAction(this));
        }
        return actions;
    }

    /**
     * Spawn PiranhaPlant if marked for reset.
     */
    @Override
    public void tick(Location location) {
        super.tick(location);

        // Spawn PiranhaPlant if no actor on tile
        if (this.hasCapability(Status.RESET)) {
            if (!location.containsAnActor()) {
                location.addActor(new PiranhaPlant());
            }
            this.removeCapability(Status.RESET);
        }
    }

    /**
     * Mark Warp pipe for reset to spawn PiranhaPlant.
     */
    @Override
    public void resetInstance() {
        this.addCapability(Status.RESET);
    }

    /**
     * Factory method for cloning a pipe
     * @return new WarpPipe instance with the same exit as input.
     */
    public WarpPipe newGround() {
        return new WarpPipe(this);
    }
}