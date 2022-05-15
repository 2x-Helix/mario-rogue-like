package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.behaviours.Behaviour;
import game.status.Status;

public class PiranhaPlant extends Enemy {
    public PiranhaPlant() {
        super("Piranha Plant", 'Y', 150);
        this.behaviours.remove(10); // remove wanderbehaviour
    }

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(90, "chomps");
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (hasCapability(Status.RESET)){
            this.increaseMaxHp(50);
            this.heal(this.getMaxHp());
        }
        for(Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null) {
                return action;
            }
        }
        return new DoNothingAction();
    }
}
