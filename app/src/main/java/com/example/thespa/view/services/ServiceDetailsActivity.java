package com.example.thespa.view.services;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.domain.entities.Service;
import com.example.thespa.R;
import com.example.thespa.databinding.ActivityServiceDetailsBinding;
import com.example.thespa.view.carts.CartActivity;

public class ServiceDetailsActivity extends AppCompatActivity {

    ActivityServiceDetailsBinding activityServiceDetailsBinding;
    Service service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityServiceDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_service_details);
        activityServiceDetailsBinding.setActivity(this);
        service = getIntent().getParcelableExtra("service");
        activityServiceDetailsBinding.setService(service);
        String url = "https://appthespa.com/"+service.getImage();
        Glide.with(this)
                .load(url)
                .into(activityServiceDetailsBinding.image);
        setSupportActionBar(activityServiceDetailsBinding.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }


}
