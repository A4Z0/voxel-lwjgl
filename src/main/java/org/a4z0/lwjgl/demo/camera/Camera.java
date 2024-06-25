package org.a4z0.lwjgl.demo.camera;

import org.a4z0.lwjgl.demo.math.Vector3f;
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
    * Sets the Yaw.
    *
    * @param yaw Yaw.
    */

    void setYaw(float yaw);

    /**
    * @return the Pitch.
    */

    float getPitch();

    /**
    * Sets the Pitch.
    *
    * @param pitch Pitch.
    */

    void setPitch(float pitch);

    /**
    * @return the Position.
    */

    Vector3f getPosition();

    /**
    * @return the Direction.
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