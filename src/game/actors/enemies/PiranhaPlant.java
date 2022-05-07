package game.actors.enemies;

public class PiranhaPlant extends Enemy {
    // Constants
    private static final String NAME = "Piranha Plant";
    private static final char DISPLAY_CHAR = 'Y';
    private static final int START_HEALTH = 150;

    /**
     * Constructor.
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public PiranhaPlant(String name, char displayChar, int hitPoints) {
        super(NAME, DISPLAY_CHAR, START_HEALTH);
    }
}
