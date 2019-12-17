package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.example.myapplication.R;

public class SplashScreen extends AppCompatActivity {
    private static final String VIEW_NAME_HEADER_IMAGE = "detail:header:image";
    private static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                newActivity();
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    public void newActivity() {
        Intent intent = new Intent(SplashScreen.this, LoginPage.class);
        startActivity(intent);

    }
}