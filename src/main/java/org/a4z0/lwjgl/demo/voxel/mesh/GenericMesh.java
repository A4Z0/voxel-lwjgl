package org.a4z0.lwjgl.demo.voxel.mesh;

import org.a4z0.lwjgl.demo.voxel.legacy.collision.AABB3i;
import org.a4z0.lwjgl.demo.voxel.position.Direction;
import org.a4z0.lwjgl.demo.voxel.gl.vertex.VertexStream;
import org.a4z0.lwjgl.demo.voxel.gl.voxel.Voxel;
import org.a4z0.lwjgl.demo.voxel.chunk.IChunkProvider;

/**
* Represents a Mesh.
*/

public class GenericMesh implements Mesh {

    protected final AABB3i AABB;

    /**
    * Construct a {@link GenericMesh}.
    *
    * @param AABB {@link AABB3i} that will represent the bounds of this {@link Mesh}.
    */

    public GenericMesh(AABB3i AABB) {
        this.AABB = AABB;
    }

    @Override
    public VertexStream build(IChunkProvider provider, VertexStream stream) {
        for(int X = this.AABB.getLowerX(); X <= this.AABB.getUpperX(); X++)
            for(int Y = this.AABB.getLowerY(); Y <= this.AABB.getUpperY(); Y++)
                for(int Z = this.AABB.getLowerZ(); Z <= this.AABB.getUpperZ(); Z++)
                    this.generateVertices(provider, stream, X, Y, Z);

        return stream;
    }


    @Deprecated
    private void generateVertices(IChunkProvider CHUNK_PROVIDER, VertexStream VERTEX_STREAM, int X, int Y, int Z) {
        Voxel VOXEL = CHUNK_PROVIDER.getChunkAt(X, Z).getVoxel(X, Y, Z);

        if(VOXEL.compare(Voxel.EMPTY_VOXEL))
            return;

        for(Direction DIRECTION : Direction.values())
            if(this.getNeighborAt(CHUNK_PROVIDER, DIRECTION, X, Y, Z))
                Generator.stream(VERTEX_STREAM, DIRECTION, X, Y, Z, 1f, (VOXEL.getColor() >> 16) & 0xFF, (VOXEL.getColor() >> 8) & 0xFF, VOXEL.getColor() & 0xFF, (VOXEL.getColor() >> 24) & 0xFF);
    }

    @Deprecated
    private boolean getNeighborAt(IChunkProvider Provider, Direction Direction, int X, int Y, int Z) {
        switch (Direction) {
            case NORTH:
                Z -= 1;

                break;
            case SOUTH:
                Z += 1;

                break;
            case EAST:
                X += 1;

                break;
            case WEST:
                X -= 1;

                break;
            case TOP:
                Y += 1;

                break;
            case BOTTOM:
                Y -= 1;

                break;
        };

        return Provider.getChunkAt(X, Z).getVoxel(X, Y, Z).compare(Voxel.EMPTY_VOXEL);
    }
}