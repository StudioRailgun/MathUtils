package io.github.studiorailgun;


/**
 * Utilities for hashing numbers
 */
public class HashUtils {

    /**
     * Computes the cantor pairing of two numbes
     * @param a The first number
     * @param b The second number
     * @return The pairing
     */
    public static int cantor(int a, int b){
        return (a + b + 1) * (a + b) / 2 + b;
    }

    /**
     * Computes a hash of three numbers using cantor pairing
     * @param a The first number
     * @param b The second number
     * @param c The third number
     * @return The hash
     */
    public static int cantorHash(int a, int b, int c){
        return cantor(a, cantor(b, c));
    }
    
}
