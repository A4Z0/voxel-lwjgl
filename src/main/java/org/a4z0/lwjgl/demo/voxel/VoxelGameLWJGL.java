package org.a4z0.lwjgl.demo.voxel;

import org.a4z0.lwjgl.demo.voxel.camera.Camera;
import org.a4z0.lwjgl.demo.voxel.camera.FreeCamera;
import org.a4z0.lwjgl.demo.voxel.camera.ObjectCameraController;
import org.a4z0.lwjgl.demo.voxel.input.Input;
import org.a4z0.lwjgl.demo.voxel.position.Location;
import org.a4z0.lwjgl.demo.voxel.render.OutlineRenderer;
import org.a4z0.lwjgl.demo.voxel.render.VoxelRenderer;
import org.a4z0.lwjgl.demo.voxel.shader.pre.VGShaders;
import org.a4z0.lwjgl.demo.voxel.window.Window;
import org.a4z0.lwjgl.demo.voxel.world.Overworld;
import org.a4z0.lwjgl.demo.voxel.world.chunk.IChunk;
import org.a4z0.lwjgl.demo.voxel.world.chunk.provider.IChunkProvider;
import org.a4z0.lwjgl.demo.voxel.world.chunk.provider.ChunkProvider;
import org.a4z0.lwjgl.demo.voxel.world.pipeline.IPipeline;
import org.a4z0.lwjgl.demo.voxel.world.pipeline.Pipeline;
import org.joml.Matrix4f;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

/**
* Represents a Voxel Game.
*/

public class VoxelGameLWJGL {

    public static Window WINDOW;

    public static Camera CAMERA;
    public static ObjectCameraController CAMERA_CONTROLLER;

    public static IChunkProvider CHUNK_PROVIDER;
    public static IPipeline PIPELINE;

    public static VoxelRenderer VOXEL_RENDERER;
    public static OutlineRenderer OUTLINE_RENDERER;

    public static boolean FULLSCREEN;
    public static boolean DEBUG_CHUNK;
    public static boolean DISABLE_FRUSTUM_UPDATE;

