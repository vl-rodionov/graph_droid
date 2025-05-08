package com.example.application.lab2;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.application.R;

public class Lab2Activity extends AppCompatActivity {
    private TexturedGLSurfaceView glView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab2);

        glView = findViewById(R.id.gl_surface);

        Button fwd = findViewById(R.id.button_forward);
        Button back = findViewById(R.id.button_back);
        Button left = findViewById(R.id.button_left);
        Button right = findViewById(R.id.button_right);
        fwd.setOnClickListener(v -> glView.getRenderer().moveCamera(Camera.Direction.FORWARD));
        back.setOnClickListener(v -> glView.getRenderer().moveCamera(Camera.Direction.BACKWARD));
        left.setOnClickListener(v -> glView.getRenderer().moveCamera(Camera.Direction.LEFT));
        right.setOnClickListener(v -> glView.getRenderer().moveCamera(Camera.Direction.RIGHT));

        findViewById(R.id.button_x).setOnClickListener(v -> glView.getRenderer().setAxis(1, 0, 0));
        findViewById(R.id.button_y).setOnClickListener(v -> glView.getRenderer().setAxis(0, 1, 0));
        findViewById(R.id.button_z).setOnClickListener(v -> glView.getRenderer().setAxis(0, 0, 1));

        findViewById(R.id.back).setOnClickListener(v -> finish());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_effects, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int eff;
        switch (item.getItemId()) {
            case R.id.effect_bw: eff = 1; break;
            case R.id.effect_sepia: eff = 2; break;
            case R.id.effect_invert: eff = 3; break;
            case R.id.effect_bloom: eff = 4; break;
            default: eff = 0;
        }
        glView.getRenderer().setEffect(eff);
        return super.onOptionsItemSelected(item);
    }
}
