package org.a4z0.lwjgl.demo.voxel.gl.window;

import org.a4z0.lwjgl.demo.voxel.gl.input.Input;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;

/**
* Represents a Window.
*/

public class Window {

    protected final long glWindow;

    protected int glWidth;
    protected int glHeight;

    protected int glInterval;

    /**
    * Construct a {@link Window}.
    */

    public Window() {
        this(1920, 1080, "Between Voxels");
    }

    /**
    * Construct a {@link Window}.
    *
    * @param glWidth ...
    * @param glHeight ...
    * @param glTitle ...
    */

    public Window(int glWidth, int glHeight, CharSequence glTitle) {
        this.glWindow = glfwCreateWindow(this.glWidth = glWidth, this.glHeight = glHeight, glTitle, 0, 0);
        this.maximize();

        if(this.glWindow == 0) {
            glfwTerminate();

            throw new RuntimeException("Unable to create a Window!");
        }

        this.bind();
        this.setVsync(true);

        GL.createCapabilities();
    }

    /**
    * @return this {@link Window} ID.
    */

    public long getID() {
        return this.glWindow;
    }

    /**
    * Binds this {@link Window} to the current context.
    */

    public void bind() {
        glfwMakeContextCurrent(this.glWindow);
    }

    /**
    * Unbinds this {@link Window} from the current context.
    */

    public void unbind() {
        glfwMakeContextCurrent(0);
    }

    /**
    * Deletes this {@link Window}.
    */

    public void delete() {
        glfwDestroyWindow(this.glWindow);
    }

    /**
    * ...
    *
    * @param glHint ...
    * @param glValue ...
    */

    public void hint(int glHint, int glValue) {
        glfwWindowHint(glHint, glValue);
    }

    /**
    * ...
    */

    public void update() {
        Input.update();

        glfwPollEvents();
        glfwSwapBuffers(this.glWindow);
    }

    /**
    * Sets the {@link Window} Title.
    *
    * @param glTitle Title of this {@link Window}.
    */

    public void setTitle(CharSequence glTitle) {
        glfwSetWindowTitle(this.glWindow, glTitle);
    }

    /**
    * @return the {@link Window} Width.
    */

    public int getWidth() {
        return this.glWidth;
    }

    /**
    * @return the {@link Window} Height.
    */

    public int getHeight() {
        return this.glHeight;
    }

    /**
    * Maximize this {@link Window}.
    */

    public void maximize() {
        glfwMaximizeWindow(this.glWindow);
    }

    /**
    * ...
    *
    * @param glWidth ...
    * @param glHeight ...
    */

    public void setSize(int glWidth, int glHeight) {
        glfwSetWindowSize(this.glWindow, this.glWidth = glWidth, this.glHeight = glHeight);
    }

    /**
    * ...
    *
    * @param glX ...
    * @param glY ...
    */

    public void setPosition(int glX, int glY) {
        glfwSetWindowPos(this.glWindow, glX, glY);
    }

    /**
    * ...
    *
    * @param glResizable ...
    */

    public void setResizable(boolean glResizable) {
        glfwSetWindowAttrib(this.glWindow, GLFW_RESIZABLE, glResizable ? GLFW_TRUE : GLFW_FALSE);
    }

    /**
    * ...
    *
    * @param b FullScreen?
    */

    public void setFullScreen(boolean b) {
        if (b) {
            long glMonitor = glfwGetPrimaryMonitor();
            GLFWVidMode glVidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            if(glVidMode == null)
                throw new RuntimeException("Unable to get VidMode.");

            glfwSetWindowMonitor(this.glWindow, glMonitor, 0, 0, glVidMode.width(), glVidMode.height(), glVidMode.refreshRate());
        } else {
            glfwSetWindowMonitor(this.glWindow, 0, 0, 0, this.glWidth, this.glHeight, 0);
        }
    }

    /**
    * Sets the {@link Window} Vertical Synchronization.
    *
    * @param b Synchronize?
    */

    public void setVsync(boolean b) {
        glfwSwapInterval(this.glInterval = b ? 1 : 0);
    }


    /**
    * @return ...
    */

    public boolean isClosing() {
        return glfwWindowShouldClose(this.glWindow);
    }
}