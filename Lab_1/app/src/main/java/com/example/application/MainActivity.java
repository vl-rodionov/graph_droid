package com.example.application;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class MainActivity extends Activity {
    private GLSurfaceView glSurfaceView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        glSurfaceView = findViewById(R.id.gl_surface_view);
        Button btnRotateX = findViewById(R.id.btnRotateX);
        Button btnRotateY = findViewById(R.id.btnRotateY);
        Button btnRotateZ = findViewById(R.id.btnRotateZ);
        btnRotateX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                glSurfaceView.getRenderer().setRotationAxis(1f, 0f, 0f);
            }
        });
        btnRotateY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                glSurfaceView.getRenderer().setRotationAxis(0f, 1f, 0f);
            }
        });
        btnRotateZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                glSurfaceView.getRenderer().setRotationAxis(0f, 0f, 1f);
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
        glSurfaceView.onPause();
    }
    @Override
    protected void onResume() {
        super.onResume();
        glSurfaceView.onResume();
    }
}
