package org.a4z0.lwjgl.demo.voxel.legacy.util;

import static org.lwjgl.glfw.GLFW.*;

/**
* Listens to Keyboard Input through GLFW.
*/

public class Keyboard {

    protected static Keyboard INSTANCE;

    public final byte[] bA;

    /**
    * Construct a {@link Keyboard}.
    *
    * @param glWindow GLFW Window.
    */

    public Keyboard(long glWindow) {
        if(INSTANCE != null)
            throw new IllegalStateException("You can't instantiate another " + this.getClass().getSimpleName() + "!");

        this.bA = new byte[GLFW_KEY_LAST];

        //noinspection resource
        glfwSetKeyCallback(glWindow, (_ignored, glKey, glScancode, glAction, glMods) -> { switch (glAction) {
            case GLFW_PRESS ->
                this.bA[glKey] = 0x01;
            case GLFW_REPEAT ->
                this.bA[glKey] = (0x01 | 0x02);
            case GLFW_RELEASE ->
                this.bA[glKey] = 0x00;
        }});

        Keyboard.INSTANCE = this;
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
        return getInstance().bA[keyCode] == (0x01 | 0x02);
    }

    /**
    * Checks if a Key is pressed.
    *
    * @param keyCode Keycode.
    *
    * @return true if the Key is pressed, false otherwise.
    */

    public static boolean isKeyPressed(int keyCode) {
        if(getInstance().bA[keyCode] == 0x01) {
            getInstance().bA[keyCode] = (0x01 | 0x02);

            return true;
        }

        return false;
    }

    /**
    * @return a {@link Keyboard} Instance.
    */

    public static Keyboard getInstance() {
        if(INSTANCE == null)
            throw new IllegalStateException("You must need to construct a " + Keyboard.class.getSimpleName() + " to get your instance!");

        return INSTANCE;
    }
}