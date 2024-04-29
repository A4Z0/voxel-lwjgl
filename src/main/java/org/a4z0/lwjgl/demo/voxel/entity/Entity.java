package org.a4z0.lwjgl.demo.voxel.entity;

import org.a4z0.lwjgl.demo.voxel.level.LevelAccess;
import org.a4z0.lwjgl.demo.voxel.position.Location;

/**
* Represents an Entity.
*/

public abstract class Entity {

    protected final Location location;

    /**
    * Construct a {@link Entity}.
    *
    * @param level ...
    */

    protected Entity(LevelAccess level) {
        this(level, 0f, 0f, 0f);
    }

    /**
    * Construct a {@link Entity}.
    *
    * @param level ...
    * @param x ...
    * @param y ...
    * @param z ...
    */

    protected Entity(LevelAccess level, float x, float y, float z) {
        this(new Location(level, x, y, z));
    }

    /**
    * Construct a {@link Entity}.
    *
    * @param level ...
    * @param x ...
    * @param y ...
    * @param z ...
    * @param yaw ...
    * @param pitch ...
    */

    protected Entity(LevelAccess level, float x, float y, float z, float yaw, float pitch) {
        this(new Location(level, x, y, z, yaw, pitch));
    }

    /**
    * Construct a {@link Entity}.
    *
    * @param location ...
    */

    protected Entity(final Location location) {
        this.location = location;
    }

    /**
    * @return this {@link Entity}'s {@link Location}.
    */

    public Location getLocation() {
        return this.location;
    }

    /**
    * Ticks this {@link Entity}.
    */

    public abstract void tick();
}
