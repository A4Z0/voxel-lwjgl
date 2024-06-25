package org.a4z0.lwjgl.demo.camera;

import org.a4z0.lwjgl.demo.Main;
import org.a4z0.lwjgl.demo.math.Vector3f;
import org.joml.Math;
import org.joml.Matrix4f;

public class PerspectiveCamera implements Camera {

    protected final Vector3f position;

    protected float yaw;
    protected float pitch;

    /**
    * Construct a {@link PerspectiveCamera}.
    */

    public PerspectiveCamera() {
        this(0f, 0f, 0f, DEFAULT_YAW, DEFAULT_PITCH);
    }

    /**
    * Construct a {@link PerspectiveCamera}.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    * @param yaw Yaw.
    * @param pitch Pitch.
    */

    public PerspectiveCamera(float x, float y, float z, float yaw, float pitch) {
        this(new Vector3f(x, y, z), yaw, pitch);
    }

    /**
    * Construct a {@link PerspectiveCamera}.
    *
    * @param position Position {@link Vector3f}.
    */

    public PerspectiveCamera(Vector3f position, float yaw, float pitch) {
        this.position = position;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    @Override
    public float getYaw() {
        return this.yaw;
    }

    @Override
    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    @Override
    public float getPitch() {
        return this.pitch;
    }

    @Override
    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    @Override
    public Vector3f getPosition() {
        return this.position;
    }

    @Override
    public Vector3f getDirection() {
        float XZ = Math.cos(Math.toRadians(this.pitch));

        return new Vector3f(
            (-XZ * Math.sin(Math.toRadians(this.yaw))),
            -Math.sin(Math.toRadians(this.pitch)),
            (XZ * Math.cos(Math.toRadians(this.yaw)))
        );
    }

    @Override
    public Matrix4f getProjection() {
        return new Matrix4f().identity().perspective(
            Math.toRadians(DEFAULT_FOV),
            ((float) Main.GL_WINDOW_WIDTH / (float) Main.GL_WINDOW_HEIGHT),
            DEFAULT_NEAR_PLANE,
            DEFAULT_FAR_PLANE
        );
    }

    @Override
    public Matrix4f getView() {
        Vector3f dir = this.getDirection();

        return new Matrix4f().identity().lookAt(
            this.position.getX(), this.position.getY(), this.position.getZ(),
            (this.position.getX() + dir.getX()),
            (this.position.getY() + dir.getY()),
            (this.position.getZ() + dir.getZ()),
            0, 1, 0
        );
    }
}