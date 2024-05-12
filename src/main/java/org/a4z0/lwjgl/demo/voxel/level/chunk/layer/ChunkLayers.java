package org.a4z0.lwjgl.demo.voxel.level.chunk.layer;

import org.a4z0.lwjgl.demo.voxel.level.chunk.Chunk;

/**
* ...
*/

public class ChunkLayers {

    protected final ChunkLayer[] Layers = new ChunkLayer[Chunk.CHUNK_SIZE_Y];

    /**
    * Construct a {@link ChunkLayers}.
    *
    * @param Chunk ...
    */

    public ChunkLayers(Chunk Chunk) {
        for(int Y = 0; Y < this.Layers.length; Y++)
            this.Layers[Y] = new ChunkLayer(Chunk, Y);
    }

    /**
    * @return ...
    */

    public ChunkLayer[] getLayers() {
        return this.Layers;
    }
}