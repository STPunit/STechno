package com.example.desktop.stechno;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {
    //    super.onCreate(savedInstanceState);
//    Intent intent = new Intent(this,MainActivity.class);
//    startActivity(intent);
//    Finish();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            Intent intent2 = new Intent(this, MainActivity.class);
            startActivity(intent2);
            finish();

    }
}
