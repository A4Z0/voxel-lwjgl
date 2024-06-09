package org.a4z0.lwjgl.demo.voxel.block.material;

import org.a4z0.lwjgl.demo.voxel.key.ResourceKey;

public enum Material {
    AIR(new ResourceKey("air"));

    private final ResourceKey k;

    /**
    * Construct a {@link Material}.
    *
    * @param k ...
    */

    Material(ResourceKey k) {
        this.k = k;
    }

    /**
    * @return a {@link ResourceKey}.
    */

    public ResourceKey getKey() {
        return this.k;
    }
}