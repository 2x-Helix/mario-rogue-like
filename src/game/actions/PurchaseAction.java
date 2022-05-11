package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.friendlies.Player;
import game.items.Purchasable;

/**
 * Allow Purchasable items to be purchased
 * @author ChunKau Mok (Peter)
 * @version 1.0
 */
public class PurchaseAction extends Action{

    private final Purchasable item;
    private final Actor seller;

    /**
     * Public constructor of this action
     * @param item is the item to be sold/bought
     * @param actor the seller of the item
     */
    public PurchaseAction(Purchasable item, Actor actor) {
        this.item = item;
        this.seller = actor;
    }

    /**
     * Called upon this action is executed
     * @param actor is the buyer of the item
     * @param map is the GameMap the actor on
     * @return a descriptive string of the result of this action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Player buyer = (Player) actor;
        if (buyer.getWallet().isAffordable(item.getPrice())) {
            buyer.getWallet().subCredit(item.getPrice());
            buyer.addItemToInventory((Item) item);
            seller.removeItemFromInventory((Item) item);
            return menuDescription(buyer);
        }
        return buyer + "can not afford" + item;
    }

    /**
     * @param actor is the buyer of this purchase
     * @return a string containing the info of this purchase action
     */
    @Override
    public String menuDescription(Actor actor) {
        try {
            return actor + " buys " + item + " from " + seller + " for $" + item.getPrice();
        } catch (Exception e) {
            return e.toString();
        }
    }
    
}
