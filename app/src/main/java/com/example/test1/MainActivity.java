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
    private Handler sliderHandler = new Handler();
    private ViewPager2 viewPager2;

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

        viewPager2= viewPager2.findViewById(R.id.viewPagerImageSlider);
        List<SliderItem> sliderItems=new ArrayList<>();
        sliderItems.add(new SliderItem(R.drawable.digimon_adventure___last_evolution_kizuna));
        sliderItems.add(new SliderItem(R.drawable.my_hero_academia___heros_rising));
        sliderItems.add(new SliderItem(R.drawable.hitman___agent_jun));
        sliderItems.add(new SliderItem(R.drawable.baba_yaga___terror_of_the_dark_forest));
        sliderItems.add(new SliderItem(R.drawable.jumanji2));
        viewPager2.setAdapter(new SliderAdapter(sliderItems,viewPager2));
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer=new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r=1-Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });

        viewPager2.setPageTransformer(compositePageTransformer);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable,3000);
            }
        });


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
    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1);
        }
    };

    @Override
    public void onPause(){
        super.onPause();
        sliderHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    public void onResume(){
        super.onResume();
        sliderHandler.postDelayed(sliderRunnable,3000);
    }



   

}