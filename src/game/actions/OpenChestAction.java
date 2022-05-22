package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.friendlies.Player;
import game.ground.chests.Chest;

/**
 * Action which, upon assessing that actor has enough credits, takes away credits from the actor and rolls a random item from that Chest's ItemPool,
 * dropping it onto the actor's location.
 * @author James Huynh, Matthew Siegenthaler
 * @version 1.1
 */
public class OpenChestAction extends Action {
    // Attributes
    private Chest chest;

    /**
     * Action to purchase and open a chest
     * @param chest chest actor is opening
     */
    public OpenChestAction(Chest chest) {
        this.chest = chest;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        // check if actor has enough currency
        Player buyer = (Player) actor;
        int cost = chest.getCost();

        if (buyer.getWallet().isAffordable(cost)) {
            buyer.getWallet().subCredit(cost); // take away cost from wallet
            // drop an item on actor
            Item itemDrop = chest.getItem();
            map.locationOf(actor).addItem(itemDrop);

            return menuDescription(actor);
        }
        return buyer + " doesn't have enough credits to open the " + chest.getChestType() + "!";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " opens the " + chest.getChestType() + " for $" + chest.getCost();
    }
}
