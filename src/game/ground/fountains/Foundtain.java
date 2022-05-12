package game.ground.fountains;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.FillWaterAction;

public abstract class Foundtain extends Ground {

    private final Integer capacity;     // maximum water
    private Integer volume;             // current water

    private final Integer refillTime;   // turns it needs to refill to maximum
    private final Integer refillVolume; // refill volume each turn
    private boolean doRefill;


    private FillWaterAction fillWaterAction = null;
    /**
     * Protected constructor for its children classes
     * @param displayChar character to display for this type of terrain
     */
    protected Foundtain(char displayChar) {
        super(displayChar);
        capacity = 10;
        refillTime = 5;
        volume = capacity;
        refillVolume = capacity/refillTime;
        doRefill = false;
    }

    /**
     * @return the single FillWaterAction for this fountain
     */
    public FillWaterAction getFillWaterAction() {
        if (fillWaterAction == null) {
            fillWaterAction = new FillWaterAction(this);
        }
        return fillWaterAction;
    }

    /**
     * Inform this fountain the passage of time
     * Refill water over 5 turns if it is empty
     * @param location The location of this fountain
     */
    public void tick(Location location) {
        if (doRefill) {
            volume += refillVolume;
            doRefill = volume.equals(capacity);
        }
    }

    public abstract String toString();

}
