package org.a4z0.lwjgl.demo.voxel.block.voxel;

import org.a4z0.lwjgl.demo.voxel.math.Position3fc;

public class VoxelPosition implements Position3fc {

    protected int x, y, z;

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

    /**
    * Construct a {@link VoxelPosition}.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    */

    public VoxelPosition(float x, float y, float z) {
        this((int) (x / 0.0625f), (int) (y / 0.0625f), (int) (z / 0.0625f));
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
        return this.x & 255;
    }

    /**
    * @return the Relative Y-Axis.
    */

    public int getRelativeY() {
        return this.y & 255;
    }

    /**
    * @return the Relative Z-Axis.
    */

    public int getRelativeZ() {
        return this.z & 255;
    }

    /**
    * @return the Index of this {@link VoxelPosition}.
    */

    public int getIndex() {
        return (this.getRelativeY() << 8 | this.getRelativeZ()) << 8 | this.getRelativeX();
    }
}