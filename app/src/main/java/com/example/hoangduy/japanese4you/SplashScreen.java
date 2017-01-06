package com.example.hoangduy.japanese4you;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_splash_screen)
public class SplashScreen extends AppCompatActivity {
    private Handler mHandler;
    private static final int SECOND=3000;
    @AfterViews
    public void init() {
        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this,Tutorials_.class);
                startActivity(intent);
                finish();
            }
        },SECOND);
    }
}
