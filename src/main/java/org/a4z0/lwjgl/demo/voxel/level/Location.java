package org.a4z0.lwjgl.demo.voxel.level;

import org.a4z0.lwjgl.demo.voxel.math.Vector3f;
import org.a4z0.lwjgl.demo.voxel.math.Vector3fc;

public class Location implements Vector3fc {

    protected static final double $2PI = 2 * Math.PI;

    protected Level level;
    protected float x, y, z;
    protected float pitch;
    protected float yaw;

    /**
    * Construct a {@link Location}.
    */

    public Location(final Level level) {
        this(level, 0, 0, 0);
    }

    /**
    * ...
    *
    * @param level ...
    * @param x ...
    * @param y ...
    * @param z ...
    */

    public Location(final Level level, final float x, final float y, final float z) {
        this(level, x, y, z, -90, 0);
    }

    /**
    * Construct a {@link Location}.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    * @param yaw Yaw.
    * @param pitch Pitch.
    */

    public Location(final Level level, final float x, final float y, final float z, final float yaw, final float pitch) {
        this.level = level;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public Level getLevel() {
        return this.level;
    }

    /**
    * @return the X-Axis.
    */

    public float getX() {
        return this.x;
    }

    /**
    * @return the Y-Axis.
    */

    public float getY() {
        return this.y;
    }

    /**
    * @return the Z-Axis.
    */

    public float getZ() {
        return this.z;
    }

    @Override
    public Vector3fc set(Vector3fc o) {
        return null;
    }

    /**
    * @return the X-Axis of the Block this {@link Location} is in.
    */

    @Deprecated
    public int getNearestX() {
        return (int) Math.floor(this.x);
    }

    /**
    * @return the Y-Axis of the Block this {@link Location} is in.
    */

    @Deprecated
    public int getNearestY() {
        return (int) Math.floor(this.y);
    }

    /**
    * @return the Z-Axis of the Block this {@link Location} is in.
    */

    @Deprecated
    public int getNearestZ() {
        return (int) Math.floor(this.z);
    }

    /**
    * @return the Yaw.
    */

    public float getYaw() {
        return this.yaw;
    }

    /**
    * @return the Pitch.
    */

    public float getPitch() {
        return this.pitch;
    }

    /**
    * ...
    *
    * @param yaw ...
    */

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    /**
    * ...
    *
    * @param pitch ...
    */

    public void setPitch(float pitch) {
        this.pitch = (float) Math.min(89.9d, Math.max(-89.9d, pitch));
    }

    /**
    * @return ...
    */

    public Vector3f getDirection() {
        float XZ = (float) Math.cos(Math.toRadians(this.getPitch()));

        return new Vector3f(
            (float) (-XZ * Math.sin(Math.toRadians(this.yaw))),
            (float) -Math.sin(Math.toRadians(this.pitch)),
            (float) (XZ * Math.cos(Math.toRadians(this.yaw)))
        );
    }

    /**
    * ...
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return this {@link Location}.
    */

    public Location setDirection(float x, float y, float z) {
        if(x == 0 && z == 0) {
            this.setPitch(y > 0 ? -90 : 90);

            return this;
        }

        this.yaw = (float) Math.toDegrees(((Math.atan2(-x, z)) + $2PI) % $2PI);
        this.pitch = (float) Math.toDegrees(Math.atan(-y / (Math.sqrt((Math.pow(x, 2)) + (Math.pow(z, 2))))));

        return this;
    }

    @Override
    public Location set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;

        return this;
    }

    @Override
    public Vector3fc add(Vector3fc o) {
        return null;
    }

    @Override
    public Location add(float x, float y, float z) {
        this.x += x;
        this.y += y;
        this.z += z;

        return this;
    }

    @Override
    public Vector3fc subtract(Vector3fc o) {
        return null;
    }

    @Override
    public Location subtract(float x, float y, float z) {
        this.x -= x;
        this.y -= y;
        this.z -= z;

        return this;
    }

    @Override
    public Vector3fc multiply(float multiplier) {
        return null;
    }

    @Override
    public Vector3fc multiply(Vector3fc o) {
        return null;
    }

    @Override
    public Location multiply(float x, float y, float z) {
        this.x *= x;
        this.y *= y;
        this.z *= z;

        return this;
    }

    @Override
    public Vector3fc divide(float divisor) {
        return null;
    }

    @Override
    public Vector3fc divide(Vector3fc o) {
        return null;
    }

    @Override
    public Vector3fc divide(float x, float y, float z) {
        return null;
    }

    @Override
    public float dot(float x, float y, float z) {
        return this.x * x + this.y * y + this.z * z;
    }

    @Override
    public Location normalize() {
        float magnitude = (float) Math.sqrt((Math.pow(2, this.x)) + (Math.pow(2, this.y)) + (Math.pow(2, this.z)));

        if(magnitude != 0) {
            this.x /= magnitude;
            this.y /= magnitude;
            this.z /= magnitude;
        }

        return this;
    }

    @Override
    public Vector3fc zero() {
        return null;
    }

    @Override
    public float distance(float x, float y, float z) {
        return (float) Math.pow((this.x - x), 2) + (float) Math.pow((this.y - y), 2) + (float) Math.pow((this.z - z), 2);
    }

    @Override
    public Location clone() {
        try {
            return (Location) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}