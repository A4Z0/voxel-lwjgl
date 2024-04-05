package org.a4z0.lwjgl.demo.voxel.world;

import org.a4z0.lwjgl.demo.voxel.map.ChunkCoordinate;
import org.a4z0.lwjgl.demo.voxel.world.chunk.Chunk;
import org.a4z0.lwjgl.demo.voxel.world.chunk.LoadedChunk;
import org.a4z0.lwjgl.demo.voxel.world.chunk.UnloadedChunk;

import java.util.HashMap;
import java.util.Optional;

/**
* Represents an Overworld.
*/

public class Overworld implements World {

    protected final long seed;
    protected final HashMap<Long, Chunk> chunks = new HashMap<>();

    /**
    * Construct a {@link Overworld}.
    *
    * @param seed Seed that will be applied to the generation of this {@link Overworld}.
    */

    public Overworld(long seed) {
        this.seed = seed;

        for(int x = -CHUNKS_PER_DIRECTION; x <= CHUNKS_PER_DIRECTION; x++) {
            for(int z = -CHUNKS_PER_DIRECTION; z <= CHUNKS_PER_DIRECTION; z++) {
                Chunk chunk = new LoadedChunk(this, x * Chunk.CHUNK_SIZE_X, z * Chunk.CHUNK_SIZE_Z);

                this.chunks.put(ChunkCoordinate.asLongFromBlock(chunk.getX(), 0, chunk.getZ()), chunk);
            }
        }
    }

    @Override
    public long getSeed() {
        return this.seed;
    }

    @Override
    public Chunk getChunkAt(int x, int z) {
        return Optional.ofNullable(this.chunks.get(ChunkCoordinate.asLongFromBlock(x, 0, z))).orElse(new UnloadedChunk(this, x, 0, z));
    }

    @Override
    public Chunk[] getChunks() {
        return this.chunks.values().toArray(new Chunk[0]);
    }

    @Override
    public void setVoxel(int x, int y, int z, int r, int g, int b, int a, boolean u) {
        this.getChunkAt(x, z).setVoxel(x, y, z, r, g, b, a, u);
    }

    @Override
    public int getVoxel(int x, int y, int z) {
        return this.getChunkAt(x, z).getVoxel(x, y, z);
    }
}