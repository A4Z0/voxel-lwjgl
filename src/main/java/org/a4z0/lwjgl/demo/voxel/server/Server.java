package org.a4z0.lwjgl.demo.voxel.server;

/**
* Represents a Server.
*/

public interface Server {

    /**
    * Starts this {@link Server}.
    */

    void start();

    /**
    * Reloads this {@link Server}.
    */

    void reload();

    /**
    * Restarts this {@link Server}.
    */

    void restart();

    /**
    * Shutdown this {@link Server}.
    */

    void shutdown();
}