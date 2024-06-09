package org.a4z0.lwjgl.demo.voxel.block;

import org.a4z0.lwjgl.demo.voxel.key.ResourceKey;

public class BlockAir extends Block {

    /**
    * Construct a {@link BlockAir}.
    */

    public BlockAir() {
        super(new ResourceKey("air"), false);
    }
}