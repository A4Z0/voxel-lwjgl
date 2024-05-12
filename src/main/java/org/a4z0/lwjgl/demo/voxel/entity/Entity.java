package org.a4z0.lwjgl.demo.voxel.entity;

import org.a4z0.lwjgl.demo.voxel.Main;
import org.a4z0.lwjgl.demo.voxel.block.BlockState;
import org.a4z0.lwjgl.demo.voxel.level.Level;
import org.a4z0.lwjgl.demo.voxel.math.Location;

/**
* Represents an Entity.
*/

public abstract class Entity {

    protected final Location location;

    /**
    * Construct a {@link Entity}.
    *
    * @param level {@link Level} this {@link Entity} will be at.
    */

    public Entity(Level level) {
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

    public Entity(Level level, float x, float y, float z) {
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

    public Entity(Level level, float x, float y, float z, float yaw, float pitch) {
        this(new Location(level, x, y, z, yaw, pitch));
    }

    /**
    * Construct a {@link Entity}.
    *
    * @param location {@link Location} where this {@link Entity} will be.
    */

    public Entity(Location location) {
        this.location = location;
    }

    /**
    * @return this {@link Entity}'s {@link Location}.
    */

    public Location getLocation() {
        return this.location;
    }

    /**
    * @return true if the {@link Entity} is on the ground.
    */

    public boolean isOnGround() {
        return true;
        //return !Main.TEST_LEVEL.getBlockAt(this.getLocation().getNearestX(), this.getLocation().getNearestY() -1, this.getLocation().getNearestZ()).compare(BlockState.EMPTY_BLOCK_STATE);
    }

    /**
    * Ticks this {@link Entity}.
    */

    public abstract void tick();
}
