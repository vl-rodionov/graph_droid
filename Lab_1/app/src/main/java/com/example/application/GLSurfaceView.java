package com.example.application;
import android.content.Context;
import android.util.AttributeSet;
public class GLSurfaceView extends android.opengl.GLSurfaceView {
    private GLRenderer renderer;

    public GLSurfaceView(Context context) {
        super(context);
        init(context);
    }
    public GLSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    private void init(Context context) {
        setEGLContextClientVersion(2);
        renderer = new GLRenderer(context);
        setRenderer(renderer);
        setRenderMode(android.opengl.GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }
    public GLRenderer getRenderer() {
        return renderer;
    }
}
