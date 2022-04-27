package game.actors.friendly;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * @TODO: Implement this class
 */
public class Toad extends Friendly{
    
    private static final String NAME = "Toad";
    private static final char DISPLAY_CHAR = 'O';

    /**
     * 
     */
    public Toad() {
        super(NAME, DISPLAY_CHAR, Integer.MAX_VALUE);   // Toad should have practically infinity HP right? unless we do a lil bit of trolling..
    }

    /**
     * @TODO: Implement this method
    */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        return null;
    }

}
