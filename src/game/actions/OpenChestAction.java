package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.friendlies.Player;
import game.ground.Chest;
import game.status.Status;

public class OpenChestAction extends Action {
    private Item item;
    private Chest chest;

    public OpenChestAction(Chest getChest) {
        chest = getChest;
        item = chest.getItemPool().rollItem();
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        // check if actor has enough currency ($200)
        Player buyer = (Player) actor;
        if (buyer.getWallet().isAffordable(200)) {
            buyer.getWallet().subCredit(200); // take away $200 from wallet
            // drop an item on actor
            map.locationOf(actor).addItem(item);

            // change item stored in chest
            this.item = chest.getItemPool().rollItem();

            chest.decreaseDurability();
            if (chest.getDurability() <= 0) {
                chest.addCapability(Status.RESET); // mark chest for reset
            }
            return menuDescription(actor);
        }
        return buyer + " doesn't have enough credits to open this chest!";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor +  " opens the chest for $200!";
    }
}
