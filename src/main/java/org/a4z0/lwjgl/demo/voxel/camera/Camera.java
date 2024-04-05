package org.a4z0.lwjgl.demo.voxel.camera;

import org.a4z0.lwjgl.demo.voxel.position.Location;
import org.joml.Matrix4f;

/**
* Represents a Camera.
*/

public interface Camera {

    float DEFAULT_FAR_PLANE = 1000f;
    float DEFAULT_NEAR_PLANE = 0.001f;

    float DEFAULT_FOV = 90f;

    float DEFAULT_PITCH = 0f;
    float DEFAULT_YAW = -90f;

    /**
    * @return the {@link Camera} {@link Location}.
    */

    Location getPosition();

    /**
    * @return the {@link Camera} Projection {@link Matrix4f Matrix}.
    */

    Matrix4f getProjection();

    /**
    * @return the {@link Camera} View {@link Matrix4f Matrix}.
    */

    Matrix4f getView();
}