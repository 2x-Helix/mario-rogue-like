package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Utils;
import game.actors.Player;
import game.items.magical_items.PowerStar;
import game.weapons.Wrench;

public class SpeakAction extends Action {

    @Override
    public String execute(Actor actor, GameMap map) {
        // Check if player is executing the action
        if(actor instanceof Player) {
            return menuDescription(actor);
        }
        return actor + " cannot speak to Toad.";
    }

    @Override
    public String menuDescription(Actor actor) {
        // Case 1: Mario has a wrench:
        if(actor.getInventory().contains(new Wrench())){
            return "You might need a wrench to smash Koopa's hard shells.";
        }
        // Case 2: Mario has a power star:
        if(actor.getInventory().contains(new PowerStar())){
            return "You better get back to finding the Power Stars.";
        }
        // Case 3: Randomly pick two dialogues:
        if(Utils.nextChance() <= 50) {
            return "The Princess is depending on you! You are our only hope.";
        }
        else {
            return "Being imprisoned in these walls can drive a fungus crazy :(";
        }
    }
}
