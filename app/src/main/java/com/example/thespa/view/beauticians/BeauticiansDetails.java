package com.example.thespa.view.beauticians;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.domain.entities.Beautician;
import com.example.thespa.R;
import com.example.thespa.databinding.ActivityBeauticiansDetailsBinding;
import com.example.thespa.view.carts.CartActivity;

public class BeauticiansDetails extends AppCompatActivity {


    ActivityBeauticiansDetailsBinding activityBeauticiansDetailsBinding;
    String lang;
    Beautician beautician;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityBeauticiansDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_beauticians_details);
        activityBeauticiansDetailsBinding.setActivity(this);
        beautician = getIntent().getParcelableExtra("beautician");
        activityBeauticiansDetailsBinding.setModel(beautician);
        Glide.with(this)
                .load(beautician.getImage())
                .apply(RequestOptions.circleCropTransform())
                .into(activityBeauticiansDetailsBinding.image);

        setSupportActionBar(activityBeauticiansDetailsBinding.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        activityBeauticiansDetailsBinding.cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCartActivity();
            }
        });
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
    public void startCartActivity()
    {
        Intent i = new Intent(this, CartActivity.class);
        startActivity(i);
    }
}
