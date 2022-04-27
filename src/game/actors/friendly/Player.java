package game.actors.friendly;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ConsumeAction;
import game.status.Status;
import game.status.StatusManager;

/**
 * Class representing the Player.
 */
public class Player extends Actor  {

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
	}

	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

		StatusManager statusManager = StatusManager.getStatusManager();
		
		statusManager.tick();	// tick for statuses

		// TODO: fix this stinky poopo
		for (Item item : this.getInventory()) {
			if (item.getClass().getSuperclass().getSimpleName() == "MagicalItem") {
				actions.add(new ConsumeAction(item));
			}
		}
		
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		if (this.capabilitiesList().contains(Status.IMMUNITY)) {
			String cout = "Mario consumes Power Star - " + statusManager.getStatusDuration(this, Status.IMMUNITY);
			if (statusManager.getStatusDuration(this, Status.IMMUNITY) > 1) {
				cout += " turns remaining";
			} else {
				cout += " turn remaining";
			}
			System.out.println(cout);
		}

		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	@Override
	public char getDisplayChar(){
		return this.hasCapability(Status.TALL) ? Character.toUpperCase(super.getDisplayChar()): super.getDisplayChar();
	}

}
