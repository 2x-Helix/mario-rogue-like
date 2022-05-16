package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.equipments.Equipment;

/**
 * Allows players to use equipments
 */
public class UseAction extends Action {

    // The equipment to be used
    private final Equipment equipment;

    /**
     * Public constructor for this action
     * @param equipment the equipment to be used
     */
    public UseAction(Equipment equipment) {
        this.equipment = equipment;
    }

    /**
     * Called whenever an actor uses an equipment
     * @param actor The actor using an equipment
     * @param map The map the actor is on
     * @return a description of the action
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        try {
            equipment.onUse(actor, map);
        } catch (Exception e) {
            return e.toString();
        }

        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " uses " + equipment;
    }

}
