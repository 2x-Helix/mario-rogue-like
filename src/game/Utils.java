package game;

import java.util.Random;

/**
 * Utilities class containing random number generator
 * @author Matthew Siegenthaler
 * @version 1.1
 */
public class Utils {
    /**
     * Generates a new random number in bounds
     * @param low Lower integer bound of generation (inclusive)
     * @param high Upper integer bound of generation (exclusive)
     * @return Randomly generated integer in bounds
     */
    public static int nextInt(int low, int high) throws IllegalArgumentException {
        Random r = new Random();
        if (low < high){
            return (r.nextInt(high - low) + low);
        } else {
            throw new IllegalArgumentException("Low must be less than High.");
        }
    }

    /**
     * Pseudo-randomly generates an integer for a chance roll between 1-100
     * @return Generate integer for chance roll between 1-100
     */
    public static int nextChance(){
        return nextInt(1, 101);
    }
}