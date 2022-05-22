package game.actors.friendlies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.items.equipments.Equipment;
import game.items.magicalitems.PowerStar;
import game.reset.ResetAction;
import game.reset.ResetManager;
import game.reset.Resettable;
import game.status.Status;
import game.Wallet;

/**
 * Class representing the Player.
 */
public class Player extends Friendly implements Resettable {

	private final Menu menu = new Menu();
	private final Wallet wallet = new Wallet();

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
		registerResettable();
	}

	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		// Check if reset is available
		if (ResetManager.getInstance().resetAvailable())
			actions.add(new ResetAction());

		// Display player's equipment info FIXME: loop inside
		System.out.print(this.equipmentDescription());

		// Display player's statuses info
		this.statusDescription();

		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	/**
	 * @return the wallet of this player
	 */
	public Wallet getWallet() { return this.wallet;}

	/**
	 * Creates and returns an intrinsic weapon.
	 * If player has the status POWERFUL, base attack damage +15
	 *
	 * @return a freshly-instantiated IntrinsicWeapon
	 */
	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		if (this.hasCapability(Status.POWERFUL))
			return new IntrinsicWeapon(super.getIntrinsicWeapon().damage()+15,super.getIntrinsicWeapon().verb());

		return super.getIntrinsicWeapon();
	}

	/**
	 * Upon reset: Reset duration of PowerStar, Heals to max hp
	 */
	@Override
	public void resetInstance() {
		heal(getMaxHp());  // Heal to max hp

		// Reset PowerStar duration if under the effects
		if (hasCapability(Status.IMMUNITY)) {
			new PowerStar().onConsume(this);
		}
	}

	/**
	 * @return a description of statuses this player has
	 */
	private String statusDescription() {
		return "";
	}

	/**
	 * @return a description of equipment this player holds
	 */
	private String equipmentDescription() {
		StringBuilder cout = new StringBuilder();
		Equipment equipment;
		for (Item item : this.getInventory()) {
			if (item instanceof Equipment) {
				equipment = (Equipment) item;
				if (!equipment.isOnCoolDown()) {
					cout.append(equipment).append(" is ready to use\n");
				} else {
					cout.append(equipment).append(" is on cool down, ").append(equipment.remainingCoolDown());
					cout.append(equipment.remainingCoolDown() == 1 ? " turn " : " turns ");
					cout.append("remaining\n");
				}
			}
		}
		return cout.toString();
	}

	@Override
	public char getDisplayChar(){
		return this.hasCapability(Status.TALL) ? Character.toUpperCase(super.getDisplayChar()): super.getDisplayChar();
	}

}
