package org.a4z0.lwjgl.demo.voxel.entity;

import org.a4z0.lwjgl.demo.voxel.Main;
import org.a4z0.lwjgl.demo.voxel.block.voxel.Voxel;
import org.a4z0.lwjgl.demo.voxel.legacy.util.Input;
import org.a4z0.lwjgl.demo.voxel.level.Level;
import org.a4z0.lwjgl.demo.voxel.level.Location;
import org.a4z0.lwjgl.demo.voxel.math.AABBfc;
import org.a4z0.lwjgl.demo.voxel.nbt.NBTTagCompound;
import org.a4z0.lwjgl.demo.voxel.math.AABBf;
import org.a4z0.lwjgl.demo.voxel.render.shader.pre.VGShaders;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;

public class EntityPlayer extends EntityLiving {

    public static final float GRAVITY_FORCE = 0.01f;

    public static final float DEFAULT_WALK_SPEED = 0.1f;
    public static final float DEFAULT_FLIGHT_SPEED = 0.1f;

    public static final AABBf DEFAULT_PLAYER_BODY = new AABBf(0.5f, 1.0f, 0.5f).subtract(0.5f, 0.f, 0.5f);


    protected boolean is_on_ground;
    protected boolean is_flying;

    private float vertical_speed_x;
    private float vertical_speed_z;

    private float horizontal_speed;

    private float fallSpeed;

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

        this.NBT_TAG_COMPOUND.set("attribute", new NBTTagCompound());

