package org.a4z0.lwjgl.demo.math;

public class Vector3i implements Vector3ic {
    
    protected int x, y, z;

    /**
    * Construct a {@link Vector3i}.
    */

    public Vector3i() {
        this.zero();
    }

    /**
    * Construct a {@link Vector3i}.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    */

    public Vector3i(int x, int y, int z) {
        this.set(x, y, z);
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public int getZ() {
        return this.z;
    }

    @Override
    public Vector3i set(Vector3ic o) {
        return this.set(o.getX(), o.getY(), o.getZ());
    }

    @Override
    public Vector3i set(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;

        return this;
    }

    @Override
    public Vector3i add(Vector3ic o) {
        return this.add(o.getX(), o.getY(), o.getZ());
    }

    @Override
    public Vector3i add(int x, int y, int z) {
        this.x += x;
        this.y += y;
        this.z += z;

        return this;
    }

    @Override
    public Vector3i subtract(Vector3ic o) {
        return this.subtract(o.getX(), o.getY(), o.getZ());
    }

    @Override
    public Vector3i subtract(int x, int y, int z) {
        this.x -= x;
        this.y -= y;
        this.z -= z;

        return this;
    }

    @Override
    public Vector3i multiply(int multiplier) {
        return this.multiply(multiplier, multiplier, multiplier);
    }

    @Override
    public Vector3i multiply(Vector3ic o) {
        return this.multiply(o.getX(), o.getY(), o.getZ());
    }

    @Override
    public Vector3i multiply(int x, int y, int z) {
        this.x *= x;
        this.y *= y;
        this.z *= z;

        return this;
    }

    @Override
    public Vector3i divide(int divisor) {
        return this.divide(divisor, divisor, divisor);
    }

    @Override
    public Vector3i divide(Vector3ic o) {
        return this.divide(o.getX(), o.getY(), o.getZ());
    }

    @Override
    public Vector3i divide(int x, int y, int z) {
        this.x /= x;
        this.y /= y;
        this.z /= z;

        return this;
    }

    @Override
    public int dot(int x, int y, int z) {
        return this.x * x + this.y * y + this.z * z;
    }

    @Override
    public Vector3i normalize() {
        return this.divide(this.length());
    }

    @Override
    public Vector3i zero() {
        return this.set(0, 0, 0);
    }

    @Override
    public int distance(int x, int y, int z) {
        return (int) (Math.pow((this.x - x), 2) + Math.pow((this.y - y), 2) + Math.pow((this.z - z), 2));
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof Vector3i)
            && this.x == ((Vector3i) o).x
            && this.y == ((Vector3i) o).y
            && this.z == ((Vector3i) o).z;
    }

    @Override
    public Vector3i clone() {
        try {
            return (Vector3i) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}