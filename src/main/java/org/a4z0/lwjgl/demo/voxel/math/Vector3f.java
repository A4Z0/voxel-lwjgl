package org.a4z0.lwjgl.demo.voxel.math;

/**
* Represents a Vector in a three-dimensional space.
*/

public class Vector3f implements Cloneable {
    
    protected float x, y, z;

    /**
    * Construct a {@link Vector3f}.
    */

    public Vector3f() {
        this(0, 0, 0);
    }

    /**
    * Construct a {@link Vector3f}.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    */

    public Vector3f(float x, float y, float z) {
        this.set(x, y, z);
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
    * @return the X-Axis of the Nearest Integer.
    */

    public int getNearestX() {
        return (int) Math.floor(this.x);
    }

    /**
    * @return the Y-Axis of the Nearest Integer.
    */

    public int getNearestY() {
        return (int) Math.floor(this.y);
    }

    /**
    * @return the Z-Axis of the Nearest Integer.
    */

    public int getNearestZ() {
        return (int) Math.floor(this.z);
    }

    /**
    * Sets this {@link Vector3f} by another.
    *
    * @param o Other {@link Vector3f}.
    *
    * @return this {@link Vector3f}.
    */

    public Vector3f set(Vector3f o) {
        return this.set(o.x, o.y, o.z);
    }

    /**
    * Sets the axes by other axes.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return this {@link Vector3f}.
    */

    public Vector3f set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;

        return this;
    }

    /**
    * Adds this {@link Vector3f} by another.
    *
    * @param o Other {@link Vector3f}.
    *
    * @return this {@link Vector3f}.
    */

    public Vector3f add(Vector3f o) {
        return this.add(o.x, o.y, o.z);
    }

    /**
    * Adds the axes by other axes.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return this {@link Vector3f}.
    */

    public Vector3f add(float x, float y, float z) {
        this.x += x;
        this.y += y;
        this.z += z;

        return this;
    }

    /**
    * Subtracts this {@link Vector3f} by another.
    *
    * @param o Other {@link Vector3f}.
    *
    * @return this {@link Vector3f}.
    */

    public Vector3f subtract(Vector3f o) {
        return this.subtract(o.x, o.y, o.z);
    }

    /**
    * Subtracts the axes by other axes.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return this {@link Vector3f}.
    */

    public Vector3f subtract(float x, float y, float z) {
        this.x -= x;
        this.y -= y;
        this.z -= z;

        return this;
    }

    /**
    * Performs a scalar multiplication, multiplying this {@link Vector3f} by another.
    *
    * @param o Other {@link Vector3f}.
    *
    * @return this {@link Vector3f}.
    */

    public Vector3f multiply(Vector3f o) {
        return this.multiply(o.x, o.y, o.z);
    }

    /**
    * Performs a scalar multiplication, multiplying all axes by another axes.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return this {@link Vector3f}.
    */

    public Vector3f multiply(float x, float y, float z) {
        this.x *= x;
        this.y *= y;
        this.z *= z;

        return this;
    }

    /**
    * Normalizes this {@link Vector3f}.
    *
    * @return this {@link Vector3f}.
    */

    public Vector3f normalize() {
        float magnitude = (float) Math.sqrt((Math.pow(2, this.x)) + (Math.pow(2, this.y)) + (Math.pow(2, this.z)));

        if(magnitude != 0) {
            this.x /= magnitude;
            this.y /= magnitude;
            this.z /= magnitude;
        }

        return this;
    }

    /**
    * Calculates the distance between this {@link Vector3f} and the other.
    *
    * @param o Other {@link Vector3f}.
    *
    * @return the distance between the axes.
    */

    public float distance(Vector3f o) {
        return this.distance(o.x, o.y, o.z);
    }

    /**
    * Calculates the distance between this {@link Vector3f} and the axes.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return the distance between the axes.
    */

    public float distance(float x, float y, float z) {
        return (float) (Math.pow((this.x - x), 2) + Math.pow((this.y - y), 2) + Math.pow((this.z - z), 2));
    }

    /**
    * @return a clone of this {@link Vector3f}.
    */

    @Override
    public Vector3f clone() {
        try {
            return ((Vector3f) super.clone());
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}