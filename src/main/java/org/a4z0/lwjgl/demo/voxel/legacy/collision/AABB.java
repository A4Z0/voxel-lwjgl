package org.a4z0.lwjgl.demo.voxel.legacy.collision;

import org.joml.Math;

/**
* Represents an {@link AABB Axis Aligned Bounding Box}.
*/

public class AABB {

    protected final float x1, y1, z1;
    protected final float x2, y2, z2;

    /**
    * Construct a {@link AABB}.
    *
    * @param x1 Minimum X-Axis.
    * @param y1 Minimum Y-Axis.
    * @param z1 Minimum Z-Axis.
    * @param x2 Maximum X-Axis.
    * @param y2 Maximum Y-Axis.
    * @param z2 Maximum Z-Axis.
    */

    public AABB(float x1, float y1, float z1, float x2, float y2, float z2) {
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

    public float getLowerX() {
        return this.x1;
    }

    /**
    * @return the minimum Y-Axis.
    */

    public float getLowerY() {
        return this.y1;
    }

    /**
    * @return the minimum Z-Axis.
    */

    public float getLowerZ() {
        return this.z1;
    }

    /**
    * @return the maximum X-Axis.
    */

    public float getUpperX() {
        return this.x2;
    }

    /**
    * @return the maximum Y-Axis.
    */

    public float getUpperY() {
        return this.y2;
    }

    /**
    * @return the maximum Z-Axis.
    */

    public float getUpperZ() {
        return this.z2;
    }

    /**
    * Checks if this {@link AABB} intersects another {@link AABB}.
    *
    * @param other Other {@link AABB}.
    *
    * @return true if this {@link AABB} intersects the other {@link AABB}, false otherwise.
    */

    public boolean intersects(AABB other) {
        return this.x1 <= other.x2 && this.x2 >= other.x1 && this.y1 <= other.y2 && this.y2 >= other.y1 && this.z1 <= other.z2 && this.z2 >= other.z1;
    }

    /**
    * Checks if this {@link AABB} intersects the coordinates.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return true if this {@link AABB} intersects the coordinates, false otherwise.
    */

    public boolean intersects(float x, float y, float z) {
        return x >= this.x1 && x <= this.x2 && y >= this.y1 && y <= this.y2 && z >= this.z1 && z <= this.z2;
    }
}