package game.zones;

import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.World;
import game.ground.Dirt;
import game.ground.Lava;

import java.util.Arrays;
import java.util.List;

/**
 * Lava zone map where the player may fight Bowser and rescue Princess Peach.
 * @author Matthew Siegenthaler
 * @version 1.0
 */
public class LavaZone extends Zone {
    // Constants
    private static final List<String> strMap = Arrays.asList(
        "C......L#############L.......",
        ".......L#LLLLLLLLLLL#L.......",
        ".......L#LL.......LL#L.......",
        ".......L#L.........L#L.......",
        ".......L#L.........L#L.......",
        ".......L#LL.......LL#L.......",
        ".......L#LLLL...LLLL#L.......",
        ".......L#####___#####L.......",
        ".......LLLLL#___#LLLLL.......",
        "...........L#___#L...........",
        ".............................",
        ".............................",
        "............................."
    );

    // Constructors
    public LavaZone(World world, FancyGroundFactory groundFactory) {
        super(world, groundFactory, strMap);
        randomizeGround(new Dirt(), new Lava(), 5, this);
    }

    // Methods
    @Override
    public void addActors() {
        // Add Bowser and Peach
    }

    @Override
    public void addItems() {
        // No items by default
    }
}
