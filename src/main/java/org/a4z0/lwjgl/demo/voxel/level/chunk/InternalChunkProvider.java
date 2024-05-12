package org.a4z0.lwjgl.demo.voxel.level.chunk;

import org.a4z0.lwjgl.demo.voxel.math.ChunkPosition;

import java.util.HashMap;
import java.util.Map;

/**
* ...
*/

public class InternalChunkProvider implements ChunkProvider {

    protected final Map<Long, Chunk> CHUNK_MAP = new HashMap<>();

    /**
    * Construct a {@link InternalChunkProvider}.
    */

    public InternalChunkProvider() {

    }

    @Override
    public Chunk getChunkAt(int x, int z) {
        return this.getChunkAt(ChunkPosition.getIndexFromBlock(x, z));
    }

    @Override
    public Chunk getChunkAt(long i) {
        return this.CHUNK_MAP.get(i);
    }

    @Override
    public Chunk setChunkAt(int x, int z, Chunk chunk) {
        return this.setChunkAt(ChunkPosition.getIndexFromBlock(x, z), chunk);
    }

    @Override
    public Chunk setChunkAt(long i, Chunk chunk) {
        this.CHUNK_MAP.put(i, chunk);

        return chunk;
    }

    @Override
    public Chunk delChunkAt(int x, int z) {
        return this.delChunkAt(ChunkPosition.getIndexFromBlock(x, z));
    }

    @Override
    public Chunk delChunkAt(long i) {
        return this.CHUNK_MAP.remove(i);
    }

    @Override
    public boolean exists(int x, int z) {
        return this.exists(ChunkPosition.getIndexFromBlock(x, z));
    }

    @Override
    public boolean exists(long i) {
        return this.CHUNK_MAP.get(i) != null;
    }
}