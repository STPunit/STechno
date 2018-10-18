package com.example.desktop.stechno;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Main3Activity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;

    TextView TaskCom, TaskPen, TaskPro,comid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

      DatabaseReference tsk = FirebaseDatabase.getInstance().getReference();
      DatabaseReference tsk1 = tsk.child("New Task").getRef();


      TaskCom = findViewById(R.id.TaskCom);
      TaskPen = findViewById(R.id.TaskPen);
      TaskPro = findViewById(R.id.TaskPro);
      comid = findViewById(R.id.comid);
     comid.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent i = new Intent(Main3Activity.this,Main4Activity.class);
             startActivity(i);
         }
     });

        DatabaseReference rootref = tsk.child("New Task");
        Query query = rootref.orderByChild("TaskStatus").equalTo("COMPLETED");
        Query query1 = rootref.orderByChild("TaskStatus").equalTo("PENDING");
        Query query2 = rootref.orderByChild("TaskStatus").equalTo("In_Progress");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                long count = dataSnapshot.getChildrenCount();
                String cm  = Long.toString(count);
                TaskCom.setText(cm);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        query.addListenerForSingleValueEvent(eventListener);


        ValueEventListener eventListener1 = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                long countpo = dataSnapshot.getChildrenCount();
                String stpo = Long.toString(countpo);
                TaskPro.setText(stpo);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        query2.addListenerForSingleValueEvent(eventListener1);


        ValueEventListener eventListener2 = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                long countip = dataSnapshot.getChildrenCount();
                String stip = Long.toString(countip);

                TaskPen.setText(stip);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        query1.addListenerForSingleValueEvent(eventListener2);



    }
}
