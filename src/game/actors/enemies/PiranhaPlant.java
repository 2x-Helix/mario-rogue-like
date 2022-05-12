package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;

public class PiranhaPlant extends Enemy {
    // Constants
    private static final String NAME = "Piranha Plant";
    private static final char DISPLAY_CHAR = 'Y';
    private static final int START_HEALTH = 150;
    private static final int BONUS_HEALTH = 50;

    /**
     * Constructor.
     */
    public PiranhaPlant() {
        super(NAME, DISPLAY_CHAR, START_HEALTH);
        registerResettable();
    }

    /**
     * Piranha plant to fully heal and receive bonus health.
     */
    @Override
    public void resetInstance() {
        this.increaseMaxHp(BONUS_HEALTH);   // Bonus health on reset
        this.heal(getMaxHp());              // Fully heal
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return null;
    }
}
