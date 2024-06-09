package org.a4z0.lwjgl.demo.voxel.camera;

import org.a4z0.lwjgl.demo.voxel.math.Vector3f;
import org.joml.Matrix4f;

public interface Camera {

    float DEFAULT_FAR_PLANE = 1000f;
    float DEFAULT_NEAR_PLANE = 0.001f;

    float DEFAULT_FOV = 90f;

    float DEFAULT_PITCH = 0f;
    float DEFAULT_YAW = -90f;

    /**
    * @return the Yaw.
    */

    float getYaw();

    /**
    * ...
    *
    * @param yaw ...
    */

    void setYaw(float yaw);

    /**
    * @return the Pitch.
    */

    float getPitch();

    /**
    * ...
    *
    * @param pitch ...
    */

    void setPitch(float pitch);

    /**
    * @return ...
    */

    Vector3f getPosition();

    /**
    * @return ...
    */

    Vector3f getDirection();

    /**
    * @return the Projection.
    */

    Matrix4f getProjection();

    /**
    * @return the View.
    */

    Matrix4f getView();
}