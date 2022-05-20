package game;

import game.actors.enemies.DisguisedChest;
import game.ground.Chest;

/**
 * The main class for the Mario World game.
 * @version 2.0
 */
public class Application {

	public static void main(String[] args) {
		Game game = new Game();
		game.run();
	}

//	// Spawn either a DisguisedChest or Chest (50% chance)
//			if(Utils.nextChance() <= 50) {
//		gameMap.at(40, Utils.nextInt(14,17)).addActor(new DisguisedChest());
//	}
//			else {
//		gameMap.at(40, Utils.nextInt(14,17)).setGround(new Chest());
//	}
}
