package org.a4z0.lwjgl.demo.voxel.world.chunk;

import org.a4z0.lwjgl.demo.voxel.voxel.Palette;
import org.a4z0.lwjgl.demo.voxel.world.World;
import org.a4z0.lwjgl.demo.voxel.world.chunk.layer.Layer;

/**
* Represents a Chunk.
*/

public interface Chunk extends Palette {

    int CHUNK_SIZE_X = 16;
    int CHUNK_SIZE_Y = 128;
    int CHUNK_SIZE_Z = 16;

    int CHUNK_SIZE_X_SQRT = (int) Math.sqrt(CHUNK_SIZE_X);
    int CHUNK_SIZE_Y_SQRT = (int) Math.sqrt(CHUNK_SIZE_Y);
    int CHUNK_SIZE_Z_SQRT = (int) Math.sqrt(CHUNK_SIZE_Z);

    /**
    * @return the X-Axis of this {@link Chunk}.
    */

    int getX();

    /**
    * @return the Z-Axis of this {@link Chunk}.
    */

    int getZ();

    /**
    * @return the {@link World} this {@link Chunk} is in.
    */

    World getWorld();

    /**
    * @return the {@link Layer Layers} of this {@link Chunk}.
    */

    Layer[] getLayers();
}