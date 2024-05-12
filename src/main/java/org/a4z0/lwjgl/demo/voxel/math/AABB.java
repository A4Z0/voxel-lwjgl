package org.a4z0.lwjgl.demo.voxel.math;

import org.joml.Math;

/**
* Represents an Axis Aligned Bounding Box.
*/

public class AABB implements Cloneable {

    protected float x1, y1, z1;
    protected float x2, y2, z2;

    /**
    * Construct a {@link AABB}.
    *
    * @param min Minimum.
    * @param max Maximum.
    */

    public AABB(Vector3f min, Vector3f max) {
        this(min.getX(), min.getY(), min.getZ(), max.getX(), max.getY(), max.getZ());
    }

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
    * @return the {@link Vector3f} of the Lower Northeast corner.
    */

    public Vector3f getUpperSE() {
        return new Vector3f(this.x1, this.y1, this.z1);
    }

    /**
    * @return the {@link Vector3f} of the Upper Southwest corner.
    */

    public Vector3f getLowerSW() {
        return new Vector3f(this.x2, this.y2, this.z2);
    }

    /**
    * @return a {@link Vector3f} corresponding to the center.
    */

    public Vector3f getCenter() {
        return new Vector3f((this.x1 + this.x2) / 2.0f, (this.y1 + this.y2) / 2.0f, (this.z1 + this.z2) / 2.0f);
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
    * Translates this {@link AABB} by the {@link Vector3f}.
    *
    * @param v3 {@link Vector3f}.
    *
    * @return this {@link AABB}.
    */

    public AABB translate(Vector3f v3) {
        return this.translate(v3.getZ(), v3.getY(), v3.getZ());
    }

    /**
    * Translates this {@link AABB} by the axes.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return this {@link AABB}.
    */

    public AABB translate(float x, float y, float z) {
        this.x1 += x;
        this.y1 += y;
        this.z1 += z;
        this.x2 += x;
        this.y2 += y;
        this.z2 += z;

        return this;
    }

    /**
    * ...
    *
    * @param o ...
    *
    * @return ...
    */

    public boolean intersects(AABB o) {
        return this.x1 <= o.x2 && this.x2 >= o.x1 && this.y1 <= o.y2 && this.y2 >= o.y1 && this.z1 <= o.z2 && this.z2 >= o.z1;
    }

    /**
    * ...
    *
    * @param x ...
    * @param y ...
    * @param z ...
    *
    * @return ...
    */

    public boolean intersects(float x, float y, float z) {
        return x >= this.x1 && x <= this.x2 && y >= this.y1 && y <= this.y2 && z >= this.z1 && z <= this.z2;
    }

    /**
    * @return a clone of this {@link AABB}.
    */

    @Override
    public AABB clone() {
        try {
            return (AABB) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}