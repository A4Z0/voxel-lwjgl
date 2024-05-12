package org.a4z0.lwjgl.demo.voxel.level.chunk;

import org.a4z0.lwjgl.demo.voxel.palette.Palette;
import org.a4z0.lwjgl.demo.voxel.math.ChunkPosition;
import org.a4z0.lwjgl.demo.voxel.level.Level;
import org.a4z0.lwjgl.demo.voxel.block.BlockState;

/**
* Represents an Internal Chunk.
*/

public class InternalChunk implements Chunk {

    protected final ChunkProvider provider;

    protected final Level level;
    protected final ChunkPosition position;

    protected final Palette<BlockState> palette = new Palette<>(16, 128, 16);

    /**
    * Construct a {@link InternalChunk}.
    *
    * @param provider ...
    * @param level ...
    * @param x X-Axis.
    * @param z Z-Axis.
    */

    public InternalChunk(ChunkProvider provider, Level level, int x, int z) {
        this(provider, level, new ChunkPosition(x, z));
    }

    /**
    * Construct a {@link InternalChunk}.
    *
    * @param provider ...
    * @param level ...
    * @param position ...
    */

    protected InternalChunk(ChunkProvider provider, Level level, ChunkPosition position) {
        this.provider = provider;
        this.level = level;
        this.position = position;

        for(int x = 0; x < 16; x++) {
            for(int y = 0; y < 128; y++) {
                for(int z = 0; z < 16; z++) {
                    this.palette.set(x, y, z, new BlockState(x, y, z, 0));
                }
            }
        }
    }

    @Override
    public Level getLevel() {
        return this.level;
    }

    @Override
    public int getX() {
        return this.position.getX();
    }

    @Override
    public int getZ() {
        return this.position.getZ();
    }

    @Override
    public BlockState getBlockAt(int x, int y, int z) {
       return this.palette.get(x, y, z);
    }

    @Override
    public boolean load(boolean g) {
        return true;
    }

    @Override
    public boolean unload(boolean l) {
        return true;
    }
}