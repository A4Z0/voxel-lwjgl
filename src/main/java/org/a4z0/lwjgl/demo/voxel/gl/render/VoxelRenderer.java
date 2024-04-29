package org.a4z0.lwjgl.demo.voxel.gl.render;

/**
* ...
*/

public class VoxelRenderer {

    /**
    * ...
    */

    public VoxelRenderer() {

    }

    /**
    * ...
    *
    * @param Grid ...
    * @param Length ...
    */

    /*public void render(int X, int Y, int Z, GridBuffer Grid, int Length) {
        VoxelGameShaders.VOXEL_SHADER_PROGRAM.bind();

        glBindVertexArray(Grid.getVAO().getID());

        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glEnableVertexAttribArray(2);

        VoxelGameShaders.VOXEL_SHADER_PROGRAM.setUniform("transformation", new Matrix4f().translate(X, Y, Z));

        glDrawArrays(GL_TRIANGLES, 0, Length);

        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glDisableVertexAttribArray(2);
        glBindVertexArray(0);

        Grid.getVAO().unbind();
        VoxelGameShaders.VOXEL_SHADER_PROGRAM.unbind();
    }*/
}