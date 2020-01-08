package com.sandesh.hamrobazar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = getSharedPreferences("welcome",MODE_PRIVATE);
                String Ad = sharedPreferences.getString("Ad","");
                String Terms = sharedPreferences.getString("Terms","");
                String Safety = sharedPreferences.getString("Safety","");

                if ((Terms.equals("checked")) && (Safety.equals("checked")) && (Ad.equals("checked"))){
                    Intent intent = new Intent(SplashScreen.this,DashboardActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Intent intent = new Intent(SplashScreen.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        },2000);
    }
}
