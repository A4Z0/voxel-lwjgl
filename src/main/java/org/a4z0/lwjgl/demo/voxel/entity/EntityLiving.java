package org.a4z0.lwjgl.demo.voxel.entity;

import org.a4z0.lwjgl.demo.voxel.level.Level;
import org.a4z0.lwjgl.demo.voxel.math.Location;

/**
* Represents an Entity Living.
*/

public abstract class EntityLiving extends Entity {

    protected final String name;

    /**
    * Construct a {@link EntityLiving}.
    *
    * @param name {@link EntityLiving}'s Name.
    * @param level {@link Level} this {@link EntityLiving} will be at.
    */

    protected EntityLiving(@Deprecated String name, Level level) {
        this(name, level, 0f, 0f, 0f);
    }

    /**
    * Construct a {@link EntityLiving}.
    *
    * @param name {@link EntityLiving}'s Name.
    * @param level {@link Level} this {@link EntityLiving} will be at.
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    */

    protected EntityLiving(@Deprecated String name, Level level, float x, float y, float z) {
        this(name, level, x, y, z, 0f, 0f);
    }

    /**
    * Construct a {@link EntityLiving}.
    *
    * @param name {@link EntityLiving}'s Name.
    * @param level {@link Level} this {@link EntityLiving} will be at.
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    * @param yaw Yaw.
    * @param pitch Pitch.
    */

    public EntityLiving(@Deprecated String name, Level level, float x, float y, float z, float yaw, float pitch) {
        this(name, new Location(level, x, y, z, yaw, pitch));
    }

    /**
    * Construct a {@link EntityLiving}.
    *
    * @param location {@link Location} where this {@link EntityLiving} will be.
    */

    public EntityLiving(@Deprecated String name, Location location) {
        super(location);

        this.name = name;
    }

    /**
    * @return the Name.
    */

    @Deprecated
    public String getName() {
        return this.name;
    }
}