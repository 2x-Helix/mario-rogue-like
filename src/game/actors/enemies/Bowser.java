package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.AttackAction;
import game.actions.SuicideAction;
import game.status.Status;

public class Bowser extends Enemy {
    public Bowser() {
        super("Bowser", 'B', 500);
        behaviours.remove(10); // remove wanderbehaviour
    }

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(80, "punches");
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // If marked for reset, remove from map via suicide.
        if (hasCapability(Status.RESET)){
            this.heal(this.getMaxHp());
            //map.moveActor(this, //LOCATION HERE);
        }
        return lastAction;
    }

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
