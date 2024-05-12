package org.a4z0.lwjgl.demo.voxel.level.chunk.layer;

import org.a4z0.lwjgl.demo.voxel.math.Direction;
import org.a4z0.lwjgl.demo.voxel.gl.vertex.VertexStream;

/**
* ...
*/

@Deprecated
public class Generator {

    /**
    * ...
    *
    * @param stream ...
    * @param dir ...
    * @param x ...
    * @param y ...
    * @param z ...
    * @param w ...
    * @param r ...
    * @param g ...
    * @param b ...
    * @param a ...
    */

    public static void stream(VertexStream stream, Direction dir, float x, float y, float z, float w, int r, int g, int b, int a) {
        float PX = (0.5f * w) + (x * w);
        float NX = (-0.5f * w) + (x * w);
        float PY = (0.5f * w) + (y * w);
        float NY = (-0.5f * w) + (y * w);
        float PZ = (0.5f * w) + (z * w);
        float NZ = (-0.5f * w) + (z * w);

        switch (dir) {
            case NORTH: {
                stream.put(PX).put(PY).put(NZ).put(r).put(g).put(b).put(a).put(0);
                stream.put(PX).put(NY).put(NZ).put(r).put(g).put(b).put(a).put(0);
                stream.put(NX).put(NY).put(NZ).put(r).put(g).put(b).put(a).put(0);
                stream.put(NX).put(NY).put(NZ).put(r).put(g).put(b).put(a).put(0);
                stream.put(NX).put(PY).put(NZ).put(r).put(g).put(b).put(a).put(0);
                stream.put(PX).put(PY).put(NZ).put(r).put(g).put(b).put(a).put(0);

                break;
            }
            case SOUTH: {
                stream.put(NX).put(PY).put(PZ).put(r).put(g).put(b).put(a).put(0);
                stream.put(NX).put(NY).put(PZ).put(r).put(g).put(b).put(a).put(0);
                stream.put(PX).put(NY).put(PZ).put(r).put(g).put(b).put(a).put(0);
                stream.put(PX).put(NY).put(PZ).put(r).put(g).put(b).put(a).put(0);
                stream.put(PX).put(PY).put(PZ).put(r).put(g).put(b).put(a).put(0);
                stream.put(NX).put(PY).put(PZ).put(r).put(g).put(b).put(a).put(0);

                break;
            }
            case EAST: {
                stream.put(PX).put(PY).put(PZ).put(r).put(g).put(b).put(a).put(0);
                stream.put(PX).put(NY).put(PZ).put(r).put(g).put(b).put(a).put(0);
                stream.put(PX).put(NY).put(NZ).put(r).put(g).put(b).put(a).put(0);
                stream.put(PX).put(NY).put(NZ).put(r).put(g).put(b).put(a).put(0);
                stream.put(PX).put(PY).put(NZ).put(r).put(g).put(b).put(a).put(0);
                stream.put(PX).put(PY).put(PZ).put(r).put(g).put(b).put(a).put(0);

                break;
            }
            case WEST: {
                stream.put(NX).put(PY).put(NZ).put(r).put(g).put(b).put(a).put(0);
                stream.put(NX).put(NY).put(NZ).put(r).put(g).put(b).put(a).put(0);
                stream.put(NX).put(NY).put(PZ).put(r).put(g).put(b).put(a).put(0);
                stream.put(NX).put(NY).put(PZ).put(r).put(g).put(b).put(a).put(0);
                stream.put(NX).put(PY).put(PZ).put(r).put(g).put(b).put(a).put(0);
                stream.put(NX).put(PY).put(NZ).put(r).put(g).put(b).put(a).put(0);

                break;
            }
            case TOP: {
                stream.put(NX).put(PY).put(NZ).put(r).put(g).put(b).put(a).put(0);
                stream.put(NX).put(PY).put(PZ).put(r).put(g).put(b).put(a).put(0);
                stream.put(PX).put(PY).put(PZ).put(r).put(g).put(b).put(a).put(0);
                stream.put(PX).put(PY).put(PZ).put(r).put(g).put(b).put(a).put(0);
                stream.put(PX).put(PY).put(NZ).put(r).put(g).put(b).put(a).put(0);
                stream.put(NX).put(PY).put(NZ).put(r).put(g).put(b).put(a).put(0);

                break;
            }
            case BOTTOM: {
                stream.put(NX).put(NY).put(PZ).put(r).put(g).put(b).put(a).put(0);
                stream.put(NX).put(NY).put(NZ).put(r).put(g).put(b).put(a).put(0);
                stream.put(PX).put(NY).put(NZ).put(r).put(g).put(b).put(a).put(0);
                stream.put(PX).put(NY).put(NZ).put(r).put(g).put(b).put(a).put(0);
                stream.put(PX).put(NY).put(PZ).put(r).put(g).put(b).put(a).put(0);
                stream.put(NX).put(NY).put(PZ).put(r).put(g).put(b).put(a).put(0);

                break;
            }
        }
    }
}