package org.a4z0.lwjgl.demo.voxel.world.chunk;

import org.a4z0.lwjgl.demo.voxel.palette.Palette;
import org.a4z0.lwjgl.demo.voxel.position.ChunkPosition;
import org.a4z0.lwjgl.demo.voxel.world.World;
import org.a4z0.lwjgl.demo.voxel.voxel.Voxel;

/**
* Represents a Chunk.
* Section of the {@link World} that can be loaded or unloaded.
*/

public class Chunk implements IChunk {

    protected final World world;
    protected final ChunkPosition position;
    protected final Palette<Voxel> palette;

    /**
    * Construct a {@link Chunk}.
    *
    * @param world {@link World} that this {@link Chunk} is.
    * @param x X-Axis.
    * @param z Z-Axis.
    * @param palette ...
    */

    public Chunk(World world, int x, int z, Palette<Voxel> palette) {
        this(world, new ChunkPosition(x, z), palette);
    }

    /**
    * Construct a {@link Chunk}.
    *
    * @param world {@link World} that this {@link Chunk} is.
    * @param position {@link ChunkPosition} that this {@link Chunk} is.
    * @param palette ...
    */

    public Chunk(World world, ChunkPosition position, Palette<Voxel> palette) {
        this.world = world;
        this.position = position;
        this.palette = new Palette<>(CHUNK_SIZE_X, CHUNK_SIZE_Y, CHUNK_SIZE_Z);
        this.palette.fill(Voxel.EMPTY_VOXEL);
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
        this.palette.set(x, y, z, voxel);
    }

    @Override
    public Voxel getVoxel(int x, int y, int z) {
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