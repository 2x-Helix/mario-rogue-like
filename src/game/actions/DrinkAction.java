package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.ground.fountains.Fountain;
import game.status.Status;

public class DrinkAction extends Action {

    private final Fountain fountain;

    /**
     * @param fountain the fountain enemies drink from
     */
    public DrinkAction(Fountain fountain) {
        this.fountain = fountain;
    }

    /**
     * Called when this action is executed, i.e. enemies drink from fountain
     * @param actor The enemy drinking from the fountain
     * @param map The map the actor is on.
     * @return a description of the action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        fountain.onDrink(actor);
        return menuDescription(actor);
    }

    /**
     * @param actor The actor performing the action.
     * @return a description of the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " drinks from " + fountain;
    }
}
