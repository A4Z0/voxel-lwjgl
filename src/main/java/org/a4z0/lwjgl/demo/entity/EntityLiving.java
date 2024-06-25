package org.a4z0.lwjgl.demo.entity;

import org.a4z0.lwjgl.demo.level.Level;
import org.a4z0.lwjgl.demo.level.Location;

public abstract class EntityLiving extends Entity {

    /**
    * Construct a {@link EntityLiving}.
    *
    * @param name {@link EntityLiving}'s Name.
    * @param level {@link Level} this {@link EntityLiving} will be at.
    */

    protected EntityLiving(String name, Level level) {
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

    protected EntityLiving(String name, Level level, float x, float y, float z) {
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

    protected EntityLiving(String name, Level level, float x, float y, float z, float yaw, float pitch) {
        this(name, new Location(level, x, y, z, yaw, pitch));
    }

    /**
    * Construct a {@link EntityLiving}.
    *
    * @param location {@link Location} where this {@link EntityLiving} will be.
    */

    protected EntityLiving(String name, Location location) {
        super(location);

        this.setName(name);
    }

    /**
    * @return the {@link EntityLiving}'s Name.
    */

    public String getName() {
        return this.tag.getString("name");
    }

    /**
    * Sets the {@link EntityLiving}'s Name.
    *
    * @param name Name to be set.
    */

    public void setName(String name) {
        this.tag.setString("name", name);
    }
}