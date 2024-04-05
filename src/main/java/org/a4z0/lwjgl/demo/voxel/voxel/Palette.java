package org.a4z0.lwjgl.demo.voxel.voxel;

/**
* Represents a Palette.
*/

public interface Palette {

    /**
    * Sets a Voxel in a Coordinate.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    * @param r Red.
    * @param g Green.
    * @param b Blue.
    */

    default void setVoxel(int x, int y, int z, int r, int g, int b) {
        this.setVoxel(x, y, z, r, g, b, false);
    }

    /**
    * Sets a Voxel in a Coordinate.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    * @param r Red.
    * @param g Green.
    * @param b Blue.
    * @param u Update?
    */

    default void setVoxel(int x, int y, int z, int r, int g, int b, boolean u) {
        this.setVoxel(x, y, z, r, g, b, 255, u);
    }

    /**
    * Sets a Voxel in a Coordinate.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    * @param r Red.
    * @param g Green.
    * @param b Blue.
    * @param a Alpha.
    */

    default void setVoxel(int x, int y, int z, int r, int g, int b, int a) {
        this.setVoxel(x, y, z, r, g, b, a, false);
    }

    /**
    * Sets a Voxel in a Coordinate.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    * @param r Red.
    * @param g Green.
    * @param b Blue.
    * @param a Alpha.
    * @param u Update?
    */

    void setVoxel(int x, int y, int z, int r, int g, int b, int a, boolean u);

    /**
    * Obtains a Voxel from a Coordinate.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return the Voxel Color.
    */

    int getVoxel(int x, int y, int z);
}