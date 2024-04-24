package org.a4z0.lwjgl.demo.voxel.position;

/**
* Represents a {@link Position} in a three-dimensional space.
*
* @see Location
*/

public class Position {
    
    protected final int x, y, z;

    /**
    * Construct a {@link Position}.
    */

    public Position() {
        this(0, 0, 0);
    }

    /**
    * Construct a {@link Position}.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    */

    public Position(int x, int y, int z) {
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

    /**
    * Calculates the distance between this and the other {@link Position}.
    *
    * @param o Other {@link Position}.
    *
    * @return the distance between positions.
    */

    public int distance(Position o) {
        return this.distance(o.getX(), o.getY(), o.getZ());
    }

    /**
    * Calculates the distance between this {@link Position} and the coordinate.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return the distance between positions.
    */

    public int distance(int x, int y, int z) {
        return (int) (Math.pow((this.x - x), 2) + Math.pow((this.y - y), 2) + Math.pow((this.z - z), 2));
    }
}