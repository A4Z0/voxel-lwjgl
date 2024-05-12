package org.a4z0.lwjgl.demo.voxel.level.chunk.layer;

import org.a4z0.lwjgl.demo.voxel.block.BlockState;
import org.a4z0.lwjgl.demo.voxel.level.chunk.Chunk;
import org.a4z0.lwjgl.demo.voxel.gl.vertex.VertexStream;
import org.a4z0.lwjgl.demo.voxel.level.Level;
import org.a4z0.lwjgl.demo.voxel.math.Direction;

@Deprecated
public final class ChunkLayerMesh {

    public static VertexStream create(ChunkLayer layer) {
        VertexStream stream = new VertexStream();

        for(int x = layer.getChunk().getX(); x <= (layer.getChunk().getX() + Chunk.CHUNK_SIZE_X); x++)
            for(int z = layer.getChunk().getZ(); z <= (layer.getChunk().getZ() + Chunk.CHUNK_SIZE_Z); z++)
                _v(layer.getChunk().getLevel(), stream, x, layer.getY(), z);

        return stream;
    }

    static void _v(Level level, VertexStream stream, int x, int y, int z) {
        BlockState blockState = level.getBlockAt(x, y, z);

        if(blockState.getColor() == 0)
            return;

        for(Direction dir : Direction.values()) {
            if(_a(level, dir, x, y, z)) {
                Generator.stream(stream, dir, x, y, z, 1f, (blockState.getColor() >> 16) & 0xFF, (blockState.getColor() >> 8) & 0xFF, blockState.getColor() & 0xFF, (blockState.getColor() >> 24) & 0xFF);
            }
        }
    }

    static boolean _a(Level p, Direction d, int x, int y, int z) {
        if(y < 0 || y > 128)
            return true;

        return switch (d) {
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
        };
    }

    static boolean _n(Level p, int x, int y, int z) {
        return p.getBlockAt(x, y, z - 1).getColor() == 0;
    }

    static boolean _s(Level p, int x, int y, int z) {
        return p.getBlockAt(x, y, z + 1).getColor() == 0;
    }

    static boolean _e(Level p, int x, int y, int z) {
        return p.getBlockAt(x + 1, y, z).getColor() == 0;
    }

    static boolean _w(Level p, int x, int y, int z) {
        return p.getBlockAt(x - 1, y, z).getColor() == 0;
    }

    static boolean _t(Level p, int x, int y, int z) {
        return p.getBlockAt(x, y + 1, z).getColor() == 0;
    }

    static boolean _b(Level p, int x, int y, int z) {
        return p.getBlockAt(x, y - 1, z).getColor() == 0;
    }
}