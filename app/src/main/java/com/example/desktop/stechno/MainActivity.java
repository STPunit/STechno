package com.example.desktop.stechno;

import android.content.Intent;
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
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;

import java.util.MissingFormatArgumentException;



public class MainActivity  extends AppCompatActivity {



    Button button2, t2, t3;
    ActionBarDrawerToggle actionBarDrawerToggle;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
   //Toolbar toolbar;
    android.support.v7.widget.Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseMessaging.getInstance().subscribeToTopic("pushNotifications");
        setContentView(R.layout.activity_main);
        tbr();
        button2 = findViewById(R.id.button2);
        t2 = findViewById(R.id.t2);
        t3 = findViewById(R.id.t3);
//        navigationView = findViewById(R.id.navview);
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                Toast.makeText(MainActivity.this," skdjasl", Toast.LENGTH_SHORT).show();
//                switch (menuItem.getItemId()){
//
//                    case R.id.addTask:
//                        Toast.makeText(MainActivity.this, "add task", Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.viewTask:
//                        Toast.makeText(MainActivity.this,"view", Toast.LENGTH_SHORT).show();
//                        break;
//                }
//                return false;
//            }
//        });
      //  toolbar = findViewById(R.id.tbr);
      //  navigationView.setNavigationItemSelectedListener(this);
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected( MenuItem menuItem) {
//                if (actionBarDrawerToggle.onOptionsItemSelected(menuItem))
//                switch (menuItem.getItemId()){
//                    case R.id.addTask:
//                        startActivity(new Intent(MainActivity.this,Main2Activity.class));
//                        menuItem.setChecked(true);
//                        break;
//                       // return true;
//                    case R.id.viewTask:
//                        startActivity(new Intent(MainActivity.this, Main7Activity.class));
//                        menuItem.setChecked(true);
//                        break;
////                        default:
//                           // return MainActivity.super.onOptionsItemSelected(menuItem);
//                }
//
//            return true;
//              //  return MainActivity.super.onOptionsItemSelected(menuItem);
//            }
//
//        });


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
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(MainActivity.this, Main3Activity.class);
                startActivity(i1);
                ;
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
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (actionBarDrawerToggle.onOptionsItemSelected(item))
//            return true;
//
//
//        return super.onOptionsItemSelected(item);
//    }


//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//        if (actionBarDrawerToggle.onOptionsItemSelected(menuItem)){
//            switch (menuItem.getItemId()){
//
//                case R.id.addTask:
//                        startActivity(new Intent(MainActivity.this,Main2Activity.class));
//                        menuItem.setChecked(true);
//                        break;
//                       // return true;
//                    case R.id.viewTask:
//                        startActivity(new Intent(MainActivity.this, Main7Activity.class));
//                        menuItem.setChecked(true);
//                        break;
////                        default:
//
//
//            }
//        }
//        drawerLayout.closeDrawer(GravityCompat.START);
//        return true;
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        getMenuInflater().inflate(R.menu.nav_menu, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        Toast.makeText(MainActivity.this,"clicked", Toast.LENGTH_SHORT).show();
//        if (actionBarDrawerToggle.onOptionsItemSelected(item)){
//
//            switch (item.getItemId()){
//                case R.id.addTask:
//                    startActivity(new Intent(MainActivity.this, Main2Activity.class));
//                    Toast.makeText(MainActivity.this, item.getItemId(), Toast.LENGTH_SHORT).show();
//                    return true;
//                case R.id.viewTask:
//                    startActivity(new Intent(MainActivity.this, Main2Activity.class));
//                    Toast.makeText(MainActivity.this, item.getItemId(), Toast.LENGTH_SHORT).show();
//                    return true;
//                    default:
//                        return super.onOptionsItemSelected(item);
//
//            }
//        }
//        return true;
//
//    }

    //
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//        Toast.makeText(MainActivity.this,"clicked", Toast.LENGTH_LONG).show();
//        if (actionBarDrawerToggle.onOptionsItemSelected(menuItem)){
//            switch (menuItem.getItemId()){
//                case R.id.addTask:
//                    startActivity(new Intent(MainActivity.this, Main2Activity.class));
//                case R.id.viewTask:
//                    startActivity(new Intent(MainActivity.this, Main2Activity.class));
//
//            }
//
//        }
//        return super.onOptionsItemSelected(menuItem);
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (actionBarDrawerToggle.onOptionsItemSelected(item)){
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
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
    }