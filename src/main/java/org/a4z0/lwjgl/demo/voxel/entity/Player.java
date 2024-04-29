package org.a4z0.lwjgl.demo.voxel.entity;

import org.a4z0.lwjgl.demo.voxel.position.Location;

/**
* ...
*/

public interface Player {

    float DEFAULT_WALK_SPEED = 1f;
    float DEFAULT_FLIGHT_SPEED = 1f;

    /**
    * @return this {@link Player}'s Location.
    */

    Location getLocation();

    /**
    * Sets this {@link Player}'s walking speed.
    *
    * @param f ...
    */

    void setWalkSpeed(float f);

    /**
    * Sets this {@link Player}'s flight speed.
    *
    * @param f ...
    */

    void setFlightSpeed(float f);
}