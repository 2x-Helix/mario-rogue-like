package game.ground;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.Utils;
import game.reset.Resettable;

public class Tree extends Ground implements Resettable {

    /**
     * Constructor.
     *
     */
    public Tree() {
        super('+');
        registerInstance();
    }

    @Override
    public void tick(Location location) {
        // Turn Tree to dirt if has been reset. No further action.
        if (hasCapability(Status.RESET)) {
            location.setGround(new Dirt());
            return;
        }
    }

    /**
     * Tree's have a 50% chance to become dirt upon reset.
     */
    @Override
    public void resetInstance() {
        if (Utils.nextChance() <= 50) {
            addCapability(Status.RESET);  // Tag tree as RES
        }
    }
}
