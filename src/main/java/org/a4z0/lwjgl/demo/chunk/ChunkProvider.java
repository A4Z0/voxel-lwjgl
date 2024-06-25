package org.a4z0.lwjgl.demo.chunk;

import org.a4z0.lwjgl.demo.entity.EntityPlayer;
import org.a4z0.lwjgl.demo.level.Level;
import org.a4z0.lwjgl.demo.level.Location;
import org.a4z0.lwjgl.demo.server.Server;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ChunkProvider {

    protected final Server server;
    protected final Level level;

    protected final Map<Long, Chunk> cache = new HashMap<>();

    /**
    * Construct a {@link ChunkProvider}.
    *
    * @param server ...
    * @param level ....
    */

    public ChunkProvider(Server server, Level level) {
        this.server = server;
        this.level = level;
    }

    /**
    * @return the {@link Server}.
    */

    public Server getServer() {
        return this.server;
    }

    /**
    * @return the {@link Level}.
    */

    public Level getLevel() {
        return this.level;
    }

    /**
     *
     * @param x
     * @param y
     * @param z
     *
     * @return
     */

    public boolean exists(int x, int y, int z) {
        return this.exists(new ChunkPosition(x, y, z));
    }

    /**
     *
     * @param position
     *
     * @return
     */

    public boolean exists(ChunkPosition position) {
        return this.cache.containsKey(position.getIndex());
    }

    /**
    * Provides a {@link Chunk}.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return a {@link Chunk}.
    */

    public Chunk provide(int x, int y, int z) {
        return this.provide(new ChunkPosition(x, y, z));
    }

    /**
    * Provides a {@link Chunk}.
    *
    * @param position ...
    *
    * @return a {@link Chunk}.
    */

    public Chunk provide(ChunkPosition position) {
        return this.cache.computeIfAbsent(position.getIndex(), (_ignored) -> new Chunk(this.level, position));
    }

    /**
    * Removes a {@link Chunk}.
    *
    * @param chunk ...
    *
    * @return an {@link Optional} of a {@link Chunk}.
    */

    public Optional<Chunk> remove(Chunk chunk) {
        return this.remove(chunk.getPosition());
    }

    /**
    * Removes a {@link Chunk}.
    *
    * @param x X-Axis.
    * @param y Y-Axis.
    * @param z Z-Axis.
    *
    * @return an {@link Optional} of a {@link Chunk}.
    */

    public Optional<Chunk> remove(int x, int y, int z) {
        return this.remove(new ChunkPosition(x, y, z));
    }

    /**
    * Removes a {@link Chunk}.
    *
    * @param position ...
    *
    * @return an {@link Optional} of a {@link Chunk}.
    */

    public Optional<Chunk> remove(ChunkPosition position) {
        return Optional.of(this.cache.remove(position.getIndex()));
    }

    /**
    * Ticks this {@link ChunkProvider}.
    */

    public void tick() {
        for(EntityPlayer player : this.server.getPlayers()) {
            Location playerLoc = player.getLocation().clone();

            for(int x = (int) playerLoc.getX() - 16; x < (int) playerLoc.getX() + 16; x++) {
                for(int y = (int) playerLoc.getY() - 16; y < (int) playerLoc.getY() + 16; y++) {
                    for(int z = (int) playerLoc.getZ() - 16; z < (int) playerLoc.getZ() + 16; z++) {
                        Chunk playerChunk = this.provide(x >> 4, y >> 4, z >> 4);

                        if(!playerChunk.isLoaded())
                            playerChunk.load(true);

                        playerChunk.getLayer().render(true);
                    }
                }
            }
        }

        /*for(Chunk chunk : this.cache.values()) {
            boolean isVisible = false;

            for(EntityPlayer player : this.server.getPlayers()) {
                if(player.getLocation().distance(chunk.getPosition().getX(), chunk.getPosition().getY(), chunk.getPosition().getZ()) < 32) {
                    isVisible = true;
                }
            }

            if(!isVisible) {
                this.remove(chunk).ifPresent(Chunk::unload);
            }
        }*/
    }
}