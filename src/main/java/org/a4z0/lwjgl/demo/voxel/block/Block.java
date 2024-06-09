package org.a4z0.lwjgl.demo.voxel.block;

import org.a4z0.lwjgl.demo.voxel.key.ResourceKey;
import org.a4z0.lwjgl.demo.voxel.math.AABBf;

public abstract class Block {

    public static final int BLOCK_SIZE_X = 16;
    public static final int BLOCK_SIZE_Y = 16;
    public static final int BLOCK_SIZE_Z = 16;

    public static final AABBf BLOCK_AABB = new AABBf(-0.5f, -0.5f, -0.5f, 0.5f, 0.5f, 0.5f);
    public static final AABBf VOXEL_AABB = new AABBf(-0.0625f, -0.0625f, -0.0625f, 0.0625f, 0.0625f, 0.0625f);

    protected final ResourceKey k;

    protected final boolean s;

    /**
    * Construct a {@link Block}.
    *
    * @param k ...
    * @param s ...
    */

    public Block(ResourceKey k, boolean s) {
        this.k = k;
        this.s = s;
    }

    /**
    * @return the {@link ResourceKey}.
    */

    public ResourceKey getKey() {
        return this.k;
    }

    /**
    * @return true if this {@link Block} is solid, false otherwise.
    */

    public boolean isSolid() {
        return this.s;
    }
}