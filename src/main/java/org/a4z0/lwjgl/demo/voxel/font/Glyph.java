package org.a4z0.lwjgl.demo.voxel.font;

public class Glyph {

    protected final char c;
    protected final int w, h;
    protected final int x, y;

    /**
    * Construct a {@link Glyph}.
    *
    * @param c Char.
    * @param w Width.
    * @param h Height.
    * @param x X-Axis.
    * @param y Y-Axis.
    */

    public Glyph(char c, int w, int h, int x, int y) {
        this.c = c;
        this.w = w;
        this.h = h;
        this.x = x;
        this.y = y;
    }

    /**
    * @return the Char.
    */

    public char getChar() {
        return this.c;
    }

    /**
    * @return the Width.
    */

    public int getWidth() {
        return this.w;
    }

    /**
    * @return the Height.
    */

    public int getHeight() {
        return this.h;
    }

    /**
    * @return the X-Axis.
    */

    public int getX() {
        return this.x;
    }

    /**
    * @return the Y-Axis.
    */

    public int getY() {
        return this.y;
    }
}