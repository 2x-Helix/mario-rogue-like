package game.actors.friendly;

import edu.monash.fit2099.engine.actors.Actor;

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
    }
    
}
