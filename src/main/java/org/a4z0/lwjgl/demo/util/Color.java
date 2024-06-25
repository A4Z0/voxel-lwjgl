package org.a4z0.lwjgl.demo.util;

public class Color {

    protected int i;

    /**
    * Construct a {@link Color}.
    *
    * @param r Red.
    * @param g Green.
    * @param b Blue.
    */

    public Color(int r, int g, int b) {
        this.setColor(r, g, b, 255);
    }

    /**
    * Construct a {@link Color}.
    *
    * @param r Red.
    * @param g Green.
    * @param b Blue.
    * @param a Alpha.
    */

    public Color(int r, int g, int b, int a) {
        this.setColor(r, g, b, a);
    }

    /**
    * Construct a {@link Color}.
    *
    * @param r Red.
    * @param g Green.
    * @param b Blue.
    */

    public Color(byte r, byte g, byte b) {
        this.setColor(r, g, b, (byte) 255);
    }

    /**
    * Construct a {@link Color}.
    *
    * @param r Red.
    * @param g Green.
    * @param b Blue.
    * @param a Alpha.
    */

    public Color(byte r, byte g, byte b, byte a) {
        this.setColor(r, g, b, a);
    }

    /**
    * Construct a {@link Color}.
    *
    * @param i Color.
    */

    public Color(int i) {
        this.setColor(i);
    }

    /**
    * Construct a {@link Color}.
    */

    public Color() {
        this(0);
    }

    /**
    * Sets the Red.
    *
    * @param r Red.
    *
    * @return this {@link Color}.
    */

    public Color setRed(int r) {
        return this.setRed((byte) r);
    }

    /**
    * Sets the Red.
    *
    * @param r Red.
    *
    * @return this {@link Color}.
    */

    public Color setRed(byte r) {
        this.i = (this.i & 0xFFFFFF00) | (r & 0xFF);

        return this;
    }

    /**
    * Sets the Green.
    *
    * @param g Green.
    *
    * @return this {@link Color}.
    */

    public Color setGreen(int g) {
        return this.setGreen((byte) g);
    }

    /**
    * Sets the Green.
    *
    * @param g Green.
    *
    * @return this {@link Color}.
    */

    public Color setGreen(byte g) {
        this.i = (this.i & 0xFFFF00FF) | ((g & 0xFF) << 8);

        return this;
    }

    /**
    * Sets the Blue.
    *
    * @param b Blue.
    *
    * @return this {@link Color}.
    */

    public Color setBlue(int b) {
        return this.setBlue((byte) b);
    }

    /**
    * Sets the Blue.
    *
    * @param b Blue.
    *
    * @return this {@link Color}.
    */

    public Color setBlue(byte b) {
        this.i = (this.i & 0xFF00FFFF) | ((b & 0xFF) << 16);

        return this;
    }

    /**
    * Sets the Alpha.
    *
    * @param a Alpha.
    *
    * @return this {@link Color}.
    */

    public Color setAlpha(int a) {
        return this.setAlpha((byte) a);
    }

    /**
    * Sets the Alpha.
    *
    * @param a Alpha.
    *
    * @return this {@link Color}.
    */

    public Color setAlpha(byte a) {
        this.i = (this.i & 0x00FFFFFF) | ((a & 0xFF) << 24);

        return this;
    }

    /**
    * Retrieves the Color.
    *
    * @return the Color as an RGBA.
    */

    public int getColor() {
        return this.i;
    }

    /**
    * Sets the Color.
    *
    * @param i Color.
    *
    * @return this {@link Color}.
    */

    public Color setColor(int i) {
        this.i = i;

        return this;
    }

    /**
    * Sets the Color.
    *
    * @param r Red.
    * @param g Green.
    * @param b Blue.
    *
    * @return this {@link Color}.
    */

    public Color setColor(int r, int g, int b) {
        return this.setColor(r, g, b, 255);
    }

    /**
    * Sets the Color.
    *
    * @param r Red.
    * @param g Green.
    * @param b Blue.
    *
    * @return this {@link Color}.
    */

    public Color setColor(byte r, byte g, byte b) {
        return this.setColor(r, g, b, (byte) 255);
    }

    /**
    * Sets the Color.
    *
    * @param r Red.
    * @param g Green.
    * @param b Blue.
    * @param a Alpha.
    *
    * @return this {@link Color}.
    */

    public Color setColor(int r, int g, int b, int a) {
        return this.setColor((byte) r, (byte) g, (byte) b, (byte) a);
    }

    /**
    * Sets the Color.
    *
    * @param r Red.
    * @param g Green.
    * @param b Blue.
    * @param a Alpha.
    *
    * @return this {@link Color}.
    */

    public Color setColor(byte r, byte g, byte b, byte a) {
        this.i = (r & 0xFF) | ((g & 0xFF) << 8) | ((b & 0xFF) << 16) | ((a & 0xFF) << 24);

        return this;
    }

    /**
    * Checks if this {@link Color} is equals to the given {@link Object}.
    *
    * @param o {@link Object} to be compared.
    *
    * @return true if equal, false otherwise.
    */

    @Override
    public boolean equals(Object o) {
        return (o instanceof Color) && ((Color) o).i == this.i;
    }
}