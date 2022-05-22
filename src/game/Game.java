package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.*;
import game.actors.friendlies.Player;
import game.ground.*;
import game.ground.fountains.HealthFountain;
import game.ground.fountains.PowerFountain;
import game.ground.teleporters.WarpPipe;
import game.ground.trees.Sprout;
import game.zones.KingdomZone;
import game.zones.LavaZone;
import game.zones.Zone;


/**
 *
 * @author Matthew Siegenthaler
 * @version 1.0
 */
public class Game {
    // Attributes
    private World world;

    // Constructor
    public Game(){
        // Initialise world
        this.world = new World(new Display());

        // Generate maps
        FancyGroundFactory groundFactory = new FancyGroundFactory(
                new Dirt(), new Wall(), new Floor(), new Sprout(), new Lava(),
                new HealthFountain(), new PowerFountain());

        Zone kingdomMap = new KingdomZone(world, groundFactory);
        Zone lavaMap = new LavaZone(world, groundFactory);

        // Warp pipes between lava zone and kingdom map
        Location lavaPipeLocation = lavaMap.at(0,0);
        WarpPipe lavaPipe = new WarpPipe(null, null, "Mushroom Kingdom");
        lavaPipeLocation.setGround(lavaPipe);
        WarpPipe kingdomPipe = new WarpPipe(lavaPipeLocation, lavaPipe, "Lava Zone");
        kingdomMap.randomizePipes(new Dirt(), kingdomPipe, 1, kingdomMap);

        // Spawn player
        spawnPlayer(kingdomMap.at(42, 10));
    }

    /**
     * Runs the game for world
     */
    public void run(){
        world.run();
    }

    /**
     * Spawn player at specified destination
     * @param location Location to spawn the player at.
     */
    private void spawnPlayer(Location location){
        Actor mario = new Player("Player", 'm', 100);
        world.addPlayer(mario, location);
    }
}
