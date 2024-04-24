package org.a4z0.lwjgl.demo.voxel.world.chunk;

import org.a4z0.lwjgl.demo.voxel.position.ChunkPosition;
import org.a4z0.lwjgl.demo.voxel.world.World;
import org.a4z0.lwjgl.demo.voxel.voxel.Voxel;

/**
* Represents an Empty Chunk.
* Contains only its coordinates and the {@link World} where it resides.
*
* <br>
*
* It should only be used when outside the {@link World}'s boundaries.
*/

public class EmptyChunk implements IChunk {

    protected final World world;
    protected final ChunkPosition position;

    /**
    * Construct a {@link EmptyChunk}.
    *
    * @param world {@link World} that this {@link EmptyChunk} is.
    * @param x X-Axis.
    * @param z Z-Axis.
    */

    public EmptyChunk(World world, int x, int z) {
        this(world, new ChunkPosition(x, z));
    }

    /**
    * Construct a {@link EmptyChunk}.
    *
    * @param world {@link World} that this {@link EmptyChunk} is.
    * @param position {@link ChunkPosition} that this {@link EmptyChunk} is.
    */

    public EmptyChunk(World world, ChunkPosition position) {
        this.world = world;
        this.position = position;
    }

    @Override
    public World getWorld() {
        return this.world;
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
    public void setVoxel(int x, int y, int z, Voxel voxel) {

    }

    @Override
    public Voxel getVoxel(int x, int y, int z) {
        return Voxel.EMPTY_VOXEL;
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