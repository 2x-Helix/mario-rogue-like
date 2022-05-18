package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.World;
import game.actors.friendlies.Player;
import game.actors.friendlies.Toad;
import game.ground.Dirt;
import game.ground.Floor;
import game.ground.Lava;
import game.ground.Wall;
import game.ground.teleporters.WarpPipe;
import game.ground.trees.Sprout;
import game.items.magical_items.PowerStar;
import game.items.magical_items.SuperMushroom;

/**
 * The main class for the Mario World game.
 *
 */
public class Application {

	public static void main(String[] args) {

		World world = new World(new Display());

		FancyGroundFactory lavaGroundFactory = new FancyGroundFactory(
				new Dirt(), new Wall(), new Floor(), new Sprout(), new Lava());

		List<String> strLavaZoneMap = Arrays.asList(
				".......L#############L.......",
				".......L#LLLLLLLLLLL#L.......",
				"L......L#LL.......LL#L.......",
				"L......L#L.........L#L......+",
				".......L#L.........L#L...L...",
				".+.....L#LL.......LL#L.......",
				".....+.L#LLLL...LLLL#L..+...L",
				".......L#####___#####L......L",
				"+......LLLLL#___#LLLLL.......",
				"...........L#___#L......L....",
				"..L..........................",
				".........+............L....L.",
				"...L................+.....L.."
		);
		GameMap lavaZoneMap = new GameMap(lavaGroundFactory, strLavaZoneMap);

		// Warp Pipe
		WarpPipe lavaPipe = new WarpPipe();
		Location lavaEntrance = lavaZoneMap.at(0,0);
		lavaEntrance.setGround(lavaPipe);

		world.addGameMap(lavaZoneMap);


		FancyGroundFactory kingdomGroundFactory = new FancyGroundFactory(
				new Dirt(), new Wall(), new Floor(), new Sprout());
		// new WarpPipe(lavaEntrance, lavaPipe, "Lava Zone")

		List<String> strKingdomMap = Arrays.asList(
			"..........................................##..........+.........................",
			"............+............+..................#...................................",
			"............................................#...................................",
			".............................................##......................+..........",
			"...............................................#................................",
			"................................................#...............................",
			".................+................................#.............................",
			".................................................##.............................",
			"................................................##..............................",
			".........+..............................+#____####.................+............",
			".......................................+#_____###++.............................",
			".......................................+#______###..............................",
			"........................................+#_____###..............................",
			"........................+........................##.............+...............",
			"...................................................#............................",
			"....................................................#...........................",
			"...................+.................................#..........................",
			"......................................................#.........................",
			".......................................................##.......................");
		GameMap kingdomMap = new GameMap(kingdomGroundFactory, strKingdomMap);
		WarpPipe pipe = new WarpPipe(lavaZoneMap.at(0,0), lavaPipe,"Lava Zone");
		kingdomMap.at(42, 8).setGround(pipe);
		kingdomMap.at(44, 8).setGround(pipe);

		world.addGameMap(kingdomMap);

		// Player
		Actor mario = new Player("Player", 'm', 100);
		world.addPlayer(mario, kingdomMap.at(42, 10));

		// Spawn these 2 items at the spawn point of Mario
		kingdomMap.locationOf(mario).addItem(new SuperMushroom());
		kingdomMap.locationOf(mario).addItem(new PowerStar());

		world.run();
	}

	/**
	 * LavaZone map
	 * @return GameMap of the Lava Zone
	 */
	private static GameMap lavaZone() {
		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(),
				new Floor(), new Sprout(), new Lava());

		List<String> map = Arrays.asList(
			".......L#############L.......",
			".......L#LLLLLLLLLLL#L.......",
			"L......L#LL.......LL#L.......",
			"L......L#L.........L#L......+",
			".......L#L.........L#L...L...",
			".+.....L#LL.......LL#L.......",
			".....+.L#LLLL...LLLL#L..+...L",
			".......L#####___#####L......L",
            "+......LLLLL#___#LLLLL.......",
			"...........L#___#L......L....",
			"..L..........................",
			".........+............L....L.",
			"...L................+.....L.."
		);

		return new GameMap(groundFactory, map);
	}

	private static GameMap mushroomKingdom() {
		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Sprout());

		List<String> map = Arrays.asList(
				"..........................................##..........+.........................",
				"............+............+..................#...................................",
				"............................................#...................................",
				".............................................##......................+..........",
				"...............................................#................................",
				"................................................#...............................",
				".................+................................#.............................",
				".................................................##.............................",
				"................................................##..............................",
				".........+..............................+#____####.................+............",
				".......................................+#_____###++.............................",
				".......................................+#______###..............................",
				"........................................+#_____###..............................",
				"........................+........................##.............+...............",
				"...................................................#............................",
				"....................................................#...........................",
				"...................+.................................#..........................",
				"......................................................#.........................",
				".......................................................##.......................");

		GameMap gameMap = new GameMap(groundFactory, map);

		gameMap.at(44,11).addActor(new Toad());

		return gameMap;
	}

}
