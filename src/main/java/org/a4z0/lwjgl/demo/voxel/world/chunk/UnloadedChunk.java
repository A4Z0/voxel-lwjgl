package org.a4z0.lwjgl.demo.voxel.world.chunk;

import org.a4z0.lwjgl.demo.voxel.world.World;
import org.a4z0.lwjgl.demo.voxel.world.chunk.layer.Layer;

/**
* Represents an unloaded {@link Chunk}.
*/

public class UnloadedChunk implements Chunk {

    protected final World world;

    protected final int x;
    protected final int z;

    /**
    * Construct a {@link UnloadedChunk}.
    *
    * @param world {@link World} that this {@link UnloadedChunk} is.
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    */

    public UnloadedChunk(World world, int x, int y, int z) {
        this.world = world;
        this.x = (x >> CHUNK_SIZE_X_SQRT) * CHUNK_SIZE_X;
        this.z = (z >> CHUNK_SIZE_Z_SQRT) * CHUNK_SIZE_Z;
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
        return new Layer[0]; // We don't need to simulate Layers.
    }

    @Override
    public void setVoxel(int x, int y, int z, int r, int g, int b, int a, boolean u) {
        // We don't set any Voxel.
    }

    @Override
    public int getVoxel(int x, int y, int z) {
        return 0; // Always return 0
    }
}