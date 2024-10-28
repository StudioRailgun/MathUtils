package io.github.studiorailgun;

/**
 * Noise generation functions
 */
public class NoiseUtils {

    /**
     * x offsets for a 3x3 kernel
     */
    static final int[] KERNEL_3_3_X = new int[]{
        1, 0, -1,
        1, 0, -1,
        1, 0, -1,
    };

    /**
     * y offsets for a 3x3 kernel
     */
    static final int[] KERNEL_3_3_Y = new int[]{
         1,  1,  1,
         0,  0,  0,
        -1, -1, -1,
    };

    /**
     * x offsets for a 3x3x3 kernel
     */
    static final int[] KERNEL_3_3_3_X = new int[]{
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
    static final int[] KERNEL_3_3_3_Y = new int[]{
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
    static final int[] KERNEL_3_3_3_Z = new int[]{
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
     * Calculates voronoi noise on a 2d plane
     * @param x The x coordinate
     * @param y The y coordinate
     * @return The voronoi value
     */
    public static double voronoi(double x, double y){
        //integer of the point coordinates
        double x_i = Math.floor(x);
        double y_i = Math.floor(y);

        //remainders of the point coordinates
        double x_r = x - x_i;
        double y_r = y - y_i;

        //calculate min dist
        double minDist = 10.0;
        for(int i = 0; i < KERNEL_3_3_X.length; i++){
            //the point of this cell
            double p_x = MathUtils.rand(x_i + KERNEL_3_3_X[i], y_i + KERNEL_3_3_Y[i], 0);
            double p_y = MathUtils.rand(x_i + KERNEL_3_3_X[i], y_i + KERNEL_3_3_Y[i], 1);

            //dist calc + comparison
            double dist = Math.sqrt((p_x + KERNEL_3_3_X[i] - x_r) * (p_x + KERNEL_3_3_X[i] - x_r) + (p_y + KERNEL_3_3_Y[i] - y_r) * (p_y + KERNEL_3_3_Y[i] - y_r));
            if(dist < minDist){
                minDist = dist;
            }
        }

        return minDist;
    }

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
            double p_x = MathUtils.rand(x_i + KERNEL_3_3_3_X[i], y_i + KERNEL_3_3_3_Y[i], z_i + KERNEL_3_3_3_Z[i], 0);
            double p_y = MathUtils.rand(x_i + KERNEL_3_3_3_X[i], y_i + KERNEL_3_3_3_Y[i], z_i + KERNEL_3_3_3_Z[i], 1);
            double p_z = MathUtils.rand(x_i + KERNEL_3_3_3_X[i], y_i + KERNEL_3_3_3_Y[i], z_i + KERNEL_3_3_3_Z[i], 2);

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
        double cell_x = MathUtils.rand(x_i,y_i,0);
        double cell_y = MathUtils.rand(x_i,y_i,1);

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
    
}
