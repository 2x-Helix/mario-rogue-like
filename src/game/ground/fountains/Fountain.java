package game.ground.fountains;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.DrinkAction;
import game.actions.FillWaterAction;
import game.actors.enemies.Enemy;
import game.actors.friendlies.Player;
import game.items.magical_items.bottles.Bottle;

public abstract class Fountain extends Ground {

    private final Integer capacity;     // maximum water
    private Integer volume;             // current water

    private final Integer refillVolume; // refill volume each turn
    private boolean doRefill;

    private FillWaterAction fillWaterAction = null;
    private DrinkAction drinkAction = null;

    /**
     * Protected constructor for its children classes
     * @param displayChar character to display for this type of terrain
     */
    protected Fountain(char displayChar) {
        super(displayChar);
        capacity = 10;
        Integer refillTime = 5;             // turns it needs to refill to maximum
        volume = capacity;
        refillVolume = capacity/refillTime;
        doRefill = false;
    }

    /**
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return the list of allowable actions for the actors
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();

        // Provides no actions when it is empty
        if (isEmpty())
            return actions;

        // Provides DrinkAction if the actor is enemy
        try{
            if (actor instanceof Enemy)
                actions.add(this.getDrinkAction());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Provides FillWaterAction if the actor is player AND it has a bottle
        try {
            if (actor instanceof Player) {
                for (Item item : actor.getInventory()) {
                    if (item.getClass() == Bottle.class) {
                        actions.add(this.getFillWaterAction());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return actions;
    }

    /**
     * Provided only when !isEmpty()
     * @return the single FillWaterAction for this fountain
     * @throws Exception when the fountain is empty
     */
    public FillWaterAction getFillWaterAction() throws Exception {
        if (isEmpty())
            throw new Exception("Fountain is empty");

        if (fillWaterAction == null) {
            fillWaterAction = new FillWaterAction(this);
        }
        return fillWaterAction;
    }

    /**
     * Provided only when !isEmpty()
     * @return the single DrinkAction for this fountain
     * @throws Exception when the fountain is empty
     */
    public DrinkAction getDrinkAction() throws Exception {
        if (isEmpty())
            throw new Exception("Fountain is empty");

        if (drinkAction == null) {
            drinkAction = new DrinkAction(this);
        }
        return drinkAction;
    }

    /**
     * Called whenever actor fills water bottle
     * @param actor the actor that fills the water bottle
     */
    public void onFill(Actor actor) throws Exception {
        volume -= 1;
    }

    /**
     * Called whenever actor(enemy) drink water
     * @param actor the actor that drinks the water
     */
    public void onDrink(Actor actor) {
        for (Enum<?> status : this.capabilitiesList()) {
            actor.addCapability(status);
        }
        volume -= 1;
    }

    /**
     * @return flag indicating if this fountain is empty
     */
    public boolean isEmpty() {
        return volume == 0;
    }

    /**
     * Inform this fountain the passage of time
     * Refill water over 5 turns if it is empty
     * @param location The location of this fountain
     */
    @Override
    public void tick(Location location) {
        if (!doRefill)                              // if not refilling,
            doRefill = isEmpty();                   // start refilling when fountain is empty

        if (doRefill) {                             // if refilling
            volume += refillVolume;                 // refill and
            doRefill = !volume.equals(capacity);          // keep refilling till volume == capacity
        }
    }

    public Integer volume() { return volume; }

    public Integer capacity() { return capacity; }

    public abstract String toString();

}
