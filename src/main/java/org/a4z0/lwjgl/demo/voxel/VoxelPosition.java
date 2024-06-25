package org.a4z0.lwjgl.demo.voxel;

import org.a4z0.lwjgl.demo.math.Position3fc;

public class VoxelPosition implements Position3fc {

    protected final int x, y, z;

    /**
    * Construct a {@link VoxelPosition}.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    */

    public VoxelPosition(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public float getX() {
        return this.x * 0.0625f;
    }

    @Override
    public float getY() {
        return this.y * 0.0625f;
    }

    @Override
    public float getZ() {
        return this.z * 0.0625f;
    }

    /**
    * @return the Relative X-Axis.
    */

    public int getRelativeX() {
        return this.x & 0xFF;
    }

    /**
    * @return the Relative Y-Axis.
    */

    public int getRelativeY() {
        return this.y & 0xFF;
    }

    /**
    * @return the Relative Z-Axis.
    */

    public int getRelativeZ() {
        return this.z & 0xFF;
    }

    /**
    * @return the Index of this {@link VoxelPosition}.
    */

    public int getIndex() {
        return (this.getRelativeY() << 8 | this.getRelativeZ()) << 8 | this.getRelativeX();
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof VoxelPosition) && ((VoxelPosition) o).x == this.x && ((VoxelPosition) o).y == this.y && ((VoxelPosition) o).z == this.z;
    }
}