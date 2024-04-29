package org.a4z0.lwjgl.demo.voxel.legacy.geometry;

/**
* Represents a Plane.
*/

public class Plane {

    public float x;
    public float y;
    public float z;

    public float distance;

    /**
    * Construct a {@link Plane}.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    * @param distance Distance.
    */

    public Plane(final float x, final float y, final float z, final float distance) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.distance = distance;
    }

    /**
    * @return the X-Axis.
    */

    public float getX() {
        return this.x;
    }

    /**
    * @return the Y-Axis.
    */

    public float getY() {
        return this.y;
    }

    /**
    * @return the Z-Axis.
    */

    public float getZ() {
        return this.z;
    }

    /**
    * @return the Distance.
    */

    public float getDistance() {
        return this.distance;
    }

    /**
    * Sets the Distance.
    *
    * @param w Distance to be set.
    *
    * @return this {@link Plane}.
    */

    public Plane setDistance(float w) {
        this.distance = w;

        return this;
    }

    /**
    * Sets the X, Y and Z axis.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return this {@link Plane}.
    */

    public Plane set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;

        return this;
    }

    /**
    * Normalizes this {@link Plane}.
    *
    * @return this {@link Plane}.
    */

    public Plane normalize() {
        float length = (float) Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);

        this.x /= length;
        this.y /= length;
        this.z /= length;
        this.distance /= length;

        return this;
    }

    /**
    * Calculates the distance between this {@link Plane} and the coordinate.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return the distance between coordinates.
    */

    public float distance(float x, float y, float z) {
        return this.x * x + this.y * y + this.z * z + this.distance;
    }
}