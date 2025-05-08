package com.example.application.lab2;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import com.example.application.R;
import com.example.application.RawResourceReader;

public class TextureLightingRenderer implements GLSurfaceView.Renderer {
    private final Context ctx;
    private int program;
    private int texId;
    private final TexturedPyramid pyramid;
    private int uEffectLoc;
    private int effect = 0;
    private final Camera camera;

    private final float[] ambient = {0.3f, 0.3f, 0.3f, 1f};
    private final float[] lightColor = {1f, 1f, 1f, 1f};
    private final float[] lightDir = {0f, -1f, -1f};

    private float angle;
    private float axisX = 0f, axisY = 1f, axisZ = 0f;

    private final float[] M = new float[16];
    private final float[] V = new float[16];
    private final float[] P = new float[16];
    private final float[] MV = new float[16];
    private final float[] MVP = new float[16];

    public TextureLightingRenderer(Context context) {
        ctx = context;
        pyramid = new TexturedPyramid();
        camera = new Camera();
    }

    public void setEffect(int e) { effect = e; }
    public void setAxis(float x, float y, float z) { axisX = x; axisY = y; axisZ = z; }
    public void moveCamera(Camera.Direction dir) { camera.processMovement(dir); }

    @Override
    public void onSurfaceCreated(javax.microedition.khronos.opengles.GL10 gl, javax.microedition.khronos.egl.EGLConfig config) {
        GLES20.glClearColor(0f, 0f, 0f, 1f);
        String vs = RawResourceReader.read(ctx, R.raw.vertex_shader_lab2);
        String fs = RawResourceReader.read(ctx, R.raw.fragment_shader_lab2);
        program = ShaderUtils.createProgram(vs, fs);
        texId = TextureUtils.loadTexture(ctx, R.drawable.texture_image);
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        uEffectLoc = GLES20.glGetUniformLocation(program, "u_Effect");
    }

    @Override
    public void onSurfaceChanged(javax.microedition.khronos.opengles.GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        float ratio = (float) width / height;
        Matrix.perspectiveM(P, 0, 60, ratio, 1f, 100f);
    }

    @Override
    public void onDrawFrame(javax.microedition.khronos.opengles.GL10 gl) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        float[] view = camera.getViewMatrix();
        Matrix.setIdentityM(M, 0);
        Matrix.rotateM(M, 0, angle, axisX, axisY, axisZ);
        angle += 1f;
        Matrix.multiplyMM(MV, 0, view, 0, M, 0);
        Matrix.multiplyMM(MVP, 0, P, 0, MV, 0);

        GLES20.glUseProgram(program);
        GLES20.glUniform1i(uEffectLoc, effect);
        GLES20.glUniformMatrix4fv(GLES20.glGetUniformLocation(program, "u_MVPMatrix"), 1, false, MVP, 0);
        GLES20.glUniformMatrix4fv(GLES20.glGetUniformLocation(program, "u_MVMatrix"), 1, false, MV, 0);
        GLES20.glUniform3fv(GLES20.glGetUniformLocation(program, "u_LightDirection"), 1, lightDir, 0);
        GLES20.glUniform4fv(GLES20.glGetUniformLocation(program, "u_AmbientColor"), 1, ambient, 0);
        GLES20.glUniform4fv(GLES20.glGetUniformLocation(program, "u_LightColor"), 1, lightColor, 0);

        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texId);
        GLES20.glUniform1i(GLES20.glGetUniformLocation(program, "u_Texture"), 0);

        int aPos = GLES20.glGetAttribLocation(program, "a_Position");
        int aNorm = GLES20.glGetAttribLocation(program, "a_Normal");
        int aTex = GLES20.glGetAttribLocation(program, "a_TexCoordinate");
        pyramid.draw(aPos, aNorm, aTex);
    }
}
