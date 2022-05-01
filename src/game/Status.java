package game;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 */
public enum Status {
    HOSTILE_TO_ENEMY,   // use this status to be considered hostile towards enemy (e.g., to be attacked by enemy)
    TALL,               // use this status to tell that current instance has "grown".
    INCREASED_MAX_HP,   // indicates actor have 50 more max hp
    EASY_JUMP,          // indicates actor can jump with 100% success rate
    HIGHER_GROUND,      // indicates actor can just walk to higher ground, destroy higher ground into dirt when walk, destroyed ground drop $5
    IMMUNITY,           // indicates actor takes 0 damage when attacked by enemies
    INSTA_KILL,         // indicates actor instantly kill enemies if the attack is successful
    INDESTRUCTIBLE,      // indicates actor cannot be removed when hp is less than or equal to 0
    DORMANT             // indicates actor cannot attack, follow or wander around
}
