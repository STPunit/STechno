package com.example.desktop.stechno;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Main6Activity extends AppCompatActivity {
    ArrayList<proAdd> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        Intent context = getIntent();
        String id = context.getStringExtra("id");
       // Toast.makeText(Main6Activity.this,id, Toast.LENGTH_LONG).show();
        final TextView TskName = findViewById(R.id.Tskname);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("New Task").child(id);

        list = new ArrayList<>();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String tr  = dataSnapshot.child("TaskName").getValue(String.class);

//                proAdd ppp = dataSnapshot.getValue(proAdd.class);
//                list.add(ppp);
//                 String tr = list.get(0).getTaskId();
                Toast.makeText(Main6Activity.this, tr,Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
//        ref.addListenerForSingleValueEvent(new ValueEventListener() {
//
//
//
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//
//                proAdd p = dataSnapshot.getValue(proAdd.class);
//                list.add(p);
//                String ss = list.get(1).getTaskName();
//                Toast.makeText(Main6Activity.this, ss, Toast.LENGTH_LONG).show();
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//
//        });


    }

}
