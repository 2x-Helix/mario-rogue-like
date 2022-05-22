package game.items.equipments;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.Optional;

/**
 * Equipment to heal player 50hp
 */
public class Fruit extends Equipment {

    public static final String NAME = "Fruit";
    public static final char DISPLAY_CHAR = ' ';
    public static final boolean PORTABLE = true;

    /***
     * Public constructor for this equipment
     */
    public Fruit() {
        super(NAME, DISPLAY_CHAR, PORTABLE, 5);
    }

    /**
     * Heal user's hp by 50
     * @param actor the actor using this equipment
     * @param map the GameMap actor is on
     * @throws Exception if this equipment is on cool down
     */
    public void onUse(Actor actor, GameMap map) throws Exception {
        super.onUse(actor, map);
        actor.heal(50);
    }

    @Override
    public String toString() {
        return NAME;
    }

}
