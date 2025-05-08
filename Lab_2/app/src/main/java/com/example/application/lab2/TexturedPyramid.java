package com.example.application.lab2;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import android.opengl.GLES20;

public class TexturedPyramid {
    private FloatBuffer buffer;

    private static final float[] DATA = {
            0f,  1f,  0f,   0f,  0.4472f,  0.8944f,   0.5f, 0f,
            -1f, -1f,  1f,  -0.8944f, 0.4472f,  0f,     0f, 1f,
            1f, -1f,  1f,   0.8944f, 0.4472f,  0f,     1f, 1f,

            0f,  1f,  0f,   0.8944f, 0.4472f,  0f,     0.5f, 0f,
            1f, -1f,  1f,   0.8944f, 0.4472f,  0f,     0f, 1f,
            0f, -1f, -1f,   0f,      0.4472f, -0.8944f,1f, 1f,

            0f,  1f,  0f,   0f,      0.4472f, -0.8944f,0.5f, 0f,
            0f, -1f, -1f,  -0.8944f, 0.4472f,  0f,     0f, 1f,
            -1f, -1f,  1f,   0f,      0.4472f,  0.8944f,1f, 1f
    };
    private static final int STRIDE = (3 + 3 + 2) * 4;

    public TexturedPyramid() {
        ByteBuffer bb = ByteBuffer.allocateDirect(DATA.length * 4);
        bb.order(ByteOrder.nativeOrder());
        buffer = bb.asFloatBuffer();
        buffer.put(DATA).position(0);
    }

    public void draw(int aPos, int aNorm, int aTex) {
        buffer.position(0);
        GLES20.glEnableVertexAttribArray(aPos);
        GLES20.glVertexAttribPointer(aPos, 3, GLES20.GL_FLOAT, false, STRIDE, buffer);
        buffer.position(3);
        GLES20.glEnableVertexAttribArray(aNorm);
        GLES20.glVertexAttribPointer(aNorm, 3, GLES20.GL_FLOAT, false, STRIDE, buffer);
        buffer.position(6);
        GLES20.glEnableVertexAttribArray(aTex);
        GLES20.glVertexAttribPointer(aTex, 2, GLES20.GL_FLOAT, false, STRIDE, buffer);
        buffer.position(0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 9);
    }
}
