package org.a4z0.lwjgl.demo.voxel.entity;

import org.a4z0.lwjgl.demo.voxel.camera.Camera;
import org.a4z0.lwjgl.demo.voxel.level.LevelAccess;
import org.a4z0.lwjgl.demo.voxel.position.Location;

/**
* ...
*/

public class EntityPlayer extends EntityLiving implements Player {

    protected final Camera camera;

    protected float walkSpeed;
    protected float flightSpeed;

    /**
    * Construct a {@link EntityPlayer}.
    *
    * @param name ...
    * @param camera ...
    * @param level ...
    */

    public EntityPlayer(@Deprecated String name, Camera camera, LevelAccess level) {
        this(name, camera, new Location(level));
    }

    /**
    * Construct a {@link EntityPlayer}.
    *
    * @param name ...
    * @param camera ...
    * @param level ...
    * @param x ...
    * @param y ...
    * @param z ...
    */

    public EntityPlayer(@Deprecated String name, Camera camera, LevelAccess level, float x, float y, float z) {
        this(name, camera, new Location(level, x, y, z));
    }

    /**
    * Construct a {@link EntityPlayer}.
    *
    * @param name ...
    * @param camera ...
    * @param level ...
    * @param x ...
    * @param y ...
    * @param z ...
    * @param yaw ...
    * @param pitch ...
    */

    public EntityPlayer(@Deprecated String name, Camera camera, LevelAccess level, float x, float y, float z, float yaw, float pitch) {
        this(name, camera, new Location(level, x, y, z, yaw, pitch));
    }

    /**
    * Construct a {@link EntityPlayer}.
    *
    * @param name ...
    * @param camera ...
    * @param location ...
    */

    public EntityPlayer(@Deprecated String name, Camera camera, Location location) {
        super(name, location);

        this.camera = camera;

        this.walkSpeed = DEFAULT_WALK_SPEED;
        this.flightSpeed = DEFAULT_FLIGHT_SPEED;
    }

    @Override
    public void tick() {

    }

    @Override
    public void setWalkSpeed(float f) {
        this.walkSpeed = f;
    }

    @Override
    public void setFlightSpeed(float f) {
        this.flightSpeed = f;
    }
}