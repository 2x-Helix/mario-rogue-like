package game.items.magical_items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;

/**
 * A class of Item that is consummable by Players (and possibly all actors)
 * @author ChunKau Mok (Peter)
 * @version 1.0
 */
public abstract class MagicalItem extends Item{

    private ConsumeAction consumeAction = null;
    private DropItemAction dropItemAction = null;

    /**
     * Protected contructor of this class
     * @param name name of magical item
     * @param displayChar character to display item on the ground
     * @param portable can the magical item be moved?
     */
    protected MagicalItem(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
        this.addAction(this.getConsumeAction());
    }

    /**
     * @return the single ConsumeAction for this item
     */
    protected ConsumeAction getConsumeAction() {
        if (consumeAction == null) {
            consumeAction = new ConsumeAction(this);
        }
        return consumeAction;
    }

    /**
     * Remove the ConsumeAction of this item
     * Called when player consumes this item
     */
    public void removeConsumeAction() {
        this.removeAction(this.consumeAction);
    }

    /**
     * Intended to be override by child classess
     * Call this function when MagicalItems are consumed, to provide statuses and effects
     */
    public abstract void onConsume(Actor actor);
}
