package com.example.desktop.stechno;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {




    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public  void onClick(View v)
            {
                Intent i = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(i);
            }
        });
    }
}
