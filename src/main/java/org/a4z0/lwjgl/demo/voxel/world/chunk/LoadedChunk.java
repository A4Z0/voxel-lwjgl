package org.a4z0.lwjgl.demo.voxel.world.chunk;

import org.a4z0.lwjgl.demo.voxel.world.chunk.layer.Layers;
import org.a4z0.lwjgl.demo.voxel.world.World;
import org.a4z0.lwjgl.demo.voxel.world.chunk.layer.Layer;

/**
* Represents a loaded {@link Chunk}.
*/

public class LoadedChunk implements Chunk {

    protected final World world;

    protected final int x;
    protected final int z;

    protected final int[] Blocks = new int[CHUNK_SIZE_X * CHUNK_SIZE_Y * CHUNK_SIZE_Z];

    protected final org.a4z0.lwjgl.demo.voxel.world.chunk.layer.Layers Layers;

    /**
    * Construct a {@link LoadedChunk}.
    *
    * @param world {@link World} that this {@link LoadedChunk} is.
    * @param x X-Axis.
    * @param z Z-Axis.
    */

    public LoadedChunk(World world, int x, int z) {
        this.world = world;
        this.x = (x >> CHUNK_SIZE_X_SQRT) * CHUNK_SIZE_X;
        this.z = (z >> CHUNK_SIZE_Z_SQRT) * CHUNK_SIZE_Z;
        this.Layers = new Layers(this);
    }

    @Override
    public World getWorld() {
        return this.world;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getZ() {
        return this.z;
    }

    @Override
    public Layer[] getLayers() {
        return this.Layers.getLayers();
    }

    @Override
    public void setVoxel(int x, int y, int z, int r, int g, int b, int a, boolean u) {
        int Index = (y << CHUNK_SIZE_X_SQRT | (z - this.getZ())) << CHUNK_SIZE_Z_SQRT | (x - this.getX());

        if(Index < 0 || Index >= this.Blocks.length)
            return;

        this.Blocks[Index] = ((a & 0xFF) << 24) | ((r & 0xFF) << 16) | ((g & 0xFF) << 8) | (b & 0xFF);

        if(u) {
            Layer layer = this.Layers.getLayerAt(x, y, z);

            if (layer != null)
                layer.delete(true);
        }
    }

    @Override
    public int getVoxel(int x, int y, int z) {
        int Index = (y << CHUNK_SIZE_X_SQRT | (z - this.getZ())) << CHUNK_SIZE_Z_SQRT | (x - this.getX());

        if(Index < 0 || Index >= this.Blocks.length)
            return 0;

        return this.Blocks[Index];
    }
}