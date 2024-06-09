package org.a4z0.lwjgl.demo.voxel.server;

import org.a4z0.lwjgl.demo.voxel.entity.EntityPlayer;
import org.a4z0.lwjgl.demo.voxel.world.server.WorldLoaderServer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Server {

    /** "7", "7 + 7", "7 * 7" */
    public static final int DEFAULT_SERVER_PORT = 71449;
    public static final String DEFAULT_SERVER_IP = "0.0.0.0";

    protected final int p;
    protected final String i;
    protected final String n;
    protected final String v;

    protected final WorldLoaderServer worldLoaderServer;
    protected final List<EntityPlayer> o = new ArrayList<>();

    /**
    * Construct a {@link Server}.
    *
    * @param n ...
    * @param v ...
    */

    public Server(String n, String v) {
        this(DEFAULT_SERVER_IP, DEFAULT_SERVER_PORT, n, v);
    }

    /**
    * Construct a {@link Server}.
    *
    * @param i ...
    * @param p ...
    * @param n ...
    * @param v ...
    */

    public Server(String i, int p, String n, String v) {
        this.i = i;
        this.p = p;
        this.n = n;
        this.v = v;
        this.worldLoaderServer = new WorldLoaderServer(this);
    }

    /**
    * @return {@link Server}'s Port.
    */

    public int getPort() {
        return this.p;
    }

    /**
    * @return {@link Server}'s IP.
    */

    public String getIP() {
        return this.i;
    }

    /**
    * @return {@link Server}'s Name.
    */

    public String getName() {
        return this.n;
    }

    /**
    * @return {@link Server}'s Version.
    */

    public String getVersion() {
        return this.v;
    }

    /**
    * @return ...
    */

    public WorldLoaderServer getWorldLoader() {
        return worldLoaderServer;
    }

    /**
    * ...
    *
    * @return ...
    */

    public Collection<EntityPlayer> getPlayers() {
        return this.o;
    }

    /**
    * ...
    *
    * @param n ...
    *
    * @return ...
    */

    public EntityPlayer getPlayerByName(String n) {
        for(EntityPlayer p : this.o)
            if(p.getName().equals(n))
                return p;

        return null;
    }

    /**
    * Ticks this {@link Server}.
    */

    public abstract void tick();

    /**
    * Starts this {@link Server}.
    */

    public abstract void start();

    /**
    * Reloads this {@link Server}.
    */

    public abstract void reload();

    /**
    * Restarts this {@link Server}.
    */

    public abstract void restart();

    /**
    * Shutdown this {@link Server}.
    */

    public abstract void shutdown();
}