package org.a4z0.lwjgl.demo.voxel.layer;

import org.a4z0.lwjgl.demo.voxel.chunk.Chunk;

/**
* ...
*/

public class ChunkLayers {

    protected final ChunkLayer[] Layers = new ChunkLayer[Chunk.CHUNK_SIZE_Y];
    protected Chunk chunk;

    /**
    * Construct a {@link ChunkLayers}.
    *
    * @param Chunk ...
    */

    public ChunkLayers(Chunk Chunk) {
        this.chunk = Chunk;

        for(int Y = 0; Y < this.Layers.length; Y++)
            this.Layers[Y] = new ChunkLayer(Chunk, Y);
    }

    public Chunk getChunk() {
        return chunk;
    }

    public void setChunk(Chunk Chunk) {
        this.chunk = Chunk;

        for(ChunkLayer layer : this.Layers) {
            layer.setChunk(Chunk);
        }
    }

    public void delete() {
        for (ChunkLayer layer : this.Layers) {
            layer.delete(true);
        }
    }

    /**
    * @return ...
    */

    public ChunkLayer[] getLayers() {
        return this.Layers;
    }
}