package org.a4z0.lwjgl.demo.voxel.block.face;

import org.a4z0.lwjgl.demo.voxel.buffer.DynamicByteBuffer;
import org.a4z0.lwjgl.demo.voxel.math.Vector3f;

public class FaceBuilder {

    public static final Vector3f[] NORTH = new Vector3f[]{
        new Vector3f(1f, 1f, 0f),
        new Vector3f(1f, 0f, 0f),
        new Vector3f(0f, 0f, 0f),
        new Vector3f(0f, 0f, 0f),
        new Vector3f(0f, 1f, 0f),
        new Vector3f(1f, 1f, 0f)
    };

    public static final Vector3f[] SOUTH = new Vector3f[]{
        new Vector3f(0f, 1f, 1f),
        new Vector3f(0f, 0f, 1f),
        new Vector3f(1f, 0f, 1f),
        new Vector3f(1f, 0f, 1f),
        new Vector3f(1f, 1f, 1f),
        new Vector3f(0f, 1f, 1f)
    };

    public static final Vector3f[] EAST = new Vector3f[]{
        new Vector3f(1f, 1f, 1f),
        new Vector3f(1f, 0f, 1f),
        new Vector3f(1f, 0f, 0f),
        new Vector3f(1f, 0f, 0f),
        new Vector3f(1f, 1f, 0f),
        new Vector3f(1f, 1f, 1f)
    };

    public static final Vector3f[] WEST = new Vector3f[]{
        new Vector3f(0f, 1f, 0f),
        new Vector3f(0f, 0f, 0f),
        new Vector3f(0f, 0f, 1f),
        new Vector3f(0f, 0f, 1f),
        new Vector3f(0f, 1f, 1f),
        new Vector3f(0f, 1f, 0f)
    };

    public static final Vector3f[] TOP = new Vector3f[]{
        new Vector3f(0f, 1f, 0f),
        new Vector3f(0f, 1f, 1f),
        new Vector3f(1f, 1f, 1f),
        new Vector3f(1f, 1f, 1f),
        new Vector3f(1f, 1f, 0f),
        new Vector3f(0f, 1f, 0f)
    };

    public static final Vector3f[] BOTTOM = new Vector3f[]{
        new Vector3f(0f, 0f, 1f),
        new Vector3f(0f, 0f, 0f),
        new Vector3f(1f, 0f, 0f),
        new Vector3f(1f, 0f, 0f),
        new Vector3f(1f, 0f, 1f),
        new Vector3f(0f, 0f, 1f)
    };

    static {
        for(Vector3f[] Face : new Vector3f[][]{NORTH, SOUTH, EAST, WEST, TOP, BOTTOM}) {
            for(Vector3f Vertex : Face) {
                Vertex.multiply(0.0625f);
            }
        }
    }

    public static Face create(DynamicByteBuffer Buffer, int i) {
        return (Direction, x, y, z) -> {
            for(Vector3f Vertex : switch (Direction) {
                case NORTH ->
                    NORTH;
                case SOUTH ->
                    SOUTH;
                case EAST ->
                    EAST;
                case WEST ->
                    WEST;
                case TOP ->
                    TOP;
                case BOTTOM ->
                    BOTTOM;
            }) {
                Buffer.put(Vertex.getX() + x).put(Vertex.getY() + y).put(Vertex.getZ() + z).put(i).put(0);
            }
        };
    }
}