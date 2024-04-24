package org.a4z0.lwjgl.demo.voxel.world.chunk.provider;

import org.a4z0.lwjgl.demo.voxel.world.chunk.IChunk;

/**
* Represents a Chunk Provider.
*/

public interface IChunkProvider {

    /**
    * Retrieves a {@link IChunk} at the coordinates.
    *
    * @param x X-Axis.
    * @param z Y-Axis.
    *
    * @return a {@link IChunk}.
    */

    IChunk getChunkAt(int x, int z);

    /**
    * @return ...
    */

    IChunk[] getChunks();
}