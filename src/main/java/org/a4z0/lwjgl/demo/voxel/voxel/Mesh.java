package org.a4z0.lwjgl.demo.voxel.voxel;

import org.a4z0.lwjgl.demo.voxel.VoxelGameLWJGL;
import org.a4z0.lwjgl.demo.voxel.position.Direction;
import org.a4z0.lwjgl.demo.voxel.vertex.stream.VertexStream;

/**
* ...
*/

public class Mesh {

    /**
    * ...
    *
    * @param X1 ...
    * @param Y1 ...
    * @param Z1 ...
    * @param X2 ...
    * @param Y2 ...
    * @param Z2 ...
    *
    * @return ...
    */

    public static VertexStream create(int X1, int Y1, int Z1, int X2, int Y2, int Z2) {
        VertexStream Stream = new VertexStream();

        for(int X = X1; X <= X2; X++) {
            for(int Y = Y1; Y <= Y2; Y++) {
                for(int Z = Z1; Z <= Z2; Z++) {
                    int ID = VoxelGameLWJGL.WORLD.getVoxel(X, Y, Z);

                    if(ID == 0)
                        continue;

                    for(org.a4z0.lwjgl.demo.voxel.position.Direction Direction : Direction.values()) {
                        if(Mesh.isFaceVisible(Direction, X, Y, Z)) {
                            Generator.stream(Stream, Direction, X, Y, Z, 1f, (ID >> 16) & 0xFF, (ID >> 8) & 0xFF, ID & 0xFF, (ID >> 24) & 0xFF);
                        }
                    }
                }
            }
        }

        return Stream;
    }

    /**
    * ...
    *
    * @param Direction ...
    * @param X ...
    * @param Y ...
    * @param Z ...
    *
    * @return ...
    */

    private static boolean isFaceVisible(Direction Direction, int X, int Y, int Z) {
        return switch (Direction) {
            case NORTH ->
                VoxelGameLWJGL.WORLD.getVoxel(X, Y, Z - 1) == 0;
            case SOUTH ->
                VoxelGameLWJGL.WORLD.getVoxel(X, Y, Z + 1) == 0;
            case EAST ->
                VoxelGameLWJGL.WORLD.getVoxel(X + 1, Y, Z) == 0;
            case WEST ->
                VoxelGameLWJGL.WORLD.getVoxel(X - 1, Y, Z) == 0;
            case TOP ->
                VoxelGameLWJGL.WORLD.getVoxel(X, Y + 1, Z) == 0;
            case BOTTOM ->
                VoxelGameLWJGL.WORLD.getVoxel(X, Y - 1, Z) == 0;
        };
    }
}