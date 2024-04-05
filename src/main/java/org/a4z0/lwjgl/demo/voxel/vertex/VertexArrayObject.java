package org.a4z0.lwjgl.demo.voxel.vertex;

import static org.lwjgl.opengl.GL30.*;

/**
* Represents a Vertex Array Object.
*/

public class VertexArrayObject {

    protected int glVAO;

    /**
    * Construct a {@link VertexArrayObject}.
    */

    public VertexArrayObject() {
        this.glVAO = glGenVertexArrays();
    }

    /**
    * @return this {@link VertexArrayObject VAO} ID.
    */

    public int getID() {
        return this.glVAO;
    }

    /**
    * Binds this {@link VertexArrayObject VAO} to the current context.
    */

    public void bind() {
        glBindVertexArray(this.glVAO);
    }

    /**
    * Unbinds this {@link VertexArrayObject VAO} from the current context.
    */

    public void unbind() {
        glBindVertexArray(0);
    }

    /**
    * Deletes this {@link VertexArrayObject VAO}.
    */

    public void delete() {
        glDeleteVertexArrays(this.glVAO);
    }

    /**
    * Points out the format of the attribute data in the current context.
    *
    * @param glIndex Index of the Attribute.
    * @param glSize Size of the Data per Attribute.
    * @param glType Data Type of the Attribute Data.
    * @param glNormalized Indicates whether the Data should be normalized.
    * @param glStride Number of bytes between consecutive sets of Attributes in memory.
    * @param glPointer Offset in bytes from the start of the Buffer to the first Attribute.
    */

    public void attribute(int glIndex, int glSize, int glType, boolean glNormalized, int glStride, int glPointer) {
        glVertexAttribPointer(glIndex, glSize, glType, glNormalized, glStride, glPointer);
    }
}