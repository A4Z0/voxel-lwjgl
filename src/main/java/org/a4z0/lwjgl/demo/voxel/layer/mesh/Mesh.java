package org.a4z0.lwjgl.demo.voxel.layer.mesh;

import org.a4z0.lwjgl.demo.voxel.block.voxel.VoxelPosition;
import org.a4z0.lwjgl.demo.voxel.chunk.Chunk;
import org.a4z0.lwjgl.demo.voxel.block.face.FaceGenerator;
import org.a4z0.lwjgl.demo.voxel.legacy.done.buffer.DynamicBuffer;
import org.a4z0.lwjgl.demo.voxel.level.Direction;
import org.a4z0.lwjgl.demo.voxel.block.voxel.Voxel;

public class Mesh {

    /**
    * ...
    *
    * @param buffer ...
    * @param chunk ...
    * @param x1 ...
    * @param y1 ...
    * @param z1 ...
    * @param x2 ...
    * @param y2 ...
    * @param z2 ...
    */

    public static void build(DynamicBuffer buffer, Chunk chunk, int x1, int y1, int z1, int x2, int y2, int z2) {
        for(int x = x1; x < x2; x++) {
            for(int y = y1; y < y2; y++) {
                for(int z = z1; z < z2; z++) {
                    Voxel voxel = chunk.getVoxelAt(x, y, z);
                    int color = voxel.getColor();

                    if(color == 0)
                        continue;

                    for(Direction dir : Direction.values()) {
                        switch (dir) {
                            case NORTH -> {
                                if(voxel.getNorth().getColor() != 0)
                                    break;

                                VoxelPosition pos = voxel.getPosition();

                                FaceGenerator.generate(buffer, dir, pos.getX(), pos.getY(), pos.getZ(), color);
                            }
                            case SOUTH -> {
                                if(voxel.getSouth().getColor() != 0)
                                    break;

                                VoxelPosition pos = voxel.getPosition();

                                FaceGenerator.generate(buffer, dir, pos.getX(), pos.getY(), pos.getZ(), color);
                            }
                            case EAST -> {
                                if(voxel.getEast().getColor() != 0)
                                    break;

                                VoxelPosition pos = voxel.getPosition();

                                FaceGenerator.generate(buffer, dir, pos.getX(), pos.getY(), pos.getZ(), color);
                            }
                            case WEST -> {
                                if(voxel.getWest().getColor() != 0)
                                    break;

                                VoxelPosition pos = voxel.getPosition();

                                FaceGenerator.generate(buffer, dir, pos.getX(), pos.getY(), pos.getZ(), color);
                            }
                            case TOP -> {
                                if(voxel.getTop().getColor() != 0)
                                    break;

                                VoxelPosition pos = voxel.getPosition();

                                FaceGenerator.generate(buffer, dir, pos.getX(), pos.getY(), pos.getZ(), color);
                            }
                            case BOTTOM -> {
                                if(voxel.getBottom().getColor() != 0)
                                    break;

                                VoxelPosition pos = voxel.getPosition();

                                FaceGenerator.generate(buffer, dir, pos.getX(), pos.getY(), pos.getZ(), color);
                            }
                        }
                    }
                }
            }
        }
    }
}