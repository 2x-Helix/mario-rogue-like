package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.ItemManager;
import game.items.magical_items.PowerStar;
import game.items.magical_items.SuperMushroom;
import game.wallet.WalletManager;

/**
 * Allow Purchasable items to be purchased
 * @author ChunKau Mok (Peter)
 * @version 1.0
 */
public class PurchaseAction extends Action{

    private Item item;
    private Actor seller;

    /**
     * Public constructor of this action
     * @param item is the item to be sold/bought
     * @param actor the seller of the item
     */
    public PurchaseAction(Item item, Actor actor) {
        this.item = item;
        this.seller = actor;
    }

    /**
     * Called upon this action is executed
     * @param actor is the buyer of the item
     * @param map is the gamemap the actor on
     * @return a descriptive string of the result of this action
     */
    @Override
    public String execute(Actor buyer, GameMap map) {
        WalletManager walletManager = WalletManager.getInstance();
        try {
            Integer itemPrice = ItemManager.getInstance().getPrice(item);
            if (walletManager.canAfford(buyer, itemPrice)) {
                walletManager.subCredit(buyer, itemPrice);
                walletManager.addCredit(seller, itemPrice);
                seller.removeItemFromInventory(this.item);
                buyer.addItemToInventory(this.item);
                if (this.item instanceof PowerStar || this.item instanceof SuperMushroom)   // FIXME: stinky, undroppable after purchased
                    this.item.togglePortability();  
                return this.menuDescription(buyer);
            } else {
                return "You don't have enough coins!";
            }
        } catch (Exception e) {
            return e.toString();
        }
    }

    /**
     * @param actor is the buyer of this purchase
     * @return a string containing the info of this purcahse action
     */
    @Override
    public String menuDescription(Actor actor) {
        try {
            return actor + " bought " + (Item) item + " from " + this.seller + " for $" + ItemManager.getInstance().getPrice(item);
        } catch (Exception e) {
            return e.toString();
        }
    }
    
}
