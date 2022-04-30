package game.actors.friendly;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.displays.Menu;
import game.actions.ResetAction;
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
public class Player extends Actor implements Resettable {

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
		WalletManager.getInstance().createWallet(this);
	}

	@Override
	public char getDisplayChar(){
		return this.hasCapability(Status.TALL) ? Character.toUpperCase(super.getDisplayChar()): super.getDisplayChar();
	}

	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

		StatusManager.getInstance().tick();	// tick for statuses
		
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
		return StatusManager.getInstance().getDescription(this);
	}

}
