package org.a4z0.lwjgl.demo.voxel.entity;

import org.a4z0.lwjgl.demo.voxel.block.BlockState;
import org.a4z0.lwjgl.demo.voxel.level.Level;
import org.a4z0.lwjgl.demo.voxel.math.AABB;
import org.a4z0.lwjgl.demo.voxel.math.Location;

import java.util.ArrayList;
import java.util.List;

/**
* Represents an Entity Player.
*/

public class EntityPlayer extends EntityLiving {

    public static final float PLAYER_HEIGHT = 2.75f;
    public static final float PLAYER_WIDTH = 1f;

    public static final AABB PLAYER_AABB = new AABB((PLAYER_WIDTH / 2f), (PLAYER_HEIGHT / 2f), (PLAYER_WIDTH / 2f), -(PLAYER_WIDTH / 2f), -(PLAYER_HEIGHT / 2f), -(PLAYER_WIDTH / 2f));

    protected boolean flying;
    protected boolean is_on_ground = true;
    protected float gravity_force = 0f;

    @Deprecated
    protected float walk_speed = 0.1f;

    @Deprecated
    protected float flight_speed = 1f;

    protected AABB aabb;

    /**
    * Construct a {@link EntityPlayer}.
    *
    * @param name {@link EntityPlayer}'s Name.
    * @param level {@link Level} this {@link EntityPlayer} will be at.
    */

    public EntityPlayer(@Deprecated String name, Level level) {
        this(name, level, 0f, 0f, 0f);
    }

    /**
    * Construct a {@link EntityPlayer}.
    *
    * @param name {@link EntityPlayer}'s Name.
    * @param level {@link Level} this {@link EntityPlayer} will be at.
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    */

    public EntityPlayer(@Deprecated String name, Level level, float x, float y, float z) {
        this(name, level, x, y, z, 0f, 0f);
    }

    /**
    * Construct a {@link EntityPlayer}.
    *
    * @param name {@link EntityPlayer}'s Name.
    * @param level {@link Level} this {@link EntityPlayer} will be at.
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    * @param yaw Yaw.
    * @param pitch Pitch.
    */

    public EntityPlayer(@Deprecated String name, Level level, float x, float y, float z, float yaw, float pitch) {
        this(name, new Location(level, x, y, z, yaw, pitch));
    }

    /**
    * Construct a {@link EntityPlayer}.
    *
    * @param name {@link EntityPlayer}'s Name.
    * @param location {@link Location} where this {@link EntityPlayer} will be.
    */

    public EntityPlayer(@Deprecated String name, Location location) {
        super(name, location);

        this.aabb = new AABB((PLAYER_WIDTH / 2f), (PLAYER_HEIGHT / 2f), (PLAYER_WIDTH / 2f), -(PLAYER_WIDTH / 2f), -(PLAYER_HEIGHT / 2f), -(PLAYER_WIDTH / 2f));
    }

    /**
    * @return the Player's AABB.
    */

    public AABB getAABB() {
        return this.aabb;
    }

    /**
    * @return the Walk speed.
    */

    public float getWalkSpeed() {
        return this.walk_speed;
    }

    /**
    * Sets the Walk speed.
    *
    * @param walkSpeed Walk Speed.
    */

    public void setWalkSpeed(float walkSpeed) {
        this.walk_speed = walkSpeed;
    }

    /**
    * @return true if this {@link EntityPlayer} is flying, false otherwise.
    */

    public boolean isFlying() {
        return this.flying;
    }

    /**
    * Sets the Flight.
    *
    * @param flying Flying?
    */

    public void setFlying(boolean flying) {
        this.flying = flying;
    }

    /**
    * @return the Flight Speed.
    */

    public float getFlightSpeed() {
        return this.flight_speed;
    }

    /**
    * Sets the Flight Speed.
    *
    * @param flightSpeed Flight Speed.
    */

    public void setFlightSpeed(float flightSpeed) {
        this.flight_speed = flightSpeed;
    }

    @Override
    public void tick() {
        this.tickGround();
        this.tickGravity();
    }

    private void tickGround() {
        for(BlockState Block : this.getFeet()) {
            if(Block.getColor() != 0) {
                this.is_on_ground = true;

                return;
            }
        }

        this.is_on_ground = false;
    }

    private void tickGravity() {
        if(this.is_on_ground) {
            this.gravity_force = 0f;
        } else {
            if(this.gravity_force < 14f) {
                this.gravity_force += 0.01f;
            }

            float newY = (this.location.getY() - this.gravity_force);
            float diff = 0;

            BlockState tracedBlock = null;

            for(int i = this.getLocation().getNearestY(); i < (128 - this.getLocation().getNearestY()); i++) {
                for(BlockState Block : this.getUnder(i)) {
                    tracedBlock = Block;

                    break;
                }
            }

            if(
                tracedBlock != null && tracedBlock.getPosition().getY() < newY
            ) {
                diff = this.gravity_force - tracedBlock.getPosition().getY();
            }

            this.location.subtract(0, this.gravity_force - diff, 0);
        }
    }

    private List<BlockState> getFeet() {
        return this.getUnder(-1);
    }

    private List<BlockState> getUnder(int i) {
        List<BlockState> Blocks = new ArrayList<>();

        for(int x = -1; x <= 1; x ++) {
            for(int z = -1; z <= 1; z ++) {
                Blocks.add(this.getLocation().getLevel().getBlockAt(
                    x + this.getLocation().getNearestX(),
                    i + this.getLocation().getNearestY(),
                    z + this.getLocation().getNearestZ()
                ));
            }
        }

        return Blocks;
    }
}