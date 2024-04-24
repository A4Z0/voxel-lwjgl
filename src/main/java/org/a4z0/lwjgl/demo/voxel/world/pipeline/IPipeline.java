package org.a4z0.lwjgl.demo.voxel.world.pipeline;

/**
* Represents a Pipeline.
*/

public interface IPipeline {

    int ELEMENTS_SIZE = 3 + 4 + 1;
    int ELEMENTS_STRIDE = ELEMENTS_SIZE * Float.BYTES;

    int LAYER_SIZE_X = 16;
    int LAYER_SIZE_Y = 16;
    int LAYER_SIZE_Z = 16;

    int LAYER_SIZE_X_BIT = LAYER_SIZE_X - 1;
    int LAYER_SIZE_Y_BIT = LAYER_SIZE_Y - 1;
    int LAYER_SIZE_Z_BIT = LAYER_SIZE_Z - 1;

    int LAYER_SIZE_X_BIT_SHIFT = (int) (Math.log(LAYER_SIZE_X) / Math.log(2));
    int LAYER_SIZE_Y_BIT_SHIFT = (int) (Math.log(LAYER_SIZE_Y) / Math.log(2));
    int LAYER_SIZE_Z_BIT_SHIFT = (int) (Math.log(LAYER_SIZE_Z) / Math.log(2));

    /**
    * @return true if this {@link IPipeline} is ready for rendering.
    */

    boolean isReadyForRendering();

    /**
    * Updates this {@link IPipeline}.
    */

    void update();

    /**
    * Renders this {@link IPipeline}.
    */

    void render();

    /**
    * Deletes this {@link IPipeline}.
    */

    void delete();
}