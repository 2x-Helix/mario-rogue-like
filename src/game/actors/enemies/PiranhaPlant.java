package game.actors.enemies;

public class PiranhaPlant extends Enemy {
    // Constants
    private static final String NAME = "Piranha Plant";
    private static final char DISPLAY_CHAR = 'Y';
    private static final int START_HEALTH = 150;

    /**
     * Constructor.
     */
    public PiranhaPlant() {
        super(NAME, DISPLAY_CHAR, START_HEALTH);
    }
}
