package org.a4z0.lwjgl.demo.voxel.math;

/**
* Represents a Block Position.
*/

public class BlockPosition {

    protected final int x, y, z;

    /**
    * Construct a {@link BlockPosition}.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    */

    public BlockPosition(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
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
}