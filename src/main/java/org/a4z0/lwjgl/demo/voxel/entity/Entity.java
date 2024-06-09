package org.a4z0.lwjgl.demo.voxel.entity;

import org.a4z0.lwjgl.demo.voxel.level.Location;
import org.a4z0.lwjgl.demo.voxel.nbt.NBTTagCompound;
import org.a4z0.lwjgl.demo.voxel.level.Level;

public abstract class Entity {

    protected final Location LOCATION;
    protected final NBTTagCompound NBT_TAG_COMPOUND = new NBTTagCompound();

    /**
    * Construct a {@link Entity}.
    *
    * @param level {@link Level} this {@link Entity} will be at.
    */

    protected Entity(Level level) {
        this(level, 0f, 0f, 0f);
    }

    /**
    * Construct a {@link Entity}.
    *
    * @param level {@link Level} this {@link Entity} will be at.
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    */

    protected Entity(Level level, float x, float y, float z) {
        this(level, x, y, z, 0f, 0f);
    }

    /**
    * Construct a {@link Entity}.
    *
    * @param level {@link Level} this {@link Entity} will be at.
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    * @param yaw Yaw.
    * @param pitch Pitch.
    */

    protected Entity(Level level, float x, float y, float z, float yaw, float pitch) {
        this(new Location(level, x, y, z, yaw, pitch));
    }

    /**
    * Construct a {@link Entity}.
    *
    * @param location {@link Location} where this {@link Entity} will be.
    */

    protected Entity(Location location) {
        this.LOCATION = location;
    }

    /**
    * Retrieves the {@link Location} where this {@link Entity} is.
    *
    * @return a {@link Location}.
    */

    public Location getLocation() {
        return this.LOCATION;
    }

    /**
    * Ticks this {@link Entity}.
    */

    public abstract void tick();
}
