package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.ground.Chest;

public class OpenChestAction extends Action {
    private Item item;
    private Chest chest;

    public OpenChestAction(Chest getChest) {
        chest = getChest;
        item = chest.getItemPool().rollItem();
    }
    @Override
    public String execute(Actor actor, GameMap map) {
        // drop an item on actor
        map.locationOf(actor).addItem(item);
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return chest +  " drops a " + item + "!!!";
    }
}
