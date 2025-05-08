package com.example.application;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import com.example.application.lab1.Lab1Activity;
import com.example.application.lab2.Lab2Activity;

public class MainMenuActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Button oldBtn = findViewById(R.id.button_old);
        Button newBtn = findViewById(R.id.button_new);

        oldBtn.setOnClickListener(v -> startActivity(new Intent(MainMenuActivity.this,
                Lab1Activity.class)));

        newBtn.setOnClickListener(v -> startActivity(new Intent(MainMenuActivity.this,
                Lab2Activity.class)));
    }
}