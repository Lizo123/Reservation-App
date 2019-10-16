package com.example.thespa.offers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.thespa.R;
import com.example.thespa.carts.CartActivity;
import com.example.thespa.models.Offer;

import java.util.ArrayList;

public class OffersActivity extends AppCompatActivity {

    OffersAdapter offersAdapter;
    ArrayList<Offer> offerModels;
    Offer offer;
    Offer offer1;
    Offer offer2;
    Offer offer3;
    RecyclerView offers_recycler;
    Toolbar toolbar;
    ImageView cartBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        cartBar = findViewById(R.id.cart);
        offers_recycler = this.findViewById(R.id.offers_recycler);
        offerModels = new ArrayList<>();
        offer = new Offer("Spa Escape","10% Offer");
        offer1 =  new Offer("Spa Escape","10% Offer");
        offer2 =  new Offer("Spa Escape","10% Offer");
        offer3 =  new Offer("Spa Escape","10% Offer");
        offerModels.add(offer);
        offerModels.add(offer1);
        offerModels.add(offer2);
        offerModels.add(offer3);

        offersAdapter = new OffersAdapter(this,offerModels);
        offers_recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        offers_recycler.setAdapter(offersAdapter);
        cartBar.setOnClickListener(new View.OnClickListener() {
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
