package org.a4z0.lwjgl.demo.voxel.block.face;

import org.a4z0.lwjgl.demo.voxel.level.Direction;

public class FaceGenerator {

    public static final float DEFAULT_FACE_SIZE = 0.03125f;
    public static final float ALIGN_ZERO = 1f - 0.03125f;

    public static final FaceGen NORTH = (stream, x, y, z, i) -> {
        float PX = DEFAULT_FACE_SIZE -ALIGN_ZERO + x;
        float NX = -DEFAULT_FACE_SIZE -ALIGN_ZERO + x;
        float PY = DEFAULT_FACE_SIZE -ALIGN_ZERO + y;
        float NY = -DEFAULT_FACE_SIZE -ALIGN_ZERO + y;
        float PZ = DEFAULT_FACE_SIZE -ALIGN_ZERO  + z;
        float NZ = -DEFAULT_FACE_SIZE -ALIGN_ZERO + z;

        stream.put(PX).put(PY).put(NZ).put(i).put(0);
        stream.put(PX).put(NY).put(NZ).put(i).put(0);
        stream.put(NX).put(NY).put(NZ).put(i).put(0);
        stream.put(NX).put(NY).put(NZ).put(i).put(0);
        stream.put(NX).put(PY).put(NZ).put(i).put(0);
        stream.put(PX).put(PY).put(NZ).put(i).put(0);
    };

    public static final FaceGen SOUTH = (stream, x, y, z, i) -> {
        float PX = DEFAULT_FACE_SIZE -ALIGN_ZERO + x;
        float NX = -DEFAULT_FACE_SIZE -ALIGN_ZERO + x;
        float PY = DEFAULT_FACE_SIZE -ALIGN_ZERO + y;
        float NY = -DEFAULT_FACE_SIZE -ALIGN_ZERO + y;
        float PZ = DEFAULT_FACE_SIZE -ALIGN_ZERO  + z;
        float NZ = -DEFAULT_FACE_SIZE -ALIGN_ZERO + z;

        stream.put(NX).put(PY).put(PZ).put(i).put(0);
        stream.put(NX).put(NY).put(PZ).put(i).put(0);
        stream.put(PX).put(NY).put(PZ).put(i).put(0);
        stream.put(PX).put(NY).put(PZ).put(i).put(0);
        stream.put(PX).put(PY).put(PZ).put(i).put(0);
        stream.put(NX).put(PY).put(PZ).put(i).put(0);
    };

    public static final FaceGen EAST = (stream, x, y, z, i) -> {
        float PX = DEFAULT_FACE_SIZE -ALIGN_ZERO + x;
        float NX = -DEFAULT_FACE_SIZE -ALIGN_ZERO + x;
        float PY = DEFAULT_FACE_SIZE -ALIGN_ZERO + y;
        float NY = -DEFAULT_FACE_SIZE -ALIGN_ZERO + y;
        float PZ = DEFAULT_FACE_SIZE -ALIGN_ZERO  + z;
        float NZ = -DEFAULT_FACE_SIZE -ALIGN_ZERO + z;

        stream.put(PX).put(PY).put(PZ).put(i).put(0);
        stream.put(PX).put(NY).put(PZ).put(i).put(0);
        stream.put(PX).put(NY).put(NZ).put(i).put(0);
        stream.put(PX).put(NY).put(NZ).put(i).put(0);
        stream.put(PX).put(PY).put(NZ).put(i).put(0);
        stream.put(PX).put(PY).put(PZ).put(i).put(0);
    };

