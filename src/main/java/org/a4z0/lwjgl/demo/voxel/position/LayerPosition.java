package org.a4z0.lwjgl.demo.voxel.position;

import org.a4z0.lwjgl.demo.voxel.pipeline.IPipeline;

/**
* ...
*/

public class LayerPosition {

    protected final int x;
    protected final int y;
    protected final int z;

    /**
    * Construct a {@link LayerPosition}.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    */

    public LayerPosition(int x, int y, int z) {
        this.x = (x >> IPipeline.LAYER_SIZE_X_BIT_SHIFT) * IPipeline.LAYER_SIZE_X;
        this.y = (y >> IPipeline.LAYER_SIZE_Y_BIT_SHIFT) * IPipeline.LAYER_SIZE_Y;
        this.z = (z >> IPipeline.LAYER_SIZE_Z_BIT_SHIFT) * IPipeline.LAYER_SIZE_Z;
    }

    /**
    * @return the X-Axis.
    */

    public int getX() {
        return this.x;
    }

    /**
    * @return the Y-Axis.
    */

    public int getY() {
        return this.y;
    }

    /**
    * @return the Z-Axis.
    */

    public int getZ() {
        return this.z;
    }

    @Deprecated
    public static int getIndex(int x, int y, int z) {
        return ((y) << IPipeline.LAYER_SIZE_X_BIT_SHIFT | (z)) << IPipeline.LAYER_SIZE_Z_BIT_SHIFT | x;
    }

    public static int asBlock(int x, int y, int z) {
        return getIndex(x >> IPipeline.LAYER_SIZE_X_BIT_SHIFT, y >> IPipeline.LAYER_SIZE_Y_BIT_SHIFT, z >> IPipeline.LAYER_SIZE_Z_BIT_SHIFT);
    }
}