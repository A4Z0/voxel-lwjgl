package org.a4z0.lwjgl.demo.voxel.world;

import org.a4z0.lwjgl.demo.voxel.position.ChunkPosition;
import org.a4z0.lwjgl.demo.voxel.voxel.Voxel;
import org.a4z0.lwjgl.demo.voxel.world.chunk.IChunk;
import org.a4z0.lwjgl.demo.voxel.world.chunk.EmptyChunk;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
* Represents an Overworld.
*/

public class Overworld implements World {

    protected final long seed;
    protected final Map<Long, IChunk> chunks = new HashMap<>();

    /**
    * Construct a {@link Overworld}.
    *
    * @param seed Seed that will be applied to the generation of this {@link Overworld}.
    */

    public Overworld(long seed) {
        this.seed = seed;

        /*new Thread(() -> {

            AtomicInteger lastX = new AtomicInteger(0);
            AtomicInteger lastZ = new AtomicInteger(0);

           while(true) {
                Location Pos = VoxelGameLWJGL.CAMERA.getPosition();

                if(lastX.get() != Pos.getBlockX() || lastZ.get() != Pos.getBlockZ()) {
                    for (int x = -8; x <= 8; x++) {
                        for (int z = -8; z <= 8; z++) {
                            int nX = (x * 16) + Pos.getBlockX();
                            int nZ = (z * 16) + Pos.getBlockZ();

                            Chunk chunk = this.getChunkAt(nX, nZ);

                            synchronized (this.chunks) {
                                if (chunk instanceof UnloadedChunk)
                                    this.chunks.put(ChunkCoordinate.asLongFromBlock(nX, 0, nZ), new LoadedChunk(this, nX, nZ));
                            }
                        }
                    }
                }

                lastX.set(Pos.getBlockX());
                lastZ.set(Pos.getBlockZ());
           }
        }).start();*/

        /*for(int x = -CHUNKS_PER_DIRECTION; x <= CHUNKS_PER_DIRECTION; x++) {
            for(int z = -CHUNKS_PER_DIRECTION; z <= CHUNKS_PER_DIRECTION; z++) {
                Chunk chunk = new WorldChunk(this, x * Chunk.CHUNK_SIZE_X, z * Chunk.CHUNK_SIZE_Z, null);

                this.chunks.put(ChunkPosition.asBlock(chunk.getX(), chunk.getZ()), chunk);
            }
        }*/
    }

    @Override
    public long getSeed() {
        return this.seed;
    }

    @Override
    public IChunk getChunkAt(int x, int z) {
        return Optional.ofNullable(this.chunks.get(ChunkPosition.asBlock(x, z))).orElse(new EmptyChunk(this, x, z));
    }

    @Override
    public IChunk[] getChunks() {
        synchronized (this.chunks) {
            return this.chunks.values().toArray(new IChunk[0]);
        }
    }

    @Override
    public void setVoxel(int x, int y, int z, Voxel voxel) {
        this.getChunkAt(x, z).setVoxel(x, y, z, voxel);
    }

    @Override
    public Voxel getVoxel(int x, int y, int z) {
        return this.getChunkAt(x, z).getVoxel(x, y, z);
    }
}