package com.example.ivan.tabletest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.ivan.tabletest.Login_Register.LoginActivity;
import com.google.firebase.database.FirebaseDatabase;

public class WelcomeActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 2000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent splashIntent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(splashIntent);
                finish();
            }

        }, SPLASH_TIME_OUT);


        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
