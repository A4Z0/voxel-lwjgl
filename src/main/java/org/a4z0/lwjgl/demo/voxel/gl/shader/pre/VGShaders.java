package org.a4z0.lwjgl.demo.voxel.gl.shader.pre;

import org.a4z0.lwjgl.demo.voxel.gl.shader.Shader;
import org.a4z0.lwjgl.demo.voxel.gl.shader.ShaderProgram;

import java.io.IOException;
import java.io.InputStream;

import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;

/**
* Represents a {@link VGShaders}.
*/

public class VGShaders {

    public static ShaderProgram VOXEL_SHADER_PROGRAM;
    public static ShaderProgram OUTLINE_SHADER_PROGRAM;

    static {
        {
            VOXEL_SHADER_PROGRAM = new ShaderProgram();
            VOXEL_SHADER_PROGRAM.attribute(0, "vertex_position");
            VOXEL_SHADER_PROGRAM.attribute(1, "vertex_color");

            // Vertex
            Shader VOXEL_VERTEX_SHADER = new Shader(GL_VERTEX_SHADER);
            VOXEL_VERTEX_SHADER.source(getSource("assets/shaders/voxel.vert"));
            VOXEL_VERTEX_SHADER.compile();

            // Fragment
            Shader VOXEL_FRAGMENT_SHADER = new Shader(GL_FRAGMENT_SHADER);
            VOXEL_FRAGMENT_SHADER.source(getSource("assets/shaders/voxel.frag"));
            VOXEL_FRAGMENT_SHADER.compile();

            VOXEL_SHADER_PROGRAM.addShader(VOXEL_VERTEX_SHADER);
            VOXEL_SHADER_PROGRAM.addShader(VOXEL_FRAGMENT_SHADER);

            VOXEL_SHADER_PROGRAM.link();
        }
        {
            OUTLINE_SHADER_PROGRAM = new ShaderProgram();
            OUTLINE_SHADER_PROGRAM.attribute(0, "vertex_position");
            OUTLINE_SHADER_PROGRAM.attribute(1, "vertex_color");

            // Vertex
            Shader OUTLINE_VERTEX_SHADER = new Shader(GL_VERTEX_SHADER);
            OUTLINE_VERTEX_SHADER.source(getSource("assets/shaders/outline.vert"));
            OUTLINE_VERTEX_SHADER.compile();

            // Fragment
            Shader OUTLINE_FRAGMENT_SHADER = new Shader(GL_FRAGMENT_SHADER);
            OUTLINE_FRAGMENT_SHADER.source(getSource("assets/shaders/outline.frag"));
            OUTLINE_FRAGMENT_SHADER.compile();

            OUTLINE_SHADER_PROGRAM.addShader(OUTLINE_VERTEX_SHADER);
            OUTLINE_SHADER_PROGRAM.addShader(OUTLINE_FRAGMENT_SHADER);

            OUTLINE_SHADER_PROGRAM.link();
        }
    }

    /**
    * Initializes this {@link VGShaders}.
    */

    public static void init() {

    }

    /**
    * ...
    *
    * @param uri ...
    *
    * @return ...
    */

    private static String getSource(String uri) {
        return getSource(VGShaders.class.getClassLoader().getResourceAsStream(uri));
    }

    /**
    * ...
    *
    * @param stream ...
    *
    * @return a {@link String}.
    */

    private static String getSource(InputStream stream) {
        try(InputStream i = stream) {
            return new String(i.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}