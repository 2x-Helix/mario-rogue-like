package game.ground;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.items.Tickable;


public class Fire extends Ground implements Tickable {
    private static final Integer MAX_DURATION = 3;
    private Integer duration;   // active duration
    private final Ground previousGround; // previous ground before being on fire
    private final Integer damage; // total damage dealt every turn
    /**
     *
     * Constructor.
     */
    public Fire(Ground previousGround) {
        super('v');
        this.duration = MAX_DURATION;                               // 3 turns
        this.previousGround = previousGround;
        this.damage = 20;
    }

    @Override
    public void tick(Location location) {
        this.tick();
        if (duration == 0) {
            location.setGround(previousGround);
        }
        if(location.containsAnActor()){
            location.getActor().hurt(damage);
            System.out.println(location.getActor() + " is burned for 20 damage!!!");
        }
    }

    /**
     * Override the tick() from Tickable
     * Inform this item the passage of time
     */
    @Override
    public void tick() {
        if (duration > 0) {
            duration -= 1;
        }
    }


}
