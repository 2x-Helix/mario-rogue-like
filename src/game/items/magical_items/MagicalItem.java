package game.items.magical_items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actions.ConsumeAction;

/**
 * A class of Item that is consummable by Players (and possibly all actors)
 * @author ChunKau Mok (Peter)
 * @version 1.0
 */
public abstract class MagicalItem extends Item{
    
    /**
     * Protected contructor of this class
     * @param name
     * @param displayChar
     * @param portable
     */
    protected MagicalItem(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
        this.addAction(this.getConsumeAction());
    }

    /**
     * Create and return an action to consume this item
     */
    protected ConsumeAction getConsumeAction() {
        return new ConsumeAction(this);
    }

    /**
     * Intended to be override by child classess
     * Call this function when MagicalItems are consumed, to provide statuses and effects
     */
    public void onConsume(Actor actor) {

    }

}
