package game.actors.friendlies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.displays.Menu;
import game.reset.ResetAction;
import game.items.magical_items.PowerStar;
import game.reset.ResetManager;
import game.reset.Resettable;
import edu.monash.fit2099.engine.positions.GameMap;
import game.status.Status;
import game.status.StatusManager;
import game.wallet.WalletManager;

/**
 * Class representing the Player.
 */
public class Player extends Friendly implements Resettable {

	private final Menu menu = new Menu();

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		WalletManager.getInstance().createWallet(this);
		registerResettable();
	}

	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

		StatusManager.getStatusManager().tick();	// tick for statuses

		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		// Check if reset is available
		if (ResetManager.getInstance().resetAvailable())
			actions.add(new ResetAction());

		System.out.print(this.statusDescription());

		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	@Override
	public char getDisplayChar(){
		return this.hasCapability(Status.TALL) ? Character.toUpperCase(super.getDisplayChar()): super.getDisplayChar();
	}

	/**
	 * Upon reset: Reset duration of powerstar, Heals to max hp
	 */
	@Override
	public void resetInstance() {
		heal(getMaxHp());  // Heal to max hp

		// Reset Powerstar duration if under the effects
		if (hasCapability(Status.IMMUNITY)) {
			new PowerStar().onConsume(this);
		}
	}

	/**
	 * @return a description of statuses this player has
	 */
	private String statusDescription() {

		StatusManager statusManager = StatusManager.getStatusManager();

		if (this.capabilitiesList().contains(Status.IMMUNITY)) {
			String cout = "Mario consumes Power Star - " + statusManager.getStatusDuration(this, Status.IMMUNITY);
			if (statusManager.getStatusDuration(this, Status.IMMUNITY) > 1) {
				cout += " turns  \n";
			} else {
				cout += " turn remaining \n";
			}
			return cout;
		}
		return "";
	}

}
