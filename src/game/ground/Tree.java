package game.ground;

/**
 * A ground type that represent all kinds of trees
 * @author Matt FIXME: enter your full name here
 * @author ChunKau Mok (Peter)
 * @version 2.0
 */
public class Tree extends HighGround {

    /**
     * Public constructor that's technically incorrect but necessary 
     * so that FancyGroundFactory workds properly
     */
    public Tree() {
        super('+', 0, 0);
    }

    /**
     * Protected constructor for child
     * @param sucessThreshold
     * @param fallDamage
     */
    protected Tree(Integer sucessThreshold, Integer fallDamage) {
        super('+', sucessThreshold, fallDamage);
    }

}
