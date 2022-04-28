package game.actors.friendly;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.magical_items.PowerStar;
import game.items.magical_items.SuperMushroom;
import game.items.weapon_items.Wrench;

/**
 * @TODO: Implement this class
 */
public class Toad extends Friendly{
    
    private static final String NAME = "Toad";
    private static final char DISPLAY_CHAR = 'O';

    /**
     * Public constructor for toad
     */
    public Toad() {
        super(NAME, DISPLAY_CHAR, Integer.MAX_VALUE);   // Toad have practically infinity HP
        this.addItemToInventory(new Wrench());
        this.addItemToInventory(new SuperMushroom());
        this.addItemToInventory(new PowerStar());
    }

    /**
     * Toad will provide PurchaseAction to all adjacent actor
     * but obviously only Player can execute the PurchaseAction
    */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        return new DoNothingAction();
    }
    
}
