package org.a4z0.lwjgl.demo.voxel.input;

import org.a4z0.lwjgl.demo.voxel.VoxelGameLWJGL;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;

import java.nio.DoubleBuffer;

import static org.lwjgl.glfw.GLFW.*;

/**
* ...
*/

public final class Input {

    public static final boolean[] KEYS = new boolean[GLFW_KEY_LAST];
    public static final boolean[] BUTTONS = new boolean[GLFW_MOUSE_BUTTON_LAST];

    @Deprecated
    public static void update() {
        for(int i = 0; i < GLFW_KEY_LAST; i++)
            KEYS[i] = isKeyDown(i);
        for(int i = 0; i < GLFW_MOUSE_BUTTON_LAST; i++)
            BUTTONS[i] = isButtonDown(i);
    }

    /**
    * @param GL_KEY_CODE ...
    *
    * @return ...
    */

    public static boolean isKeyDown(int GL_KEY_CODE) {
        return GLFW.glfwGetKey(VoxelGameLWJGL.WINDOW.getID(), GL_KEY_CODE) == 1;
    }

    /**
    * @param GL_KEY_CODE ...
    *
    * @return ...
    */

    public static boolean isKeyPressed(int GL_KEY_CODE) {
        return isKeyDown(GL_KEY_CODE) && !KEYS[GL_KEY_CODE];
    }

    /**
    * @param GL_KEY_CODE ...
    *
    * @return ...
    */

    public static boolean isKeyReleased(int GL_KEY_CODE) {
        return !isKeyDown(GL_KEY_CODE) && !KEYS[GL_KEY_CODE];
    }

    /**
    * @param GL_BUTTON_CODE ...
    *
    * @return ...
    */

    public static boolean isButtonDown(int GL_BUTTON_CODE) {
        return GLFW.glfwGetMouseButton(VoxelGameLWJGL.WINDOW.getID(), GL_BUTTON_CODE) == 1;
    }

    /**
    * @param GL_BUTTON_CODE ...
    *
    * @return ...
    */

    public static boolean isButtonPressed(int GL_BUTTON_CODE) {
        return isButtonDown(GL_BUTTON_CODE) && !BUTTONS[GL_BUTTON_CODE];
    }

    /**
    * @param GL_BUTTON_CODE ...
    *
    * @return ...
    */

    public static boolean isButtonReleased(int GL_BUTTON_CODE) {
        return !isButtonDown(GL_BUTTON_CODE) && !BUTTONS[GL_BUTTON_CODE];
    }

    /**
    * @return ...
    */

    public static double getMouseX() {
        DoubleBuffer DOUBLE_BUFFER = BufferUtils.createDoubleBuffer(1);
        GLFW.glfwGetCursorPos(VoxelGameLWJGL.WINDOW.getID(), DOUBLE_BUFFER, null);

        return DOUBLE_BUFFER.get();
    }

    /**
    * @return ...
    */

    public static double getMouseY() {
        DoubleBuffer DOUBLE_BUFFER = BufferUtils.createDoubleBuffer(1);
        GLFW.glfwGetCursorPos(VoxelGameLWJGL.WINDOW.getID(), null, DOUBLE_BUFFER);

        return DOUBLE_BUFFER.get();
    }
}