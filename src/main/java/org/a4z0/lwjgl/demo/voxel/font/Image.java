package org.a4z0.lwjgl.demo.voxel.font;

import org.lwjgl.BufferUtils;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
* ...
*/

public interface Image {

    /**
    * @return ...
    */

    int getID();

    /**
    * @return ...
    */

    int getWidth();

    /**
    * @return ...
    */

    int getHeight();

    /**
    * @param TEXTURE_INPUT_STREAM ...
    *
    * @return ...
    */

    static ByteBuffer getImageData(InputStream TEXTURE_INPUT_STREAM) {
        try(MemoryStack MEMORY_STACK = MemoryStack.stackPush()) {
            IntBuffer WIDTH_BUFFER = MEMORY_STACK.mallocInt(1);
            IntBuffer HEIGHT_BUFFER = MEMORY_STACK.mallocInt(1);
            IntBuffer CHANNELS_BUFFER = MEMORY_STACK.mallocInt(1);

            byte[] FILE_DATA;

            try(InputStream INPUT_STREAM = TEXTURE_INPUT_STREAM) {
                FILE_DATA = INPUT_STREAM.readAllBytes();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            ByteBuffer IMAGE_BUFFER = BufferUtils.createByteBuffer(FILE_DATA.length);
            IMAGE_BUFFER.put(FILE_DATA);
            IMAGE_BUFFER.flip();

            STBImage.stbi_set_flip_vertically_on_load(true);
            ByteBuffer IMAGE_DATA = STBImage.stbi_load_from_memory(IMAGE_BUFFER, WIDTH_BUFFER, HEIGHT_BUFFER, CHANNELS_BUFFER, 0);

            if(IMAGE_DATA == null)
                throw new RuntimeException("Failed to load texture file: " + STBImage.stbi_failure_reason());

            return IMAGE_DATA;
        }
    }
}