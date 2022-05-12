package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
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
}
