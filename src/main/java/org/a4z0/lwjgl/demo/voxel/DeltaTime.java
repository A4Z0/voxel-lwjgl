package org.a4z0.lwjgl.demo.voxel;

/**
* ...
*/

public final class DeltaTime {

    private long lastFrameTimeNano;

    /**
    * Construct a {@link DeltaTime}.
    */

    public DeltaTime() {
        this.lastFrameTimeNano = System.nanoTime();
    }

    /**
    * @return the time elapsed since the last frame in seconds.
    */

    public float getElapsedTime() {
        long currentTimeNano = System.nanoTime();
        return (float) ((currentTimeNano - lastFrameTimeNano) / 1.0E9d);
    }

    /**
    * ...
    */

    public void reset() {
        this.lastFrameTimeNano = System.nanoTime();
    }
}
