package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.AttackAction;
import game.behaviours.Behaviour;
import game.items.ItemPool;
import game.items.magical_items.SuperMushroom;
import game.items.weapon_items.Wrench;
import game.status.Status;

import java.util.ArrayList;

/**
 * This DisguisedChest class represents an Enemy actor that is disguised as a chest, however, upon proximity to a Friendly actor,
 * will attack it, and change its display character to its undisguised form, 'e'. Upon death, the enemy will drop a random item on the actor's
 * location.
 * @author James Huynh, Matthew Siegenthaler
 * @version 1.2
 */
public class DisguisedChest extends Enemy {

    /**
     * Constructor for enemy chest
     */
    public DisguisedChest() {
        super("Disguised Chest", 'c', 100);
        behaviours.remove(10); // remove WanderBehaviour
        behaviours.remove(3); // remove DrinkBehaviour

        this.addItemToInventory(getItemDrop()); // roll a random item

        // iterate through all DisguisedChest's capabilities and remove them, ensures items don't have an effect on DisguisedChest.
        for(int i=0; i<this.getInventory().get(0).capabilitiesList().size(); i++) {
            this.removeCapability(this.getInventory().get(0).capabilitiesList().get(i));
        }
    }

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(15, "bites");
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if(lastAction instanceof AttackAction) {
            this.setDisplayChar('e'); // After DisguisedChest attacks, it changes into its undisguised form, 'e'.
        }
        // Remove effects of Items it is carrying before attacking
        // get behaviour actions
        for(Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        // do nothing at this turn
        return new DoNothingAction();
    }

    private Item getItemDrop() {
        // Item drops
        ArrayList<Item> drops = new ArrayList<>();
        drops.add(new SuperMushroom());
        drops.add(new Wrench());

        return new ItemPool(drops).rollItem();
    }
}
