package io.github.studiorailgun;

/**
 * Noise generation functions
 */
public class NoiseUtils {

    /**
     * x offsets for a 3x3x3 kernel
     */
    private static final int[] KERNEL_3_3_3_X = new int[]{
        1, 0, -1,
        1, 0, -1,
        1, 0, -1,
        1, 0, -1,
        1, 0, -1,
        1, 0, -1,
        1, 0, -1,
        1, 0, -1,
        1, 0, -1,
    };

    /**
     * y offsets for a 3x3x3 kernel
     */
    private static final int[] KERNEL_3_3_3_Y = new int[]{
        1,  1,  1,
        0,  0,  0,
       -1, -1, -1,
        1,  1,  1,
        0,  0,  0,
       -1, -1, -1,
        1,  1,  1,
        0,  0,  0,
       -1, -1, -1,
    };

    /**
     * z offsets for a 3x3x3 kernel
     */
    private static final int[] KERNEL_3_3_3_Z = new int[]{
        1,  1,  1,
        1,  1,  1,
        1,  1,  1,
        0,  0,  0,
        0,  0,  0,
        0,  0,  0,
       -1, -1, -1,
       -1, -1, -1,
       -1, -1, -1,
    };

    /**
     * Calculates voronoi noise within a cube
     * @param x The x coordinate
     * @param y The y coordinate
     * @param z The z coordinate
     * @return The voronoi value
     */
    public static double voronoi(double x, double y, double z){
        //integer of the point coordinates
        double x_i = Math.floor(x);
        double y_i = Math.floor(y);
        double z_i = Math.floor(z);

        //remainders of the point coordinates
        double x_r = x - x_i;
        double y_r = y - y_i;
        double z_r = z - z_i;

        //calculate min dist
        double minDist = 10.0;
        for(int i = 0; i < KERNEL_3_3_3_X.length; i++){
            //the point of this cell
            double p_x = RandUtils.rand(x_i + KERNEL_3_3_3_X[i], y_i + KERNEL_3_3_3_Y[i], z_i + KERNEL_3_3_3_Z[i], 0);
            double p_y = RandUtils.rand(x_i + KERNEL_3_3_3_X[i], y_i + KERNEL_3_3_3_Y[i], z_i + KERNEL_3_3_3_Z[i], 1);
            double p_z = RandUtils.rand(x_i + KERNEL_3_3_3_X[i], y_i + KERNEL_3_3_3_Y[i], z_i + KERNEL_3_3_3_Z[i], 2);

            //dist calc + comparison
            double dist = Math.sqrt(
                (p_x + KERNEL_3_3_3_X[i] - x_r) * (p_x + KERNEL_3_3_3_X[i] - x_r) +
                (p_y + KERNEL_3_3_3_Y[i] - y_r) * (p_y + KERNEL_3_3_3_Y[i] - y_r) +
                (p_z + KERNEL_3_3_3_Z[i] - z_r) * (p_z + KERNEL_3_3_3_Z[i] - z_r)
            );
            if(dist < minDist){
                minDist = dist;
            }
        }

        return minDist;
    }

    /**
     * Calculates voronoi noise on a 2d plane
     * @param x The x coordinate
     * @param y The y coordinate
     * @return The voronoi value
     */
    public static double voronoi(double x, double y){
        return voronoi(x, y, 0);
    }

    /**
     * Generates relaxed points
     * @param x The x position
     * @param y The y position
     * @param relaxationFactor A parameter to control how uniform the points are. Essentially, the higher this is, the more uniform the points will be. Ranges (0,1)
     * @param threshold The threshold at which the pixel is within the point
     * @return 1 if at the relaxed point, 0 otherwise
     */
    public static double relaxedPointGen(double x, double y, double relaxationFactor, double threshold){
        //integer of the point coordinates
        double x_i = Math.floor(x);
        double y_i = Math.floor(y);

        //position of the source point within the current cell
        double source_x = x - x_i;
        double source_y = y - y_i;

        //calculate the current cell's point
        double cell_x = RandUtils.rand(x_i,y_i,0);
        double cell_y = RandUtils.rand(x_i,y_i,1);

        //relax the point
        double x_relaxed = cell_x * (1.0 - relaxationFactor) + (relaxationFactor / 2.0);
        double y_relaxed = cell_y * (1.0 - relaxationFactor) + (relaxationFactor / 2.0);

        //dist calc + comparison
        double dist = Math.sqrt((source_x - x_relaxed) * (source_x - x_relaxed) + (source_y - y_relaxed) * (source_y - y_relaxed));
        if(dist < threshold){
            return 1;
        }

        return 0;
    }

