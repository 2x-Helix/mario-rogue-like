package game.weapons;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.Weapon;

/**
 * Class representing items that can be used as a weapon.
 */
public class Wrench extends Item implements Weapon {

	private final int damage;
	private final int hitRate;
	private final String verb;

	/** Constructor.
	 *
	 */
	public Wrench() {
		super("Wrench", 'w', true);
		this.damage = 50;
		this.verb = "smashes";
		this.hitRate = 80;
	}

	/**
	 * Accessor for damage done by this weapon.
	 *
	 * @return the damage
	 */
	public int damage() {
		return damage;
	}

	/**
	 * Returns the verb used for attacking with this weapon, so that it can be displayed
	 * in the UI.
	 *
	 * @return a third-person present tense verb, e.g. "hits", "zaps"
	 */
	public String verb() {
		return verb;
	}


	/**
	 * Returns the chance to hit the target in integer. Use it altogether with nextInt() method.
	 * @return Integer, such as
	 */
	public int chanceToHit(){return hitRate;}

}
