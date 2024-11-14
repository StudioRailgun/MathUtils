package io.github.studiorailgun;

/**
 * Utilities for getting random numbers
 */
public class RandUtils {

    /**
     * The magnitude of the random oscillator
     */
    static final float RANDOM_MAGNITUDE = 100000.0f;

    /**
     * Half of pi
     */
    public static final double HALF_PI = Math.PI / 2.0;

    /**
     * Values for the vector dot'd to create a pseudorandom number
     */
    static final double RAND_VEC_X_VAL = 111.154315;
    static final double RAND_VEC_Y_VAL = 123.631631;
    static final double RAND_VEC_Z_VAL = 117.724545;
    static final double RAND_VEC_W_VAL = 141.724545;

    /**
     * Calculates a random number. Range [0,1]
     * @param in The input
     * @return The output
     */
    public static double rand(double in){
        return MathUtils.fract(Math.sin(in) * RANDOM_MAGNITUDE);
    }

    /**
     * Calculates a random number. Range [0,1]
     * @param x The x input
     * @param y The y input
     * @return The output
     */
    public static double rand(double x, double y){
        return MathUtils.fract(Math.sin(MathUtils.dot(x,y,RAND_VEC_X_VAL,RAND_VEC_Y_VAL)) * RANDOM_MAGNITUDE);
    }

    /**
     * Calculates a random number. Range [0,1]
     * @param x The x input
     * @param y The y input
     * @param z The z input
     * @return The output
     */
    public static double rand(double x, double y, double z){
        return MathUtils.fract(Math.sin(MathUtils.dot(x,y,z,RAND_VEC_X_VAL,RAND_VEC_Y_VAL,RAND_VEC_Z_VAL)) * RANDOM_MAGNITUDE);
    }

    /**
     * Calculates a random number. Range [0,1]
     * @param x The x input
     * @param y The y input
     * @param z The z input
     * @param w The w input
     * @return The output
     */
    public static double rand(double x, double y, double z, double w){
        return MathUtils.fract(Math.sin(MathUtils.dot(x,y,z,w,RAND_VEC_X_VAL,RAND_VEC_Y_VAL,RAND_VEC_Z_VAL,RAND_VEC_W_VAL)) * RANDOM_MAGNITUDE);
    }

    /**
     * Calculates a random integer. Range [min,max]
     * @param min The minimum integer value
     * @param max The maximum integer value
     * @param seed The seed
     * @return The output
     */
    public static int rand(int min, int max, double seed){
        if(min > max){
            throw new Error("The minimum is larger than the maximum! " + min + " " + max);
        }
        double percentage = RandUtils.rand(seed);
        return (int)(percentage * (max - min)) + min;
    }
    
}
