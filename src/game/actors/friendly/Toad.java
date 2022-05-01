package game.actors.friendly;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Utils;
import game.actions.PurchaseAction;
import game.actors.SpeakAction;
import game.actors.Talkable;
import game.items.magical_items.PowerStar;
import game.items.magical_items.SuperMushroom;
import game.items.weapon_items.Wrench;
import game.status.Status;
import game.wallet.WalletManager;

/**
 * Friendly NPC Toad who sells items and talks to the player.
 * @author James Huynh, Matthew Siegenthaler
 * @version 1.1
 */
public class Toad extends Friendly implements Talkable {

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
        WalletManager.getInstance().createWallet(this);
    }

    /**
     * Gets Toad's action for current turn.
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return Action for Toad to perform
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // Toad should remain stationary between turns
        return new DoNothingAction();
    }

    /**
     * Give player the ability to talk to Toad
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return           Action list towards Toad
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);  // Get parents action list
        actions.add(new SpeakAction(this));  // Toad to perform speak action

        // Add store inventory
        for (Item item : this.getInventory()) {
            actions.add(new PurchaseAction(item, this));
        }

        return actions;
    }

    /**
     * Toad's monologue options when spoken to.
     * @param actor The actor being spoken towards in monologue.
     * @return Randomly selected monologue String.
     */
    @Override
    public String getMonologue(Actor actor) {
        List<String> monologueOptions = new ArrayList<>();

        // Determine dialogue options to add to monologue list
        // Display if Player doesn't have Weapon Wrench
        if(!(actor.getWeapon() instanceof Wrench)){
            monologueOptions.add("You might need a wrench to smash Koopa's hard shells.");
        }
        // Display if Player doesn't have powerstar status:
        if(!(actor.hasCapability(Status.IMMUNITY))){
            monologueOptions.add("You better get back to finding the Power Stars.");
        }
        // Options added by default
        monologueOptions.add("The Princess is depending on you! You are our only hope.");
        monologueOptions.add("Being imprisoned in these walls can drive a fungus crazy :(");

        // Select random monologue option
        int indexMonologue = Utils.nextInt(0, monologueOptions.size());
        return monologueOptions.get(indexMonologue);
    }

    /**
     * Return Toads name
     * @return name Toad as String
     */
    @Override
    public String getSpeaker() {
        return this.toString();
    }
}