    /**
     * Calculates voronoi noise within a cube
     * @param x The x coordinate
     * @param y The y coordinate
     * @param z The z coordinate
     * @param relaxationFactor The relaxation factor
     * @return The voronoi value
     */
    public static double voronoiRelaxed(double x, double y, double z, double relaxationFactor){
        //integer of the point coordinates
        double x_i = Math.floor(x);
        double y_i = Math.floor(y);
        double z_i = Math.floor(z);

        //remainders of the point coordinates
        double x_r = x - x_i;
        double y_r = y - y_i;
        double z_r = z - z_i;

        //calculate min dist
        double minDist = 10.0;
        for(int i = 0; i < KERNEL_3_3_3_X.length; i++){
            //the point of this cell
            double p_x = RandUtils.rand(x_i + KERNEL_3_3_3_X[i], y_i + KERNEL_3_3_3_Y[i], z_i + KERNEL_3_3_3_Z[i], 0);
            double p_y = RandUtils.rand(x_i + KERNEL_3_3_3_X[i], y_i + KERNEL_3_3_3_Y[i], z_i + KERNEL_3_3_3_Z[i], 1);
            double p_z = RandUtils.rand(x_i + KERNEL_3_3_3_X[i], y_i + KERNEL_3_3_3_Y[i], z_i + KERNEL_3_3_3_Z[i], 2);

            //relax the point
            double x_relaxed = p_x * (1.0 - relaxationFactor) + (relaxationFactor / 2.0);
            double y_relaxed = p_y * (1.0 - relaxationFactor) + (relaxationFactor / 2.0);
            double z_relaxed = p_z * (1.0 - relaxationFactor) + (relaxationFactor / 2.0);

            //dist calc + comparison
            double dist = Math.sqrt(
                (x_relaxed + KERNEL_3_3_3_X[i] - x_r) * (x_relaxed + KERNEL_3_3_3_X[i] - x_r) +
                (y_relaxed + KERNEL_3_3_3_Y[i] - y_r) * (y_relaxed + KERNEL_3_3_3_Y[i] - y_r) +
                (z_relaxed + KERNEL_3_3_3_Z[i] - z_r) * (z_relaxed + KERNEL_3_3_3_Z[i] - z_r)
            );
            if(dist < minDist){
                minDist = dist;
            }
        }

        return minDist;
    }

    /**
     * Calculates voronoi noise on a 2d plane
     * @param x The x coordinate
     * @param y The y coordinate
     * @param relaxationFactor The relaxation factor
     * @return The voronoi value
     */
    public static double voronoiRelaxed(double x, double y, double relaxationFactor){
        return NoiseUtils.voronoiRelaxed(x, y, 0, relaxationFactor);
    }


    /**
     * Calculates a smooth voronoi noise value
     * @param x The x value
     * @param y The y value
     * @param z The z value
     * @param falloff The falloff (recommended values in range (4,256] )
     * @return The smooth voronoi noise value
     */
    public static double smoothVoronoi(double x, double y, double z, double falloff){
        //integer of the point coordinates
        double x_i = Math.floor(x);
        double y_i = Math.floor(y);
        double z_i = Math.floor(z);

        //remainders of the point coordinates
        double x_r = x - x_i;
        double y_r = y - y_i;
        double z_r = z - z_i;

        //calculate min dist
        double res = 0.0;
        for(int i = 0; i < KERNEL_3_3_3_X.length; i++){
            //the point of this cell
            double p_x = RandUtils.rand(x_i + KERNEL_3_3_3_X[i], y_i + KERNEL_3_3_3_Y[i], z_i + KERNEL_3_3_3_Z[i], 0);
            double p_y = RandUtils.rand(x_i + KERNEL_3_3_3_X[i], y_i + KERNEL_3_3_3_Y[i], z_i + KERNEL_3_3_3_Z[i], 1);
            double p_z = RandUtils.rand(x_i + KERNEL_3_3_3_X[i], y_i + KERNEL_3_3_3_Y[i], z_i + KERNEL_3_3_3_Z[i], 2);

            //dist calc + comparison
            double dist = Math.sqrt(
                (p_x + KERNEL_3_3_3_X[i] - x_r) * (p_x + KERNEL_3_3_3_X[i] - x_r) +
                (p_y + KERNEL_3_3_3_Y[i] - y_r) * (p_y + KERNEL_3_3_3_Y[i] - y_r) +
                (p_z + KERNEL_3_3_3_Z[i] - z_r) * (p_z + KERNEL_3_3_3_Z[i] - z_r)
            );
            res = res + Math.exp(-falloff * dist);
        }

        return -(1.0/falloff)*Math.log(res);
    }

