package com.example.desktop.stechno;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.Toast;

public class Main7Activity extends AppCompatActivity implements BlankFragment1.OnFragmentInteractionListener, BlankFragment2.OnFragmentInteractionListener, BlankFragment3.OnFragmentInteractionListener{

    TableLayout BlankFragment1, BlankFragment2, BlankFragment3;
    ActionBarDrawerToggle actionBarDrawerToggle;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    //Toolbar toolbar;
    android.support.v7.widget.Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        tbr();

        TabLayout tabLayout = findViewById(R.id.tabelayout);
        tabLayout.addTab(tabLayout.newTab().setText("Completed"));
        tabLayout.addTab(tabLayout.newTab().setText("In Progress"));
        tabLayout.addTab(tabLayout.newTab().setText("Pending"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = findViewById(R.id.pager1);
        final PagerAdapter pagerAdapter = new Pageradapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private void tbr(){
        toolbar  = findViewById(R.id.tbr);
        drawerLayout = findViewById(R.id.drawid);
        setSupportActionBar(toolbar);
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //  getSupportActionBar().setHomeButtonEnabled(true);
        actionBarDrawerToggle = new ActionBarDrawerToggle(Main7Activity.this, drawerLayout,toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView = findViewById(R.id.navview);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){

                    case R.id.addTask:
                        startActivity(new Intent(Main7Activity.this, Main2Activity.class));
                        //  Toast.makeText(MainActivity.this, "add task", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.viewTask:
                        //startActivity(new Intent(Main7Activity.this, Main7Activity.class));
                         Toast.makeText(Main7Activity.this,"Already Viewing", Toast.LENGTH_SHORT).show();
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });



    }



}
