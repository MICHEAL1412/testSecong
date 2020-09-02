package com.example.test1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    LinearLayout Popular,Coming_soon,Now_showing;

    private DrawerLayout drawer;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    static final String STATE_FRAGMENT = "state_of_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer=findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FaqFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_faq);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new PolicyFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_policy);
        }


        Popular = (LinearLayout)findViewById(R.id.Popular2);
        Coming_soon = (LinearLayout)findViewById(R.id.Now_showing2);
        Now_showing= (LinearLayout)findViewById(R.id.Coming_soon2);

    }


    public void Pop(View view){
        Popular.setVisibility(View.VISIBLE);
        Coming_soon.setVisibility(View.GONE);
        Now_showing.setVisibility(View.GONE);
    }
    public void coming(View view){
        Popular.setVisibility(View.GONE);
        Coming_soon .setVisibility(View.VISIBLE);
        Now_showing.setVisibility(View.GONE);
    }
    public void Now(View view){
        Popular.setVisibility(View.GONE);
        Coming_soon.setVisibility(View.GONE);
        Now_showing.setVisibility(View.VISIBLE);
    }

    public void Rating(View view) {
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, rating.class);
        startActivity(intent);

    }
    public void general(View view) {
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, General.class);
        startActivity(intent);

    }
    public void FB(View view) {
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, food_beverage.class);
        startActivity(intent);

    }
    public void feedback(View view) {
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, Feedback.class);
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
                break;
            case R.id.nav_faq:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FaqFragment()).commit();
                break;
            case R.id.nav_policy:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new PolicyFragment()).commit();
                break;
        }
        return false;
    }



   

}