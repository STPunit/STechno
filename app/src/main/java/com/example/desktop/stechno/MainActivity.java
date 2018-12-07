package com.example.desktop.stechno;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;




public class MainActivity  extends AppCompatActivity {



    Button button2, t3;
    ActionBarDrawerToggle actionBarDrawerToggle;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    TextView TaskCom, TaskPro, TaskPen;
    FirebaseDatabase firebaseDatabase;
   //Toolbar toolbar;
    android.support.v7.widget.Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseMessaging.getInstance().subscribeToTopic("pushNotifications");
        setContentView(R.layout.activity_main);
        tbr();
        button2 = findViewById(R.id.button2);
        t3 = findViewById(R.id.t3);
        TaskPro = findViewById(R.id.TaskPro);
        TaskPen = findViewById(R.id.TaskPen);
        TaskCom = findViewById(R.id.TaskCom);

        DatabaseReference tsk = FirebaseDatabase.getInstance().getReference();
        DatabaseReference tsk1 = tsk.child("New Task").getRef();


        Query query = tsk1.orderByChild("taskStatus").equalTo("COMPLETED");
        Query query1 = tsk1.orderByChild("taskStatus").equalTo("PENDING");
        Query query2 = tsk1.orderByChild("taskStatus").equalTo("IN PROGRESS");


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(i);
            }
        });
        actionBarDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(MainActivity.this, Main7Activity.class);
                startActivity(i1);
                ;
            }
        });



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
        query.addValueEventListener(eventListener);





//        tsk1.orderByChild("COMPLETED").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                Long countpo = dataSnapshot.getChildrenCount();
//                String stpo = Long.toString(countpo);
//                TaskPro.setText(stpo);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });


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
        query2.addValueEventListener(eventListener1);


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
        query1.addValueEventListener(eventListener2);

    }


    private void tbr(){
        toolbar  = findViewById(R.id.tbr);
        drawerLayout = findViewById(R.id.drawid);
        setSupportActionBar(toolbar);
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      //  getSupportActionBar().setHomeButtonEnabled(true);
        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout,toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView = findViewById(R.id.navview);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){

                    case R.id.addTask:
                        startActivity(new Intent(MainActivity.this, Main2Activity.class));
                      //  Toast.makeText(MainActivity.this, "add task", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.viewTask:
                        startActivity(new Intent(MainActivity.this, Main7Activity.class));
                       // Toast.makeText(MainActivity.this,"view", Toast.LENGTH_SHORT).show();
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });



    }
    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
    }