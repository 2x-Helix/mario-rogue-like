package game.ground;

public class WarpPipe extends HighGround {
    // Constants
    private static final char DISPLAY_CHAR = 'C';
    private static final int JUMP_SUCCESS_RATE = 100;
    private static final int FALL_DAMAGE = 0;

    /**
     * Constructor.
     * Spawns a Piranha plant initially
     */
    public WarpPipe() {
        super(DISPLAY_CHAR, JUMP_SUCCESS_RATE, FALL_DAMAGE);
    }
}
