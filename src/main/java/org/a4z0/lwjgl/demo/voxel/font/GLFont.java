package org.a4z0.lwjgl.demo.voxel.font;

import org.a4z0.lwjgl.demo.voxel.legacy.done.buffer.DynamicBuffer;
import org.a4z0.lwjgl.demo.voxel.render.shader.pre.VGShaders;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.*;

/**
* ...
*/

public class GLFont {

    public int glTexture;
    public int w, h;

    public final Map<Character, Glyph> Glyphs = new HashMap<>();

    /**
    * Construct a {@link GLFont}.
    *
    * @param URI ...
    * @param Size ...
    */

    protected GLFont(String URI, float Size) throws IOException, FontFormatException {
        this(GLFont.class.getClassLoader().getResourceAsStream(URI), Size);
    }

    /**
    * Construct a {@link GLFont}.
    *
    * @param Stream ...
    * @param Size ...
    */

    protected GLFont(InputStream Stream, float Size) throws FontFormatException, IOException {
        this(Font.createFont(Font.TRUETYPE_FONT, Stream).deriveFont(Size));
    }

    /**
    * Construct a {@link GLFont}.
    */

    protected GLFont(Font Font) {
        this.A(Font);
    }

    /**
    * @return ...
    */

    public int getID() {
        return this.glTexture;
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


    private void A(Font Font) {
        BufferedImage Image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D G2D = Image.createGraphics();

        G2D.setFont(Font);
        FontMetrics Metrics = G2D.getFontMetrics();
        G2D.dispose();

        int X = 0;
        int Y = 0;
        int Width = 0;
        int Height = 0;

        for(int i = 0; i < 256; i++) {
            if(!Font.canDisplay(i))
                continue;

            int CHAR_WIDTH = Metrics.charWidth(i);
            int CHAR_HEIGHT = Metrics.getHeight();

            if(CHAR_WIDTH == 0)
                continue;

            Glyphs.put((char) i, new Glyph((char) i, CHAR_WIDTH, CHAR_HEIGHT, X, (Y + 1) * Height));

            if(X >= Width)
                Width = X;

            X += CHAR_WIDTH;
            Height = Math.max(Height, CHAR_HEIGHT);

            if(Glyphs.size() % 16 == 0) {
                X = 0;
                Y++;
            }
        }

        this.w = Width;
        this.h = Height;

        if(Width == 0 || Height == 0)
            throw new NullPointerException("It isn't possible to create an Image with a width or height equal or less than 0.");

        Image = new BufferedImage(Width, (Y + 1) * Height, BufferedImage.TYPE_INT_ARGB);
        G2D = Image.createGraphics();

        G2D.setFont(Font);
        G2D.setColor(Color.WHITE);
        G2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        for(Glyph glyph : Glyphs.values())
            G2D.drawString(Character.toString(glyph.getChar()), glyph.getX(), glyph.getY());

        G2D.dispose();

        this.B(Image);
    }

    private void B(BufferedImage Image) {
        int[] Pixels = new int[Image.getWidth() * Image.getHeight()];
        Image.getRGB(0, 0, Image.getWidth(), Image.getHeight(), Pixels, 0, Image.getWidth());

        ByteBuffer Buffer = BufferUtils.createByteBuffer(Image.getWidth() * Image.getHeight() * 4);

        for(int y = 0; y < Image.getHeight(); y++){
            for(int x = 0; x < Image.getWidth(); x++){
                int Pixel = Pixels[y * Image.getWidth() + x];

                Buffer.put((byte) ((Pixel >> 16) & 0xFF));
                Buffer.put((byte) ((Pixel >> 8) & 0xFF));
                Buffer.put((byte) ( Pixel & 0xFF));
                Buffer.put((byte) ((Pixel >> 24) & 0xFF));
            }
        }

        Buffer.flip();

        this.glTexture = glGenTextures();

        glBindTexture(GL_TEXTURE_2D, this.glTexture);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);

        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, Image.getWidth(), Image.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, Buffer);
        glBindTexture(GL_TEXTURE_2D, 0);

        try(FileOutputStream out = new FileOutputStream("C:\\Users\\William A. Oliveira\\IdeaProjects\\voxel-lwjgl\\src\\main\\resources\\assets\\font\\minecraft.png")) {
            ImageIO.write(Image, "png", out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
    * Deletes this {@link GLFont}.
    */

    public void delete() {
        glDeleteTextures(this.glTexture);
    }

    /**
    * ...
    *
    * @param x ...
    * @param y ...
    * @param s ...
    */

    public void drawString(float x, float y, String s) {
        final float SIZE = 0.5f;

        DynamicBuffer Buffer = new DynamicBuffer();

        float Width = 0;

        for(char c : s.toCharArray()) {
            Glyph Glyph = this.Glyphs.get(c);

            if(Glyph == null)
                continue;

            float glyphX = Glyph.getX();
            float glyphY = Glyph.getY();
            float glyphWidth = Glyph.getWidth();
            float glyphHeight = Glyph.getHeight();

            float s1 = glyphX / (float) this.w;
            float t1 = glyphY / (float) this.h;
            float s2 = (glyphX + glyphWidth) / (float) this.w;
            float t2 = (glyphY + glyphHeight) / (float) this.h;

            Buffer.put(SIZE + Width + x).put(SIZE + y).put(s2).put(t1);
            Buffer.put(SIZE + Width + x).put(-SIZE + y).put(s2).put(t2);
            Buffer.put(-SIZE + Width + x).put(-SIZE + y).put(s1).put(t2);
            Buffer.put(SIZE + Width + x).put(SIZE + y).put(s2).put(t1);
            Buffer.put(-SIZE + Width + x).put(-SIZE + y).put(s1).put(t2);
            Buffer.put(-SIZE + Width + x).put(SIZE + y).put(s1).put(t1);

            Width += Glyph.getWidth();
        }

        glDisable(GL_DEPTH_TEST);
        VGShaders.TEXT_SHADER_PROGRAM.bind();

        int glVAO = glGenVertexArrays();
        int glVBO = glGenBuffers();

        glBindVertexArray(glVAO);
        glBindBuffer(GL_ARRAY_BUFFER, glVBO);

        glBufferData(GL_ARRAY_BUFFER, Buffer.array(), GL_STATIC_DRAW);

        glEnableVertexAttribArray(0);
        glVertexAttribPointer(0, 2, GL_FLOAT, true, (2 + 2) * Float.BYTES, 0);

        glEnableVertexAttribArray(1);
        glVertexAttribPointer(1, 2, GL_FLOAT, true, (2 + 2) * Float.BYTES, 2 * Float.BYTES);

        glBindBuffer(GL_ARRAY_BUFFER, 0);

        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, this.glTexture);

        VGShaders.TEXT_SHADER_PROGRAM.setUniform4f("text_color", 0f, 0f, 0f, 1f);

        glDrawArrays(GL_TRIANGLES, 0, (Buffer.size() / (2 + 2)));

        glBindVertexArray(0);
        glDeleteVertexArrays(glVAO);
        glDeleteBuffers(glVBO);

        VGShaders.TEXT_SHADER_PROGRAM.unbind();
        glEnable(GL_DEPTH_TEST);
    }

    /**
    * ...
    *
    * @param URI ...
    * @param Size ...
    *
    * @return ...
    */

    public static GLFont create(String URI, int Size) {
        try {
            return new GLFont(URI, Size);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}