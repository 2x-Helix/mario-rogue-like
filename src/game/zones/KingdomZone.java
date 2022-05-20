package game.zones;

import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.World;
import game.actors.friendlies.Toad;
import game.ground.Dirt;
import game.ground.teleporters.WarpPipe;
import game.ground.trees.Sprout;
import game.items.magical_items.PowerStar;
import game.items.magical_items.SuperMushroom;

import java.util.Arrays;
import java.util.List;

/**
 * Starting zone where Player may buy items from Toad.
 * @author Matthew Siegenthaler
 * @version 1.0
 */
public class KingdomZone extends Zone {
    // Constants
    private static final List<String> strMap = Arrays.asList(
        "..........................................##....................................",
        "............................................#...................................",
        "............................................#...................................",
        ".............................................##.................................",
        "...............................................#................................",
        "................................................#...............................",
        "..................................................#.............................",
        "..........................................A.H....##.............................",
        "................................................##..............................",
        "........................................+#____####..............................",
        ".......................................+#_____###...............................",
        ".......................................+#______###..............................",
        "........................................+#_____###..............................",
        ".................................................##.............................",
        "...................................................#............................",
        "....................................................#...........................",
        ".....................................................#..........................",
        "......................................................#.........................",
        ".......................................................##.......................");

    // Constructors
    public KingdomZone(World world, FancyGroundFactory groundFactory) {
        super(world, groundFactory, strMap);
        randomizeGround(new Dirt(), new Sprout(), 3, this);
    }

    // Methods
    @Override
    public void addActors() {
        // Add actors
        this.at(44,11).addActor(new Toad());
    }

    @Override
    public void addItems() {
        // Add items
        this.at(42,10).addItem(new SuperMushroom());
        this.at(42, 10).addItem(new PowerStar());
    }
}