        this.setFlying(true);
        this.setWalkSpeed(DEFAULT_WALK_SPEED);
        this.setFlightSpeed(DEFAULT_FLIGHT_SPEED);
    }

    /**
    * @return true if it is on the ground, false otherwise.
    */

    public boolean isOnGround() {
        return this.is_on_ground;
    }

    /**
    * @return true if flying, false otherwise.
    */

    public boolean isFlying() {
        return this.is_flying;
    }

    /**
    * ...
    *
    * @param flight ...
    */

    public void setFlying(boolean flight) {
        this.is_flying = flight;
    }

    /**
    * @return the Walk speed.
    */

    public float getWalkSpeed() {
        return this.NBT_TAG_COMPOUND.getFloat("attribute.walk_speed");
    }

    /**
    * Sets the Walk speed.
    *
    * @param walkSpeed Walk Speed to be set.
    */

    public void setWalkSpeed(float walkSpeed) {
        this.NBT_TAG_COMPOUND.setFloat("attribute.walk_speed", walkSpeed);
    }

    /**
    * @return the Flight Speed.
    */

    public float getFlightSpeed() {
        return this.NBT_TAG_COMPOUND.getFloat("attribute.flight_speed");
    }

    /**
    * Sets the Flight Speed.
    *
    * @param flightSpeed Flight Speed to be set.
    */

    public void setFlightSpeed(float flightSpeed) {
        this.NBT_TAG_COMPOUND.setFloat("attribute.flight_speed", flightSpeed);
    }

    @Override
    public void tick() {
        VGShaders.OUTLINE_SHADER_PROGRAM.bind();
        VGShaders.OUTLINE_SHADER_PROGRAM.setUniform4fv("camera_projection", Main.Render.CAMERA.getProjection());
        VGShaders.OUTLINE_SHADER_PROGRAM.setUniform4fv("camera_view", Main.Render.CAMERA.getView());

        tickInput();
        tickMovement();
        tickGround();
        tickGravity();

        Main.OUTLINE_RENDERER.render(DEFAULT_PLAYER_BODY.clone().add(0, 1f, 0), 0, 0, 0, 1f, 1f, 1f, 1f, 1f);

        VGShaders.OUTLINE_SHADER_PROGRAM.unbind();
    }

    private void tickInput() {
        float vertical_speed_x = 0;
        float vertical_speed_z = 0;
        float horizontal_speed = 0;

        if(Input.isKeyDown(GLFW_KEY_W))
            vertical_speed_x += this.getWalkSpeed();
        if(Input.isKeyDown(GLFW_KEY_A))
            vertical_speed_z += this.getWalkSpeed();
        if(Input.isKeyDown(GLFW_KEY_S))
            vertical_speed_x -= this.getWalkSpeed();
        if(Input.isKeyDown(GLFW_KEY_D))
            vertical_speed_z -= this.getWalkSpeed();
        if(Input.isKeyDown(GLFW_KEY_SPACE) && this.isFlying())
            horizontal_speed += this.getFlightSpeed();
        if(Input.isKeyDown(GLFW_KEY_LEFT_SHIFT) && this.isFlying())
            horizontal_speed -= this.getFlightSpeed();
        if(Input.isKeyPressed(GLFW_KEY_F))
            this.setFlying(!this.is_flying);

        this.vertical_speed_x = vertical_speed_x;
        this.vertical_speed_z = vertical_speed_z;
        this.horizontal_speed = horizontal_speed;
    }

    private void tickMovement() {
        float RADIANS = (float) Math.toRadians(this.getLocation().getYaw());

        float DIR_X = (float) Math.sin(-RADIANS);
        float DIR_Z = (float) Math.cos(RADIANS);

        this.getLocation().add(DIR_X * this.vertical_speed_x, 0, DIR_Z * this.vertical_speed_x);
        this.getLocation().add(DIR_Z * this.vertical_speed_z, 0, -DIR_X * this.vertical_speed_z);
        this.getLocation().add(0, this.horizontal_speed, 0);
    }

    private void tickGround() {
        AABBfc Body = DEFAULT_PLAYER_BODY.clone().add(this.getLocation()).subtract(0, 0.0625f, 0);

        int MIN_X = (int) Math.floor(Body.getLowerX() / 0.0625f);
        int MIN_Z = (int) Math.floor(Body.getLowerZ() / 0.0625f);
        int MAX_X = (int) Math.floor(Body.getUpperX() / 0.0625f);
        int MAX_Z = (int) Math.floor(Body.getUpperZ() / 0.0625f);

        for(int x = MIN_X; x <= MAX_X; x++) {
            for(int z = MIN_Z; z <= MAX_Z; z++) {
                Voxel Voxel = Main.CHUNK.getVoxelAt(x, (int) Body.getLowerY(), z);

                Main.OUTLINE_RENDERER.render(Voxel.$(), 0, 0, 0, 1f, 1f, 1f, 1f, 1f);

                if(Voxel.intersects(Body.clone())) {
                    this.is_on_ground = true;

                    return;
                }
            }
        }

        this.is_on_ground = false;
    }

    private void tickGravity() {
        if(this.isOnGround() || this.isFlying()) {
            this.fallSpeed = 0;

            return;
        }

        this.fallSpeed += GRAVITY_FORCE;

        this.getLocation().subtract(0, fallSpeed, 0);
    }

    private void tickJump() {
        /*if(this.is_on_ground) {
            this.horizontal_speed = 0;
        } else {
            this.horizontal_speed += 0.01f;

            AABBfc Body = DEFAULT_PLAYER_BODY.clone().add(this.location);

            int minVoxelX = (int) Math.floor(Body.getLowerX() / 0.0625f);
            int minVoxelZ = (int) Math.floor(Body.getLowerZ() / 0.0625f);
            int maxVoxelX = (int) Math.ceil(Body.getUpperX() / 0.0625f);
            int maxVoxelZ = (int) Math.ceil(Body.getUpperZ() / 0.0625f);

            float newY = (this.location.getY() - this.horizontal_speed);
            float diff = 0;

            for(int x = minVoxelX; x <= maxVoxelX; x++) {
                for(int z = minVoxelZ; z <= maxVoxelZ; z++) {
                    for(int y = this.getLocation().getNearestY(); y > 0; y--) {
                        Voxel Voxel = Main.CHUNK.getVoxelAt(x, y, z);

                        if(Voxel.getColor() != 0 && Voxel.getPosition().getY() < newY) {
                            diff = this.horizontal_speed - Voxel.getPosition().getY();

                            break;
                        }
                    }
                }
            }

            this.location.subtract(0, this.horizontal_speed - diff, 0);
        }*/
    }
}