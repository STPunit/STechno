package com.example.desktop.stechno;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
public class Main4Activity extends Main3Activity {


DatabaseReference databaseReference;
ArrayList<Profile> list;
Comadp comadp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);


        final RecyclerView Taskcom = findViewById(R.id.Taskcom);


        databaseReference = FirebaseDatabase.getInstance().getReference().child("New Task");
        Query query = databaseReference.orderByChild("TaskStatus").equalTo("In_Progress");
//        Query query1 = databaseReference.orderByChild("TaskStatus").equalTo("PENDING");
//        Query query2 = databaseReference.orderByChild("TaskStatus").equalTo("In_Progress");


        Taskcom.setLayoutManager(new LinearLayoutManager(this));
        list = new  ArrayList<Profile>();
//        if (Taskcom.isSelected()) {
//
//             query = databaseReference.orderByChild("TaskStatus").equalTo("COMPLETED");
//
//        }
//        else if (TaskPen.isSelected()){
//             query = databaseReference.orderByChild("TaskStatus").equalTo("PENDING");
//        }
//        else {
//             query = databaseReference.orderByChild("TaskStatus").equalTo("In_Progress");
//        }
        databaseReference.orderByChild("taskStatus").equalTo("COMPLETED").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Profile pp = dataSnapshot.getValue(Profile.class);
                list.add(pp);
                comadp = new Comadp(Main4Activity.this,list);
                Taskcom.setAdapter(comadp);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


//        ChildEventListener childEventListener = new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                    Profile p = dataSnapshot.getValue(Profile.class);
//                    list.add(p);
//                comadp = new Comadp(Main4Activity.this, list);
//                Taskcom.setAdapter(comadp);
//
//            }
//
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        };
//        query.addChildEventListener(childEventListener);


    }
}
