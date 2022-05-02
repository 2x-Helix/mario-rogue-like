package game.actors.friendlies;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Abstract class representing the friendly actors in the game.
 * @author James Huynh
 * @version 1.0
 */
public abstract class Friendly extends Actor {
    
    /**
     * 
     * @param name
     * @param displayChar
     * @param hitPoints
     */
    protected Friendly(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
    }
    
}
