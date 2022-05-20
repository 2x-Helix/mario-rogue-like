package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.OpenChestAction;
import game.items.ItemPool;
import game.reset.Resettable;
import game.status.Status;

public class Chest extends Ground implements Resettable {

    private ItemPool loot = new ItemPool();

    /**
     * Constructor.
     */
    public Chest() {
        super('C');
    }

    public ItemPool getItemPool() {
        return loot;
    }

    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        if(actor.getClass().getName() == "Player") { // only offer Player OpenChestAction
            actions.add(new OpenChestAction(this));
        }
        return actions;
    }

    @Override
    public void resetInstance() {
        this.addCapability(Status.RESET);
    }
}