    public static void main(String[] args) {
        if(!glfwInit())
            throw new IllegalStateException("Couldn't initialize GLFW!");

        // Window
        WINDOW = new Window();
        WINDOW.setVsync(false);

        glfwWindowHint(GLFW_SAMPLES, 16);

        // Loaders
        VGShaders.init();

        // Input
        glfwSetInputMode(WINDOW.getID(), GLFW_CURSOR, GLFW_CURSOR_DISABLED);
        glfwSetCursorPosCallback(WINDOW.getID(), (window, x, y) -> {
            CAMERA_CONTROLLER.onMouse((float) x, (float) y);
        });

        // Camera
        CAMERA = new FreeCamera();
        CAMERA.getPosition().set(0, 16, 0);

        CAMERA_CONTROLLER = new ObjectCameraController(CAMERA);

        // Renderer
        VOXEL_RENDERER = new VoxelRenderer();
        OUTLINE_RENDERER = new OutlineRenderer();

        // Chunk Provider
        CHUNK_PROVIDER = new ChunkProvider(new Overworld(0));

        for(int x = 0; x < 16; x++) {
            for(int y = 0; y < 16; y++) {
                for(int z = 0; z < 16; z++) {
                    CHUNK_PROVIDER.getChunkAt(x, z).setVoxel(x, y, z, x * 4, y * 4, z * 4);
                }
            }
        }

        // Pipeline
        PIPELINE = new Pipeline(CHUNK_PROVIDER, 0, 0, 0);
        boolean PIPELINE_CALLED = false;

        // Default
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glClearColor(0f, 0f, 0f, 1.0f);
        glEnable(GL_DEPTH_TEST);
        glClearDepth(1f);
        glDepthFunc(GL_LEQUAL);
        glEnable(GL_CULL_FACE);
        glCullFace(GL_BACK);
        glLineWidth(1f);

        // Loop
        while(!WINDOW.isClosing()) {
            glViewport(0, 0, WINDOW.getWidth(), WINDOW.getHeight());
            glClear(GL_COLOR_BUFFER_BIT  | GL_DEPTH_BUFFER_BIT);
            glClearColor(0.6f, 0.8f, 1f, 1);

            // Window Fullscreen (F12)
            if(Input.isKeyPressed(GLFW_KEY_F12))
                WINDOW.setFullScreen(FULLSCREEN = !FULLSCREEN);

            // Game Exit (ESC+X)
            if(Input.isKeyDown(GLFW_KEY_ESCAPE) && Input.isKeyPressed(GLFW_KEY_X)) {
                glfwTerminate();
                System.exit(0);
            }

            // Camera Keyboard
            CAMERA_CONTROLLER.onKeyboard();

            Matrix4f Projection = CAMERA.getProjection();
            Matrix4f View = CAMERA.getView();

            Location Position = CAMERA.getPosition();

            // Camera Projection + View
            VGShaders.VOXEL_SHADER_PROGRAM.bind();
            VGShaders.VOXEL_SHADER_PROGRAM.setUniform4fv("camera_projection", Projection);
            VGShaders.VOXEL_SHADER_PROGRAM.setUniform4fv("camera_view", View);

            /*int[] Looking = getBlockAt(Position, 5);

            if(Looking != null) {
                if(Input.isButtonDown(GLFW_MOUSE_BUTTON_LEFT)) {
                    CHUNK_PROVIDER.getChunkAt(Looking[0], Looking[2]).setVoxel(Looking[0], Looking[1], Looking[2], 0, 0, 0, 0, true);
                }

                OUTLINE_RENDERER.render(OutlineRenderer.VOXEL_BOUNDARIES, Looking[0], Looking[1], Looking[2], 0, 0, 0, 1f, 1f);
            }

            for(IChunk chunk : CHUNK_PROVIDER.getChunks()) {
                if(chunk == null)
                    continue;

                /*for(Layer layer : chunk.getLayerProvider().getLayers()) {
                    layer.update(System.nanoTime());
                    layer.render();
                }
            }*/


            PIPELINE.update();
            PIPELINE.render();

            VGShaders.VOXEL_SHADER_PROGRAM.unbind();

            VGShaders.OUTLINE_SHADER_PROGRAM.bind();
            VGShaders.OUTLINE_SHADER_PROGRAM.setUniform4fv("camera_projection", Projection);
            VGShaders.OUTLINE_SHADER_PROGRAM.setUniform4fv("camera_view", View);

            // Chunk Debugging (Ctrl+G)
            if(Input.isKeyDown(GLFW_KEY_LEFT_CONTROL) && Input.isKeyPressed(GLFW_KEY_G))
                DEBUG_CHUNK = !DEBUG_CHUNK;

            if(DEBUG_CHUNK) {
                IChunk chunk = CHUNK_PROVIDER.getChunkAt(Position.getBlockX(), Position.getBlockZ());

                WINDOW.setTitle("Chunk - X: " + chunk.getX() + ", Z: " + chunk.getZ() + " -> " + chunk.getClass().getSimpleName());

                OUTLINE_RENDERER.render(chunk, 1f, 1f, 0, 1f, 1f);
            }

            VGShaders.OUTLINE_SHADER_PROGRAM.bind();

            // Window Update
            WINDOW.update();
        }

        glfwTerminate();
        System.exit(0);
    }

    /*public static int[] getBlockAt(Location Position, int Range) {
        Vector3f Dir = Position.getDirection();

        for(int i = 0; i <= Range; i++) {
            int X = Math.round((Position.getX() + Dir.x * i));
            int Y = Math.round((Position.getY() + Dir.y * i));
            int Z = Math.round((Position.getZ() + Dir.z * i));

            if(CHUNK_PROVIDER.getChunkAt(X, Z).getVoxel(X, Y, Z) != 0)
                return new int[]{X, Y, Z};
        }

        return null;
    }*/
}