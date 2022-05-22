package game.status;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 */
public enum Status {
    HOSTILE_TO_ENEMY,       // use this status to be considered hostile towards enemy (e.g., to be attacked by enemy)
    TALL,                   // indicates player has consumed SuperMushroom, and diaplayChar changed to 'M'
    INCREASED_MAX_HP,       // indicates actor have 50 more max hp
    EASY_JUMP,              // indicates actor can jump with 100% success rate
    HIGHER_GROUND,          // indicates actor can just walk to higher ground, and destroy it
    CAN_FLY,                // indicates actor can fly/walk on higher ground
    COIN_FROM_DESTROYED_GROUND,  // destroyed ground drop $5
    IMMUNITY,               // indicates actor takes 0 damage when attacked by enemies
    INSTA_KILL,              // indicates actor instantly kill enemies if the attack is successful
    FIRE_ATTACK,              // indicates actor's AttackAction can also drop fire on ground
    INDESTRUCTIBLE,      // indicates actor cannot be removed when hp is less than or equal to 0
    DORMANT,             // indicates actor cannot attack, follow or wander around
    CAN_SMASH,              // indicates actor can use SmashShellAction
    LOCKED,                 // indicates actor cannot move, attack or be attacked
    CAN_UNLOCK,                // indicates actor has key, can unlock cages
    RESET,                  // indicates object is to be reset next turn
    POWERFUL,               // base attack damage +15 (NOT WEAPON DAMAGE)
    GREATER_HEAL,           // heal amount +50
    ENEMY,                  // Attacks player
}
