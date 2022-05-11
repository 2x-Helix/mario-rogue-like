package game.items.weapon_items;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.items.ItemManager;
import game.status.Status;

/**
 * Wrench weapon used to smash Koopa shells.
 * @author ChunKau Mok, Matthew Siegenthaler
 * @version 2.0
 */
public class Wrench extends WeaponItem {

    private static final String NAME = "Wrench";
    private static final char DISPLAY_CHAR = 'W';
    private static final int DAMAGE = 50;
    private static final String VERB = "smashes";
    private static final int HIT_RATE = 80;

    /**
     * Constructor.
     */
    public Wrench() {
        super(NAME, DISPLAY_CHAR, DAMAGE, VERB, HIT_RATE);
        this.addCapability(Status.CAN_SMASH);
        ItemManager.getInstance().insertPrice(this, 200);
    }
}
