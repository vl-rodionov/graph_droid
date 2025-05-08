package com.example.application.lab2;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

public class TexturedGLSurfaceView extends GLSurfaceView {
    private TextureLightingRenderer renderer;

    public TexturedGLSurfaceView(Context ctx) {
        super(ctx);
        init(ctx);
    }
    public TexturedGLSurfaceView(Context ctx, AttributeSet attrs) {
        super(ctx, attrs);
        init(ctx);
    }
    private void init(Context ctx) {
        setEGLContextClientVersion(2);
        renderer = new TextureLightingRenderer(ctx);
        setRenderer(renderer);
        setRenderMode(RENDERMODE_CONTINUOUSLY);
    }

    public TextureLightingRenderer getRenderer() {
        return renderer;
    }
}