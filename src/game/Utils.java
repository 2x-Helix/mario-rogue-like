package game;

import java.util.Random;

/**
 * Utilities class containing random number generator
 */
public class Utils {
    /**
     * Pseudo-randomly generates an integer for a chance roll between 1-100
     * @return Generate integer for chance roll between 1-100
     */
    public static int nextChance(){
        Random r = new Random();
        int low = 1;
        int high = 100;
        return (r.nextInt(high - low) + low);
    }
}

