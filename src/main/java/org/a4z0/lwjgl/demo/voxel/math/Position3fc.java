package org.a4z0.lwjgl.demo.voxel.math;

public interface Position3fc {

    /**
    * @return the Z-Axis.
    */

    float getX();

    /**
    * @return the Y-Axis.
    */

    float getY();

    /**
    * @return the Z-Axis.
    */

    float getZ();

    /**
    * Checks if this {@link Position3fc} is equals to the given {@link Object}, false otherwise.
    *
    * @param o {@link Object} to be checked.
    *
    * @return true if equal, false otherwise.
    */

    boolean equals(Object o);
}