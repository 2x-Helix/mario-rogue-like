package game.zones;

import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.World;
import game.actors.enemies.DisguisedChest;
import game.actors.friendlies.Toad;
import game.ground.Dirt;
import game.ground.chests.WoodChest;
import game.ground.trees.Sprout;
import game.items.magicalitems.PowerStar;
import game.items.magicalitems.SuperMushroom;

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
        randomizeGround(new Dirt(), new WoodChest(), 1, this);
    }

    // Methods
    @Override
    public void addActors() {
        // Add actors
        this.at(44,11).addActor(new Toad());
        this.at(40, 14).addActor(new DisguisedChest());
    }

    @Override
    public void addItems() {
        // Add items
        this.at(42,10).addItem(new SuperMushroom());
        this.at(42, 10).addItem(new PowerStar());
    }
}
