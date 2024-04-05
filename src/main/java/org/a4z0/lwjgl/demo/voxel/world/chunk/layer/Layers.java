package org.a4z0.lwjgl.demo.voxel.world.chunk.layer;

import org.a4z0.lwjgl.demo.voxel.map.LayerCoordinate;
import org.a4z0.lwjgl.demo.voxel.world.chunk.Chunk;

import java.util.HashMap;

/**
* ...
*/

public class Layers {

    public static final int LAYERS_SIZE_X = (Chunk.CHUNK_SIZE_X / Layer.LAYER_SIZE_X);
    public static final int LAYERS_SIZE_Y = (Chunk.CHUNK_SIZE_Y / Layer.LAYER_SIZE_Y);
    public static final int LAYERS_SIZE_Z = (Chunk.CHUNK_SIZE_Z / Layer.LAYER_SIZE_Z);

    public static final int LAYERS_ARRAY_SIZE_X = LAYERS_SIZE_X + 1;
    public static final int LAYERS_ARRAY_SIZE_Y = LAYERS_SIZE_Y + 1;
    public static final int LAYERS_ARRAY_SIZE_Z = LAYERS_SIZE_Z + 1;

    public static final int LAYERS_LENGTH = (LAYERS_SIZE_X * LAYERS_SIZE_Y * LAYERS_SIZE_Z);

    protected final Chunk chunk;
    protected final HashMap<Long, Layer> layers = new HashMap<>();

    /**
    * Construct a {@link Layers}.
    *
    * @param chunk {@link Chunk} that the {@link Layer Layers} are in.
    */

    public Layers(Chunk chunk) {
        this.chunk = chunk;

        for(int x = 0; x < LAYERS_SIZE_X; x++) {
            for(int y = 0; y < LAYERS_SIZE_Y; y++) {
                for(int z = 0; z < LAYERS_SIZE_Z; z++) {
                    Layer layer = new Layer(this.chunk, (x * Layer.LAYER_SIZE_X) + this.chunk.getX(), (y * Layer.LAYER_SIZE_Y), (z * Layer.LAYER_SIZE_Z) + this.chunk.getZ());
                    this.layers.put(LayerCoordinate.asLongFromBlock(layer.getX() - this.chunk.getX(), layer.getY(), layer.getZ() - this.chunk.getZ()), layer);
                }
            }
        }
    }

    /**
    * Obtains a {@link Layer} at the given coordinates.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return a {@link Layer}.
    */

    public Layer getLayerAt(int x, int y, int z) {
        Layer layer = this.layers.get(LayerCoordinate.asLongFromBlock(x - this.chunk.getX(), y, z - this.chunk.getZ()));

        if(layer == null)
            return null;

        return layer;
    }

    /**
    * @return all {@link Layer Layers}.
    */

    public Layer[] getLayers() {
        return this.layers.values().toArray(new Layer[0]);
    }
}