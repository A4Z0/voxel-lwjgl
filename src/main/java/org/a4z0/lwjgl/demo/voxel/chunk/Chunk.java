package org.a4z0.lwjgl.demo.voxel.chunk;

import org.a4z0.lwjgl.demo.voxel.block.voxel.VoxelPosition;
import org.a4z0.lwjgl.demo.voxel.chunk.generation.BasicGenerator;
import org.a4z0.lwjgl.demo.voxel.level.Level;
import org.a4z0.lwjgl.demo.voxel.legacy.done.chunk.ChunkProvider;
import org.a4z0.lwjgl.demo.voxel.block.voxel.Voxel;

import java.util.Random;

public class Chunk {

    public static int CHUNK_SIZE_X = 256;
    public static int CHUNK_SIZE_Y = 256;
    public static int CHUNK_SIZE_Z = 256;

    protected final ChunkProvider provider;
    protected final ChunkPosition position;

    protected boolean isLoaded;

    protected final int[] COLOR_ARRAY = new int[CHUNK_SIZE_X * CHUNK_SIZE_Y * CHUNK_SIZE_Z];

    protected final BasicGenerator gen;

    /**
    * Construct a {@link Chunk}.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    */

    public Chunk(ChunkProvider p, int x, int y, int z) {
        this.provider = p;
        this.position = new ChunkPosition(x, y, z);

        this.gen = null;//new BasicGenerator(this);

        Random R = new Random();

        for(int vx = 0; vx < 256; vx++) {
            for(int vy = 0; vy < 16; vy++) {
                for(int vz = 0; vz < 256; vz++) {
                    this.getVoxelAt(vx, vy, vz).setColor(R.nextInt(255), R.nextInt(255), R.nextInt(255));
                }
            }
        }
    }

    /**
    * @return the {@link Level} this {@link Chunk} is in.
    */

    public Level getLevel() {
        return this.provider.getLevel();
    }

    /**
    * @return the X-Axis.
    */

    public int getX() {
        return this.position.getX();
    }

    /**
    * @return the Y-Axis.
    */

    public int getY() {
        return this.position.getY();
    }

    /**
    * @return the Z-Axis.
    */

    public int getZ() {
        return this.position.getZ();
    }

    /**
    * Retrieves a {@link Voxel} at the coordinates.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return a {@link Voxel}.
    */

    public Voxel getVoxelAt(float x, float y, float z) {
        return this.getVoxelAt((int) (x / 0.0625f), (int) (y / 0.0625f), (int) (z / 0.0625f));
    }

    /**
    * Retrieves a {@link Voxel} at the coordinates.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return a {@link Voxel}.
    */

    public Voxel getVoxelAt(int x, int y, int z) {
        return new Voxel() {

            @Override
            public VoxelPosition getPosition() {
                return new VoxelPosition(x, y, z);
            }

            @Override
            public int getColor() {
                if((x < 0 || x >= CHUNK_SIZE_X) || (y < 0 || y >= CHUNK_SIZE_Y) || (z < 0 || z >= CHUNK_SIZE_Z))
                    return 0;

                return COLOR_ARRAY[this.getPosition().getIndex()];
            }

            @Override
            public void setColor(byte r, byte g, byte b, byte a) {
                if((x < 0 || x >= CHUNK_SIZE_X) || (y < 0 || y >= CHUNK_SIZE_Y) || (z < 0 || z >= CHUNK_SIZE_Z))
                    return;

                COLOR_ARRAY[this.getPosition().getIndex()] = (r & 0xFF) | ((g & 0xFF) << 8) | ((b & 0xFF) << 16) | ((a & 0xFF) << 24);
            }

            @Override
            public Voxel getNorth() {
                return getVoxelAt(x, y, z - 1);
            }

            @Override
            public Voxel getSouth() {
                return getVoxelAt(x, y, z + 1);
            }

            @Override
            public Voxel getEast() {
                return getVoxelAt(x + 1, y, z);
            }

            @Override
            public Voxel getWest() {
                return getVoxelAt(x - 1, y, z);
            }

            @Override
            public Voxel getTop() {
                return getVoxelAt(x, y + 1, z);
            }

            @Override
            public Voxel getBottom() {
                return getVoxelAt(x, y - 1, z);
            }
        };
    }

    /**
    * Loads this {@link Chunk}.
    *
    * @return true if it loads correctly, false otherwise.
    */

    public boolean load() {
        return this.load(false);
    }

    /**
    * Loads this {@link Chunk}.
    *
    * @param g Generates this Chunk when true.
    *
    * @return true if it loads correctly, false otherwise.
    */

    public boolean load(boolean g) {
        if(this.isLoaded)
            return false;

        if(g)
            this.gen.generate();

        this.provider.put(this);

        System.out.println("[Chunk]: Loaded!");

        return true;
    }

    public boolean isLoaded() {
        return this.isLoaded;
    }

    /**
    * Unloads this {@link Chunk}.
    *
    * @return true if it unloads correctly, false otherwise.
    */

    public boolean unload() {
        return this.unload(true);
    }

    /**
    * Unloads this {@link Chunk}.
    *
    * @param l Unloads slowly when true.
    *
    * @return true if it unloads correctly, false otherwise.
    */

    public boolean unload(boolean l) {
        if(!this.isLoaded)
            return false;

        System.out.println("[Chunk]: Unloaded!");

        return true;
    }
}