package game.ground.fountains;

import game.items.magical_items.bottles.HealthBottle;
import game.status.Status;

public class HealthFountain extends Foundtain {

    public static final String NAME = "Health Fountain";
    public static final char DISPLAY_CHAR = 'H';

    public HealthFountain() {
        super(DISPLAY_CHAR);
        this.capabilitiesList().add(Status.GREATER_HEAL);
    }

    @Override
    public String toString() {
        return NAME;
    }
}
