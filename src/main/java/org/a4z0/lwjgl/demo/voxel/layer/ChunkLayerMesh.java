package org.a4z0.lwjgl.demo.voxel.layer;

import org.a4z0.lwjgl.demo.voxel.chunk.Chunk;
import org.a4z0.lwjgl.demo.voxel.legacy.done.buffer.DynamicBuffer;
import org.a4z0.lwjgl.demo.voxel.level.Direction;

@Deprecated
public final class ChunkLayerMesh {

    public static DynamicBuffer create(ChunkLayer layer) {
        DynamicBuffer stream = new DynamicBuffer();

        /*for(int x = 0; x < 256; x++) {
            for(int y = 0; y < 256; y++) {
                for(int z = 0; z < 256; z++) {
                    int color = layer.getChunk().getVoxelAt(x, y, z);

                    if(color == 0)
                        continue;

                    for(Direction dir : Direction.values()) {
                        if(_a(layer.getChunk(), dir, x, y, z)) {
                            FaceGenerator.generate(stream, dir, x, y, z, 1f/*0.03125f, color);
                        }
                    }
                }
            }
        }*/

        return stream;
    }

    static boolean _a(Chunk p, Direction d, int x, int y, int z) {
        /*return switch (d) {
            case NORTH ->
                _n(p, x, y, z);
            case SOUTH ->
                _s(p, x, y, z);
            case EAST ->
                _e(p, x, y, z);
            case WEST ->
                _w(p, x, y, z);
            case TOP ->
                _t(p, x, y, z);
            case BOTTOM ->
                _b(p, x, y, z);
        };*/
        return false;
    }

    /*static boolean _n(Chunk p, int vx, int vy, int vz) {
        return p.getVoxelAt(vx, vy, vz -1) == 0;
    }

    static boolean _s(Chunk p, int vx, int vy, int vz) {
        return p.getVoxelAt(vx, vy, vz + 1) == 0;
    }

    static boolean _e(Chunk p, int vx, int vy, int vz) {
        return p.getVoxelAt(vx + 1, vy, vz) == 0;
    }

    static boolean _w(Chunk p, int vx, int vy, int vz) {
        return p.getVoxelAt(vx - 1, vy, vz) == 0;
    }

    static boolean _t(Chunk p, int vx, int vy, int vz) {
        return p.getVoxelAt(vx, vy + 1, vz) == 0;
    }

    static boolean _b(Chunk p, int vx, int vy, int vz) {
        return p.getVoxelAt(vx, vy - 1, vz) == 0;
    }*/
}