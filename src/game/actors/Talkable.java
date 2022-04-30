package game.actors;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Talkable handles monologue options for implementer
 * @author Matthew Siegenthaler
 * @version 1.0
 */
public interface Talkable {
    /**
     * Handles dialogue options for implementer of Talkable.
     * @param actor The actor being spoken towards in monologue.
     * @return Monologue spoken by the implementer of Talkable.
     */
    String getMonologue(Actor actor);

    /**
     * Gets the name of the implementer
     * @return Name of speaker
     */
    String getSpeaker();
}
