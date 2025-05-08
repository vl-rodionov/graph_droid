package com.example.application.lab1;
import android.content.Context;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import android.opengl.GLES20;
import com.example.application.R;
import com.example.application.RawResourceReader;

public class Pyramid {
    private FloatBuffer vertexBuffer;
    static final int COORDS_PER_VERTEX = 3;
    static float pyramidCoords[] = {
            0.0f,  1.0f,  0.0f,
            -1.0f, -1.0f,  1.0f,
            1.0f, -1.0f,  1.0f,
            0.0f,  1.0f,  0.0f,
            1.0f, -1.0f,  1.0f,
            0.0f, -1.0f, -1.0f,
            0.0f,  1.0f,  0.0f,
            0.0f, -1.0f, -1.0f,
            -1.0f, -1.0f,  1.0f,
            -1.0f, -1.0f,  1.0f,
            0.0f, -1.0f, -1.0f,
            1.0f, -1.0f,  1.0f
    };
    private final int mProgram;
    private int positionHandle;
    private int colorHandle;
    private int mMVPMatrixHandle;
    private final int vertexCount = pyramidCoords.length / COORDS_PER_VERTEX;
    private final int vertexStride = COORDS_PER_VERTEX * 4;
    public Pyramid(Context context) {
        ByteBuffer bb = ByteBuffer.allocateDirect(pyramidCoords.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(pyramidCoords);
        vertexBuffer.position(0);
        String vertexShaderCode = RawResourceReader.read(context, R.raw.vertex_shader_lab1);
        String fragmentShaderCode = RawResourceReader.read(context, R.raw.fragment_shader_lab1);
        int vertexShader = GLRenderer.loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
        int fragmentShader = GLRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);
        mProgram = GLES20.glCreateProgram();
        GLES20.glAttachShader(mProgram, vertexShader);
        GLES20.glAttachShader(mProgram, fragmentShader);
        GLES20.glLinkProgram(mProgram);
    }

    public void draw(float[] mvpMatrix) {
        GLES20.glUseProgram(mProgram);
        positionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
        GLES20.glEnableVertexAttribArray(positionHandle);
        GLES20.glVertexAttribPointer(positionHandle, COORDS_PER_VERTEX, GLES20.GL_FLOAT, false, vertexStride, vertexBuffer);
        mMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix");
        GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mvpMatrix, 0);
        colorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");
        float faceColor[] = {0f, 0f, 0f, 1f};
        GLES20.glUniform4fv(colorHandle, 1, faceColor, 0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);
        float edgeColor[] = {1f, 1f, 1f, 1f};
        GLES20.glUniform4fv(colorHandle, 1, edgeColor, 0);
        GLES20.glLineWidth(3f);
        for (int i = 0; i < 4; i++) {
            GLES20.glDrawArrays(GLES20.GL_LINE_LOOP, i * 3, 3);
        }
        GLES20.glDisableVertexAttribArray(positionHandle);
    }
}
