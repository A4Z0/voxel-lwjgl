package org.a4z0.lwjgl.demo.voxel.block.face;

import org.a4z0.lwjgl.demo.voxel.level.Direction;

@FunctionalInterface
public interface Face {

    void build(Direction Direction, float x, float y, float z);
}