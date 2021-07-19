package com.example.navigationdraweractivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        setContentView(R.layout.activity_splash);
        getWindow().addFlags(1024);
        nextScreen();
    }

    private void nextScreen() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                SplashActivity.this.startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 4000);

    }
}