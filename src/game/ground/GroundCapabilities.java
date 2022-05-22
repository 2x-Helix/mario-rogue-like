package game.ground;

/**
 * Avaliable capabilities for Ground
 * @author Matthew Siegenthaler
 * @version 1.0
 */
public enum GroundCapabilities {
    FERTILE,
    ON_FIRE,                // indicates ground is on fire, deals damage to actors
    FROZEN                  // indicates ground can't be set on fire, put off fire on the ground
}
