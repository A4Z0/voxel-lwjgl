package org.a4z0.lwjgl.demo.voxel.legacy.util;

import static org.lwjgl.glfw.GLFW.*;

/**
* Listens to Mouse Input through GLFW.
*/

public class Mouse {

    protected static Mouse INSTANCE;

    public final byte[] bA;

    /**
    * Construct a {@link Mouse}.
    *
    * @param glWindow GLFW Window.
    */

    public Mouse(long glWindow) {
        if(INSTANCE != null)
            throw new IllegalStateException("You can't instantiate another " + this.getClass().getSimpleName() + "!");

        this.bA = new byte[GLFW_MOUSE_BUTTON_LAST];

        //noinspection resource
        glfwSetMouseButtonCallback(glWindow, (_ignored, glKey, glScancode, glAction) -> { switch (glAction) {
            case GLFW_PRESS ->
                this.bA[glKey] = 0x01;
            case GLFW_REPEAT ->
                this.bA[glKey] = (0x01 | 0x02);
            case GLFW_RELEASE ->
                this.bA[glKey] = 0x00;
        }});

        Mouse.INSTANCE = this;
    }

    /**
    * Checks if a Key is released.
    *
    * @param keyCode Keycode.
    *
    * @return true if the Key is released, false otherwise.
    */

    public static boolean isKeyReleased(int keyCode) {
        return getInstance().bA[keyCode] == 0x00;
    }

    /**
    * Checks if a Key is down.
    *
    * @param keyCode Keycode.
    *
    * @return true if the Key is down, false otherwise.
    */

    public static boolean isKeyDown(int keyCode) {
        return (getInstance().bA[keyCode] & 0x01) != 0;
    }

    /**
    * Checks if a Key is pressed.
    *
    * @param keyCode Keycode.
    *
    * @return true if the Key is pressed, false otherwise.
    */

    public static boolean isKeyPressed(int keyCode) {
        return (getInstance().bA[keyCode] & (0x01 | 0x02)) != 0;
    }

    /**
    * @return a {@link Mouse} Instance.
    */

    public static Mouse getInstance() {
        if(INSTANCE == null)
            throw new IllegalStateException("You must need to construct a " + Mouse.class.getSimpleName() + " to get your instance!");

        return INSTANCE;
    }
}