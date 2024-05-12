package org.a4z0.lwjgl.demo.voxel.level.chunk;

import org.a4z0.lwjgl.demo.voxel.block.BlockState;
import org.a4z0.lwjgl.demo.voxel.math.ChunkPosition;
import org.a4z0.lwjgl.demo.voxel.level.Level;

/**
* Represents an Empty Chunk.
* Contains only its coordinates and the {@link Level} where it resides.
*
* <br>
*
* It should only be used when outside the {@link Level}'s boundaries.
*/

public class EmptyChunk implements Chunk {

    protected final Level level;
    protected final ChunkPosition position;

    /**
    * Construct a {@link EmptyChunk}.
    *
    * @param level {@link Level} that this {@link EmptyChunk} is.
    * @param x X-Axis.
    * @param z Z-Axis.
    */

    public EmptyChunk(Level level, int x, int z) {
        this(level, new ChunkPosition(x, z));
    }

    /**
    * Construct a {@link EmptyChunk}.
    *
    * @param level {@link Level} that this {@link EmptyChunk} is.
    * @param position {@link ChunkPosition Position} that this {@link EmptyChunk} is.
    */

    public EmptyChunk(Level level, ChunkPosition position) {
        this.level = level;
        this.position = position;
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
        return new BlockState(x, y, z, 0);
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