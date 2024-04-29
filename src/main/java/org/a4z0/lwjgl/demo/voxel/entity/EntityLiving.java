package org.a4z0.lwjgl.demo.voxel.entity;

import org.a4z0.lwjgl.demo.voxel.level.LevelAccess;
import org.a4z0.lwjgl.demo.voxel.position.Location;

/**
* Represents an Entity Living.
*/

public abstract class EntityLiving extends Entity {

    protected final String name;

    /**
    * Construct a {@link EntityLiving}.
    *
    * @param name ...
    * @param level ...
    */

    protected EntityLiving(@Deprecated String name, LevelAccess level) {
        this(name, new Location(level));
    }

    /**
    * Construct a {@link EntityLiving}.
    *
    * @param name ...
    * @param x ...
    * @param y ...
    * @param z ...
    */

    protected EntityLiving(@Deprecated String name, LevelAccess level, float x, float y, float z) {
        this(name, new Location(level, x, y, z));
    }

    /**
    * Construct a {@link EntityLiving}.
    *
    * @param name ...
    * @param level ...
    * @param x ...
    * @param y ...
    * @param z ...
    * @param yaw ...
    * @param pitch ...
    */

    protected EntityLiving(@Deprecated String name, LevelAccess level, float x, float y, float z, float yaw, float pitch) {
        this(name, new Location(level, x, y, z, yaw, pitch));
    }

    /**
    * Construct a {@link EntityLiving}.
    *
    * @param location ...
    */

    protected EntityLiving(@Deprecated String name, Location location) {
        super(location);

        this.name = name;
    }

    /**
    * @return this {@link EntityLiving}'s Name.
    */

    @Deprecated
    public String getName() {
        return this.name;
    }
}