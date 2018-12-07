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
        Intent intent= getIntent();
        String id = intent.getStringExtra("id");
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
        if (id != null){
            Intent intent1 = new Intent(this, Pintent.class);
            intent.putExtra("id", id );
            startActivity(intent1);
        }

        else {
            Intent intent2 = new Intent(this, MainActivity.class);
            startActivity(intent2);
            finish();
        }
    }
}
