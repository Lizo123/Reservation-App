package com.example.thespa.home_activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.example.thespa.Offers.CardFragmentPagerAdapter;
import com.example.thespa.Offers.OffersActivity;
import com.example.thespa.Offers.ShadowTransformer;
import com.example.thespa.R;
import com.example.thespa.beauticians_activity.BeauticiansActivity;
import com.example.thespa.cart_activity.CartActivity;
import com.example.thespa.databinding.ActivityHomeBinding;
import com.example.thespa.models.Offer;
import com.example.thespa.products_activity.ProductsActivity;
import com.example.thespa.services_activity.ServicesActivity;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ArrayList<Offer> offerModels;


    CardFragmentPagerAdapter pagerAdapter;
    ShadowTransformer fragmentCardShadowTransformer;
    ActivityHomeBinding activityHomeBinding;
    Toolbar toolbar;
    DrawerLayout drawer;
    ViewPager viewPager;
    Offer offer;
    Offer offer1;
    Offer offer2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        toolbar = activityHomeBinding.toolbar;
        viewPager = activityHomeBinding.viewpager;
        offerModels = new ArrayList<>();
        activityHomeBinding.setActivity(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //toolbar.setTitle("The spa");



        drawer = activityHomeBinding.drawerLayout;

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        activityHomeBinding.navView.setNavigationItemSelectedListener(this);
        offer = new Offer("Spa Escape","10% Offer");
        offer1 =  new Offer("Spa Escape","10% Offer");
        offer2 =  new Offer("Spa Escape","10% Offer");;
        offerModels.add(offer);
        offerModels.add(offer1);
        offerModels.add(offer2);
        setRecycler(offerModels);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_services) {
            start_Activity(new ServicesActivity());
        } else if (id == R.id.nav_products) {
            start_Activity(new ProductsActivity());

        } else if (id == R.id.nav_offers) {
            start_Activity(new OffersActivity());

        } else if (id == R.id.nav_beauticians) {
            start_Activity(new BeauticiansActivity());

        } else if (id == R.id.nav_cart) {
            start_Activity(new CartActivity());
        }
        else if(id== R.id.nav_appointments)
        {}
        else if(id == R.id.nav_favorite)
        {}

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void setRecycler(ArrayList<Offer> offerModels) {
        pagerAdapter = new CardFragmentPagerAdapter(getSupportFragmentManager(), dpToPixels(2, this),offerModels);
        fragmentCardShadowTransformer = new ShadowTransformer(viewPager, pagerAdapter);
        fragmentCardShadowTransformer.enableScaling(true);

        viewPager.setAdapter(pagerAdapter);
        viewPager.setPageTransformer(false, fragmentCardShadowTransformer);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setClipToPadding(false);

        viewPager.setPageMargin(50);

    }

    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }
    public void StartServices()
    {
        Intent i = new Intent(this,ServicesActivity.class);
        startActivity(i);
    }

    public void start_Activity(Activity activity)
    {
        Intent i = new Intent(this,activity.getClass());
        startActivity(i);
    }
}
