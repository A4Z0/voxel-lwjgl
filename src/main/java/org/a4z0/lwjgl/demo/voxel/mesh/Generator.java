package org.a4z0.lwjgl.demo.voxel.mesh;

import org.a4z0.lwjgl.demo.voxel.position.Direction;
import org.a4z0.lwjgl.demo.voxel.gl.vertex.VertexStream;

/**
* ...
*/

public class Generator {

    /**
    * ...
    *
    * @param grid ...
    * @param dir ...
    * @param x ...
    * @param y ...
    * @param z ...
    * @param w ...
    * @param i ...
    */

    public static void stream(VertexStream grid, Direction dir, float x, float y, float z, float w, int r, int g, int b, int a) {
        float PX = (0.5f * w) + (x * w);
        float NX = (-0.5f * w) + (x * w);
        float PY = (0.5f * w) + (y * w);
        float NY = (-0.5f * w) + (y * w);
        float PZ = (0.5f * w) + (z * w);
        float NZ = (-0.5f * w) + (z * w);

        switch (dir) {
            case NORTH: {
                grid.put(PX).put(PY).put(NZ).put(r).put(g).put(b).put(a).put(0);
                grid.put(PX).put(NY).put(NZ).put(r).put(g).put(b).put(a).put(0);
                grid.put(NX).put(NY).put(NZ).put(r).put(g).put(b).put(a).put(0);
                grid.put(NX).put(NY).put(NZ).put(r).put(g).put(b).put(a).put(0);
                grid.put(NX).put(PY).put(NZ).put(r).put(g).put(b).put(a).put(0);
                grid.put(PX).put(PY).put(NZ).put(r).put(g).put(b).put(a).put(0);

                break;
            }
            case SOUTH: {
                grid.put(NX).put(PY).put(PZ).put(r).put(g).put(b).put(a).put(0);
                grid.put(NX).put(NY).put(PZ).put(r).put(g).put(b).put(a).put(0);
                grid.put(PX).put(NY).put(PZ).put(r).put(g).put(b).put(a).put(0);
                grid.put(PX).put(NY).put(PZ).put(r).put(g).put(b).put(a).put(0);
                grid.put(PX).put(PY).put(PZ).put(r).put(g).put(b).put(a).put(0);
                grid.put(NX).put(PY).put(PZ).put(r).put(g).put(b).put(a).put(0);

                break;
            }
            case EAST: {
                grid.put(PX).put(PY).put(PZ).put(r).put(g).put(b).put(a).put(0);
                grid.put(PX).put(NY).put(PZ).put(r).put(g).put(b).put(a).put(0);
                grid.put(PX).put(NY).put(NZ).put(r).put(g).put(b).put(a).put(0);
                grid.put(PX).put(NY).put(NZ).put(r).put(g).put(b).put(a).put(0);
                grid.put(PX).put(PY).put(NZ).put(r).put(g).put(b).put(a).put(0);
                grid.put(PX).put(PY).put(PZ).put(r).put(g).put(b).put(a).put(0);

                break;
            }
            case WEST: {
                grid.put(NX).put(PY).put(NZ).put(r).put(g).put(b).put(a).put(0);
                grid.put(NX).put(NY).put(NZ).put(r).put(g).put(b).put(a).put(0);
                grid.put(NX).put(NY).put(PZ).put(r).put(g).put(b).put(a).put(0);
                grid.put(NX).put(NY).put(PZ).put(r).put(g).put(b).put(a).put(0);
                grid.put(NX).put(PY).put(PZ).put(r).put(g).put(b).put(a).put(0);
                grid.put(NX).put(PY).put(NZ).put(r).put(g).put(b).put(a).put(0);

                break;
            }
            case TOP: {
                grid.put(NX).put(PY).put(NZ).put(r).put(g).put(b).put(a).put(0);
                grid.put(NX).put(PY).put(PZ).put(r).put(g).put(b).put(a).put(0);
                grid.put(PX).put(PY).put(PZ).put(r).put(g).put(b).put(a).put(0);
                grid.put(PX).put(PY).put(PZ).put(r).put(g).put(b).put(a).put(0);
                grid.put(PX).put(PY).put(NZ).put(r).put(g).put(b).put(a).put(0);
                grid.put(NX).put(PY).put(NZ).put(r).put(g).put(b).put(a).put(0);

                break;
            }
            case BOTTOM: {
                grid.put(NX).put(NY).put(PZ).put(r).put(g).put(b).put(a).put(0);
                grid.put(NX).put(NY).put(NZ).put(r).put(g).put(b).put(a).put(0);
                grid.put(PX).put(NY).put(NZ).put(r).put(g).put(b).put(a).put(0);
                grid.put(PX).put(NY).put(NZ).put(r).put(g).put(b).put(a).put(0);
                grid.put(PX).put(NY).put(PZ).put(r).put(g).put(b).put(a).put(0);
                grid.put(NX).put(NY).put(PZ).put(r).put(g).put(b).put(a).put(0);

                break;
            }
        }
    }
}