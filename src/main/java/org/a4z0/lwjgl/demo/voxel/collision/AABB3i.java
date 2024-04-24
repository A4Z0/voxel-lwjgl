package org.a4z0.lwjgl.demo.voxel.collision;

import org.joml.Math;

/**
* Represents an Axis Aligned Bounding Box.
*/

public class AABB3i {

    protected final int x1, y1, z1;
    protected final int x2, y2, z2;

    /**
    * Construct a {@link AABB3i}.
    *
    * @param x1 Minimum X-Axis.
    * @param y1 Minimum Y-Axis.
    * @param z1 Minimum Z-Axis.
    * @param x2 Maximum X-Axis.
    * @param y2 Maximum Y-Axis.
    * @param z2 Maximum Z-Axis.
    */

    public AABB3i(int x1, int y1, int z1, int x2, int y2, int z2) {
        this.x1 = Math.min(x1, x2);
        this.y1 = Math.min(y1, y2);
        this.z1 = Math.min(z1, z2);
        this.x2 = Math.max(x1, x2);
        this.y2 = Math.max(y1, y2);
        this.z2 = Math.max(z1, z2);
    }

    /**
    * @return the minimum X-Axis.
    */

    public int getLowerX() {
        return this.x1;
    }

    /**
    * @return the minimum Y-Axis.
    */

    public int getLowerY() {
        return this.y1;
    }

    /**
    * @return the minimum Z-Axis.
    */

    public int getLowerZ() {
        return this.z1;
    }

    /**
    * @return the maximum X-Axis.
    */

    public int getUpperX() {
        return this.x2;
    }

    /**
    * @return the maximum Y-Axis.
    */

    public int getUpperY() {
        return this.y2;
    }

    /**
    * @return the maximum Z-Axis.
    */

    public int getUpperZ() {
        return this.z2;
    }

    /**
    * Checks if this {@link AABB3i} intersects another {@link AABB3i}.
    *
    * @param other Other {@link AABB3i}.
    *
    * @return true if this {@link AABB3i} intersects the other {@link AABB3i}, false otherwise.
    */

    public boolean intersects(AABB3i other) {
        return this.x1 <= other.x2 && this.x2 >= other.x1 && this.y1 <= other.y2 && this.y2 >= other.y1 && this.z1 <= other.z2 && this.z2 >= other.z1;
    }

    /**
    * Checks if this {@link AABB3i} intersects the coordinates.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return true if this {@link AABB3i} intersects the coordinates, false otherwise.
    */

    public boolean intersects(int x, int y, int z) {
        return x >= this.x1 && x <= this.x2 && y >= this.y1 && y <= this.y2 && z >= this.z1 && z <= this.z2;
    }
}