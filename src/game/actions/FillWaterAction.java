package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.ground.fountains.Fountain;
import game.items.magical_items.bottles.Bottle;

/**
 * Action to fill bottle from fountain
 * @author ChunKau Mok
 * @version 1.0
 */
public class FillWaterAction extends Action {

    private final Fountain fountain;

    /**
     * Public constructor for this action
     */
    public FillWaterAction(Fountain fountain) {
        this.fountain = fountain;
    }

    /**
     * Called whenever player fills a bottle with water the foundation
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of this action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        try {
            fountain.onFill(actor);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " refills bottle from " + fountain + " (" + fountain.volume() + "/" + fountain.capacity() + ")";
    }
}