    public static final FaceGen WEST = (stream, x, y, z, i) -> {
        float PX = DEFAULT_FACE_SIZE -ALIGN_ZERO + x;
        float NX = -DEFAULT_FACE_SIZE -ALIGN_ZERO + x;
        float PY = DEFAULT_FACE_SIZE -ALIGN_ZERO + y;
        float NY = -DEFAULT_FACE_SIZE -ALIGN_ZERO + y;
        float PZ = DEFAULT_FACE_SIZE -ALIGN_ZERO  + z;
        float NZ = -DEFAULT_FACE_SIZE -ALIGN_ZERO + z;

        stream.put(NX).put(PY).put(NZ).put(i).put(0);
        stream.put(NX).put(NY).put(NZ).put(i).put(0);
        stream.put(NX).put(NY).put(PZ).put(i).put(0);
        stream.put(NX).put(NY).put(PZ).put(i).put(0);
        stream.put(NX).put(PY).put(PZ).put(i).put(0);
        stream.put(NX).put(PY).put(NZ).put(i).put(0);
    };

    public static final FaceGen TOP = (stream, x, y, z, i) -> {
        float PX = DEFAULT_FACE_SIZE -ALIGN_ZERO + x;
        float NX = -DEFAULT_FACE_SIZE -ALIGN_ZERO + x;
        float PY = DEFAULT_FACE_SIZE -ALIGN_ZERO + y;
        float NY = -DEFAULT_FACE_SIZE -ALIGN_ZERO + y;
        float PZ = DEFAULT_FACE_SIZE -ALIGN_ZERO  + z;
        float NZ = -DEFAULT_FACE_SIZE -ALIGN_ZERO + z;

        stream.put(NX).put(PY).put(NZ).put(i).put(0);
        stream.put(NX).put(PY).put(PZ).put(i).put(0);
        stream.put(PX).put(PY).put(PZ).put(i).put(0);
        stream.put(PX).put(PY).put(PZ).put(i).put(0);
        stream.put(PX).put(PY).put(NZ).put(i).put(0);
        stream.put(NX).put(PY).put(NZ).put(i).put(0);
    };

    public static final FaceGen BOTTOM = (stream, x, y, z, i) -> {
        float PX = DEFAULT_FACE_SIZE -ALIGN_ZERO + x;
        float NX = -DEFAULT_FACE_SIZE -ALIGN_ZERO + x;
        float PY = DEFAULT_FACE_SIZE -ALIGN_ZERO + y;
        float NY = -DEFAULT_FACE_SIZE -ALIGN_ZERO + y;
        float PZ = DEFAULT_FACE_SIZE -ALIGN_ZERO  + z;
        float NZ = -DEFAULT_FACE_SIZE -ALIGN_ZERO + z;

        stream.put(NX).put(NY).put(PZ).put(i).put(0);
        stream.put(NX).put(NY).put(NZ).put(i).put(0);
        stream.put(PX).put(NY).put(NZ).put(i).put(0);
        stream.put(PX).put(NY).put(NZ).put(i).put(0);
        stream.put(PX).put(NY).put(PZ).put(i).put(0);
        stream.put(NX).put(NY).put(PZ).put(i).put(0);
    };

    /**
    * ...
    *
    * @param buffer ...
    * @param direction ...
    * @param x ...
    * @param y ...
    * @param z ...
    * @param i ...
    */

    /*public static void generate(DynamicBuffer buffer, Direction direction, float x, float y, float z, int i) {
        generate(buffer, direction, x, y, z, ((i >> 16) & 0xFF), ((i >> 8) & 0xFF), (i & 0xFF), ((i >> 24) & 0xFF));
    }*/

    /**
    * ...
    *
    * @param buffer ...
    * @param direction ...
    * @param x ...
    * @param y ...
    * @param z ...
    * @param r ...
    * @param g ...
    * @param b ...
    * @param a ...
    */

    public static void generate(DynamicBuffer buffer, Direction direction, float x, float y, float z, int i) {
        switch (direction) {
            case NORTH ->
                NORTH.generate(buffer, x, y, z, i);
            case SOUTH ->
                SOUTH.generate(buffer, x, y, z, i);
            case EAST ->
                EAST.generate(buffer, x, y, z, i);
            case WEST ->
                WEST.generate(buffer, x, y, z, i);
            case TOP ->
                TOP.generate(buffer, x, y, z, i);
            case BOTTOM ->
                BOTTOM.generate(buffer, x, y, z, i);
        }
    }
}