    /**
     * Samples a diamond-like noise pattern based on voronoi noise
     * @param x The x coordinate
     * @param y The y coordinate
     * @param z The z coordinate
     * @return The noise value
     */
    public static double diamond(double x, double y, double z){
        //integer of the point coordinates
        double x_i = Math.floor(x);
        double y_i = Math.floor(y);
        double z_i = Math.floor(z);

        //remainders of the point coordinates
        double x_r = x - x_i;
        double y_r = y - y_i;
        double z_r = z - z_i;

        //calculate min dist
        double minDist = 10.0;
        double minDist2 = 10.0;
        double minDist3 = 10.0;
        for(int i = 0; i < KERNEL_3_3_3_X.length; i++){
            //the point of this cell
            double p_x = RandUtils.rand(x_i + KERNEL_3_3_3_X[i], y_i + KERNEL_3_3_3_Y[i], z_i + KERNEL_3_3_3_Z[i], 0);
            double p_y = RandUtils.rand(x_i + KERNEL_3_3_3_X[i], y_i + KERNEL_3_3_3_Y[i], z_i + KERNEL_3_3_3_Z[i], 1);
            double p_z = RandUtils.rand(x_i + KERNEL_3_3_3_X[i], y_i + KERNEL_3_3_3_Y[i], z_i + KERNEL_3_3_3_Z[i], 2);

            //dist calc + comparison
            double dist = Math.sqrt(
                (p_x + KERNEL_3_3_3_X[i] - x_r) * (p_x + KERNEL_3_3_3_X[i] - x_r) +
                (p_y + KERNEL_3_3_3_Y[i] - y_r) * (p_y + KERNEL_3_3_3_Y[i] - y_r) +
                (p_z + KERNEL_3_3_3_Z[i] - z_r) * (p_z + KERNEL_3_3_3_Z[i] - z_r)
            );
            if(dist < minDist){
                minDist3 = minDist2;
                minDist2 = minDist;
                minDist = dist;
            } else if(dist < minDist2){
                minDist3 = minDist2;
                minDist2 = dist;
            } else if(dist < minDist3){
                minDist3 = dist;
            }
        }

        double triPointDiff = Math.max(Math.max(Math.abs(minDist - minDist3),Math.abs(minDist2 - minDist3)),Math.abs(minDist - minDist2));
        minDist = minDist * (triPointDiff);

        return minDist;
    }

    /**
     * Samples a variant of the diamond noise that should have sharper minimums
     * @param x The x coordinate
     * @param y The y coordinate
     * @param z The z coordinate
     * @return The noise value
     */
    public static double diamondSharp(double x, double y, double z){
        //integer of the point coordinates
        double x_i = Math.floor(x);
        double y_i = Math.floor(y);
        double z_i = Math.floor(z);

        //remainders of the point coordinates
        double x_r = x - x_i;
        double y_r = y - y_i;
        double z_r = z - z_i;

        //calculate min dist
        double minDist = 10.0;
        double minDist2 = 10.0;
        double minDist3 = 10.0;
        for(int i = 0; i < KERNEL_3_3_3_X.length; i++){
            //the point of this cell
            double p_x = RandUtils.rand(x_i + KERNEL_3_3_3_X[i], y_i + KERNEL_3_3_3_Y[i], z_i + KERNEL_3_3_3_Z[i], 0);
            double p_y = RandUtils.rand(x_i + KERNEL_3_3_3_X[i], y_i + KERNEL_3_3_3_Y[i], z_i + KERNEL_3_3_3_Z[i], 1);
            double p_z = RandUtils.rand(x_i + KERNEL_3_3_3_X[i], y_i + KERNEL_3_3_3_Y[i], z_i + KERNEL_3_3_3_Z[i], 2);

            //dist calc + comparison
            double dist = Math.sqrt(
                (p_x + KERNEL_3_3_3_X[i] - x_r) * (p_x + KERNEL_3_3_3_X[i] - x_r) +
                (p_y + KERNEL_3_3_3_Y[i] - y_r) * (p_y + KERNEL_3_3_3_Y[i] - y_r) +
                (p_z + KERNEL_3_3_3_Z[i] - z_r) * (p_z + KERNEL_3_3_3_Z[i] - z_r)
            );
            if(dist < minDist){
                minDist3 = minDist2;
                minDist2 = minDist;
                minDist = dist;
            } else if(dist < minDist2){
                minDist3 = minDist2;
                minDist2 = dist;
            } else if(dist < minDist3){
                minDist3 = dist;
            }
        }

        double triPointDiff = Math.max(Math.max(Math.abs(minDist - minDist3),Math.abs(minDist2 - minDist3)),Math.abs(minDist - minDist2));
        double twoPointDiff = Math.abs(minDist - minDist2);
        minDist = minDist * (triPointDiff - twoPointDiff);

        return minDist;
    }
    
}
