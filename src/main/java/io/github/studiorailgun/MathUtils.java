package io.github.studiorailgun;

/**
 * Utility functions that perform math
 */
public class MathUtils {

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
     * Calculates the distance between 2D points
     * @param x1 The x coordinate of the first point
     * @param y1 The y coordinate of the first point
     * @param x2 The x coordinate of the second point
     * @param y2 The y coordinate of the second point
     * @return The distance
     */
    public static double dist(double x1, double y1, double x2, double y2){
        return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }

    /**
     * Calculates the distance between 3D points
     * @param x1 The x coordinate of the first point
     * @param y1 The y coordinate of the first point
     * @param z1 The z coordinate of the first point
     * @param x2 The x coordinate of the second point
     * @param y2 The y coordinate of the second point
     * @param z2 The z coordinate of the second point
     * @return The distance
     */
    public static double dist(double x1, double y1, double z1, double x2, double y2, double z2){
        return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1) + (z2 - z1) * (z2 - z1));
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

    /**
     * Clamps a value
     * @param val The value
     * @param min The minimum value
     * @param max The maximum value
     * @return The clamped value
     */
    public static double clamp(double val, double min, double max){
        return Math.max(Math.min(val,max),min);
    }

    /**
     * Linearly interpolates between two doubles
     * @param a The first double
     * @param b The second double
     * @param percent The percentage to interpolate between them
     * @return The interpolated value
     */
    public static double lerp(double a, double b, double percent){
        return a * (1.0 - percent) + (b * percent);
    }
    
}
