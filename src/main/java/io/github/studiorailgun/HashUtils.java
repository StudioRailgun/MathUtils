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
    public static long cantor(long a, long b){
        return (a + b + 1) * (a + b) / 2 + b;
    }

    /**
     * Computes a hash of three numbers using cantor pairing
     * @param a The first number
     * @param b The second number
     * @param c The third number
     * @return The hash
     */
    public static long cantorHash(long a, long b, long c){
        return HashUtils.cantor(a, HashUtils.cantor(b, c));
    }

    /**
     * Computes a hash of four numbers using cantor pairing
     * @param a The first number
     * @param b The second number
     * @param c The third number
     * @param d The fourth number
     * @return The hash
     */
    public static long cantorHash(long a, long b, long c, long d){
        return HashUtils.cantor(a, HashUtils.cantor(b, HashUtils.cantor(c,d)));
    }
    
}
