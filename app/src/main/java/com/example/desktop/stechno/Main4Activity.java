package com.example.desktop.stechno;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Main4Activity extends AppCompatActivity {


DatabaseReference databaseReference;
ArrayList<tasks> list;
Comadp comadp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        final RecyclerView Taskcom = findViewById(R.id.Taskcom);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("New Task");
        Query query = databaseReference.orderByChild("TaskStatus").equalTo("COMPLETED");

        Taskcom.setLayoutManager(new LinearLayoutManager(this));
        list = new  ArrayList<tasks>();

//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
//                    String ss  = dataSnapshot1.getKey();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        }
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                tasks p = dataSnapshot.getValue(tasks.class);
                list.add(p);
                comadp = new Comadp(Main4Activity.this, list);
                Taskcom.setAdapter(comadp);

//                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
//
//                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }; query.addValueEventListener(eventListener);

    }
}
