package game.actors;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * @TODO: Implment this class
 */
public abstract class Enemy extends Actor{

    /**
     *
     * @param name
     * @param displayChar
     * @param hitPoints
     */
    protected Enemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
    }
    
}
