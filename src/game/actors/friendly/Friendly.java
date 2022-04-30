package game.actors.friendly;

import edu.monash.fit2099.engine.actors.Actor;
import game.status.Status;

/**
 * @TODO: Implment this class
 */
// Peter: I think it's better to change this class into an interface
public abstract class Friendly extends Actor{
    
    /**
     * 
     * @param name
     * @param displayChar
     * @param hitPoints
     */
    protected Friendly(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
    }
    
}
