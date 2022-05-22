package game.zones;

import edu.monash.fit2099.engine.positions.*;
import game.Utils;
import game.ground.teleporters.WarpPipe;

import java.util.List;

/**
 * Abstract class for creation of new maps
 * @author Matthew Siegenthaler
 * @version 1.0
 */
public abstract class Zone extends GameMap{

    public Zone(World world, GroundFactory groundFactory, List<String> lines) {
        super(groundFactory, lines);
        world.addGameMap(this);  // register game map to world to add actors
        addActors();
        addItems();
    }

    /**
     * Initialize actors in game map
     */
    public abstract void addActors();

    /**
     * Initialize items in game map.
     */
    public abstract void addItems();

    /**
     * Replaces a ground with a specified ground at a specified rate
     * @param oldGround Ground to replace
     * @param newGround Ground to become
     * @param chance Chance of replacing oldGround
     * @param map Map to replace ground of
     */
    public void randomizeGround(Ground oldGround, Ground newGround, int chance, GameMap map) {
        FancyGroundFactory groundFactory = new FancyGroundFactory(newGround);
        for (int x : map.getXRange()) {
            for (int y : map.getYRange()) {
                // If old ground
                if (map.at(x, y).getGround().getDisplayChar() == oldGround.getDisplayChar()
                        && Utils.nextChance() <= chance)
                    map.at(x, y).setGround(groundFactory.newGround(newGround.getDisplayChar()));
            }
        }
    }

    /**
     * Method for randomly placing pipes. Used as a replacement over the engine FancyGroundFactory
     * @param oldGround ground type to replace
     * @param pipe Entrance pipe to clone and place
     * @param chance integer chance for placing a pipe
     * @param map map to place the pipes on.
     */
    public void randomizePipes(Ground oldGround, WarpPipe pipe, int chance, GameMap map) {
        for (int x : map.getXRange()) {
            for (int y : map.getYRange()) {
                // If old ground
                if (map.at(x, y).getGround().getDisplayChar() == oldGround.getDisplayChar()
                        && Utils.nextChance() <= chance) {
                    Ground entrancePipe = pipe.newGround();  // Factory method
                    map.at(x, y).setGround(entrancePipe);
                }
            }
        }
    }

    /**
     * TODO: generate chests/mimics method
     */
}
