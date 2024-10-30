package io.github.studiorailgun;

/**
 * Utility functions that perform math
 */
public class MathUtils {

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
     * Calculates the fractional component of a number
     * @param num The number
     * @return The fractional component
     */
    public static float fract(float num){
        return num - (float)Math.floor(num);
    }

    /**
     * Calculates the fractional component of a number
     * @param num The number
     * @return The fractional component
     */
    public static double fract(double num){
        return num - Math.floor(num);
    }

    /**
     * Calculates a random number. Range [0,1]
     * @param in The input
     * @return The output
     */
    public static double rand(double in){
        return fract(Math.sin(in) * RANDOM_MAGNITUDE);
    }

    /**
     * Calculates a random number. Range [0,1]
     * @param x The x input
     * @param y The y input
     * @return The output
     */
    public static double rand(double x, double y){
        return fract(Math.sin(dot(x,y,RAND_VEC_X_VAL,RAND_VEC_Y_VAL)) * RANDOM_MAGNITUDE);
    }

    /**
     * Calculates a random number. Range [0,1]
     * @param x The x input
     * @param y The y input
     * @param z The z input
     * @return The output
     */
    public static double rand(double x, double y, double z){
        return fract(Math.sin(dot(x,y,z,RAND_VEC_X_VAL,RAND_VEC_Y_VAL,RAND_VEC_Z_VAL)) * RANDOM_MAGNITUDE);
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
        return fract(Math.sin(dot(x,y,z,w,RAND_VEC_X_VAL,RAND_VEC_Y_VAL,RAND_VEC_Z_VAL,RAND_VEC_W_VAL)) * RANDOM_MAGNITUDE);
    }

    /**
     * Calculates the dot product of a 2d vector
     * @param x1 The x coordinate of the first vector
     * @param y1 The y coordinate of the first vector
     * @param x2 The x coordinate of the second vector
     * @param y2 The y coordinate of the second vector
     * @return The dot product of the vectors
     */
    public static double dot(double x1, double y1, double x2, double y2){
        return (x1*x2) + (y1*y2);
    }

    /**
     * Calculates the dot product of a 3d vector
     * @param x1 The x coordinate of the first vector
     * @param y1 The y coordinate of the first vector
     * @param z1 The y coordinate of the first vector
     * @param x2 The x coordinate of the second vector
     * @param y2 The y coordinate of the second vector
     * @param z2 The y coordinate of the first vector
     * @return The dot product of the vectors
     */
    public static double dot(double x1, double y1, double z1, double x2, double y2, double z2){
        return (x1*x2) + (y1*y2) + (z1*z2);
    }

    /**
     * Calculates the dot product of a 4d vector
     * @param x1 The x coordinate of the first vector
     * @param y1 The y coordinate of the first vector
     * @param z1 The y coordinate of the first vector
     * @param w1 The w coordinate of the first vector
     * @param x2 The x coordinate of the second vector
     * @param y2 The y coordinate of the second vector
     * @param z2 The y coordinate of the second vector
     * @param w2 The w coordinate of the second vector
     * @return The dot product of the vectors
     */
    public static double dot(double x1, double y1, double z1, double w1, double x2, double y2, double z2, double w2){
        return (x1*x2) + (y1*y2) + (z1*z2) + (w1*w2);
    }

    /**
     * Checks if a number is a power of two
     * @param x The number
     * @return true if it is a power of two, false otherwise
     */
    public static boolean isPowerOfTwo(long x){
        return (x & (x - 1)) == 0;
    }

    /**
     * Calculates the log base 2 of the integer
     * @param bits The integer
     * @return The log base 2 value
     */
    public static int log2(int bits){
        int log = 0;
        if( ( bits & 0xffff0000 ) != 0 ) { bits >>>= 16; log = 16; }
        if( bits >= 256 ) { bits >>>= 8; log += 8; }
        if( bits >= 16  ) { bits >>>= 4; log += 4; }
        if( bits >= 4   ) { bits >>>= 2; log += 2; }
        return log + ( bits >>> 1 );
    }
    
}
