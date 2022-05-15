package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Utils;
import game.actions.AttackAction;
import game.actions.SuicideAction;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.status.Status;

/**
 * A little fungus guy, Enemy - Goomba.
 * modified by James Huynh
 * @version 3.0
 */
public class Goomba extends Enemy {

	// Constructor
	public Goomba() {
		super("Goomba", 'g', 20);
	}

	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		if (this.hasCapability(Status.POWERFUL))
			return new IntrinsicWeapon(25, "kicks");
		return new IntrinsicWeapon(10, "kicks");
	}

	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		// If marked for reset, remove from map via suicide.
		if (hasCapability(Status.RESET)){
			return new SuicideAction();
		}

		// Chance to self-destruct, 10%
		if(Utils.nextChance() <= 10) {
			return new SuicideAction();
		}

		// checks if lastAction was AttackAction and behaviours doesn't contain FollowBehaviour already
		if(lastAction instanceof AttackAction && !behaviours.containsKey(2)) {
			// adds FollowBehaviour, targeting the actor the current actor called AttackAction on
			this.behaviours.put(2, new FollowBehaviour(((AttackAction) lastAction).getTarget()));
		}

		for(Behaviour behaviour : behaviours.values()) {
			Action action = behaviour.getAction(this, map);
			if (action != null)
				return action;
		}

		return new DoNothingAction();
	}

	/**
	 * At the moment, we only make it can be attacked by Player.
	 * You can do something else with this method.
	 * @param otherActor the Actor that might perform an action.
	 * @param direction  String representing the direction of the other Actor
	 * @param map        current GameMap
	 * @return list of actions
	 * @see Status #HOSTILE_TO_ENEMY
	 */
	@Override
	public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
		ActionList list = new ActionList();
		// it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
		if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
			list.add(new AttackAction(this,direction));
		}
		return list;
	}
}
