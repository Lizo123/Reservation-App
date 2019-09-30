package com.example.thespa.Offers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.thespa.R;
import com.example.thespa.models.Offer;

import java.util.ArrayList;

public class OffersActivity extends AppCompatActivity {

    OffersAdapter offersAdapter;
    ArrayList<Offer> offerModels;
    Offer offer;
    Offer offer1;
    Offer offer2;
    RecyclerView offers_recycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers);
        offers_recycler = this.findViewById(R.id.offers_recycler);
        offerModels = new ArrayList<>();
        offer = new Offer("Spa Escape","10% Offer");
        offer1 =  new Offer("Spa Escape","10% Offer");
        offer2 =  new Offer("Spa Escape","10% Offer");
        offerModels.add(offer);
        offerModels.add(offer1);
        offerModels.add(offer2);

        offersAdapter = new OffersAdapter(this,offerModels);
        offers_recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        offers_recycler.setAdapter(offersAdapter);
    }
}
