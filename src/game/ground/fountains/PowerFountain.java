package game.ground.fountains;

import game.status.Status;

public class PowerFountain extends Foundtain {

    public static final String NAME = "Power Fountain";
    public static final char DISPLAY_CHAR = 'A';

    public PowerFountain() {
        super(DISPLAY_CHAR);
        this.capabilitiesList().add(Status.POWERFUL);
    }

    @Override
    public String toString() {
        return NAME;
    }
}
