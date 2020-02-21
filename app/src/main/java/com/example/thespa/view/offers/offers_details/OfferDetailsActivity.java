package com.example.thespa.view.offers.offers_details;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.domain.entities.Offer;
import com.example.thespa.R;
import com.example.thespa.view.carts.CartActivity;
import com.example.thespa.view.offers.OffersAdapter;

import java.util.ArrayList;

public class OfferDetailsActivity extends AppCompatActivity {
    OffersAdapter offersAdapter;
    ArrayList<Offer> offerModels;
    Offer offer;
    Offer offer1;
    Offer offer2;
    Offer offer3;
    RecyclerView offers_recycler;
    Toolbar toolbar;
    TextView next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_details);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        offers_recycler = this.findViewById(R.id.offers_recycler);
        next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCart();
            }
        });
        offerModels = new ArrayList<>();
//        offer = new Offer("","");
//        offer1 =  new Offer("","");
//        offer2 =  new Offer("","");
//        offer3 =  new Offer("","");
//        offerModels.add(offer);
//        offerModels.add(offer1);
//        offerModels.add(offer2);
//        offerModels.add(offer3);
        offersAdapter = new OffersAdapter(this,offerModels);
        offers_recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        offers_recycler.setAdapter(offersAdapter);
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
    public void goToCart()
    {
        Intent i = new Intent(this, CartActivity.class);
        startActivity(i);
    }
}
