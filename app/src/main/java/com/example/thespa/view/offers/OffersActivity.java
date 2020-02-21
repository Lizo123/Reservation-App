package com.example.thespa.view.offers;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.domain.entities.Beautician;
import com.example.domain.entities.Offer;
import com.example.domain.entities.Response;
import com.example.domain.usecases.DefaultObserver;
import com.example.domain.usecases.offerusecases.GetAllOffersUseCase;
import com.example.thespa.R;
import com.example.thespa.databinding.ActivityOffersBinding;
import com.example.thespa.dependencies.AndroidApplication;
import com.example.thespa.dependencies.components.OfferComponent;
import com.example.thespa.preferences.LanguageManager;
import com.example.thespa.view.carts.CartActivity;

import java.util.ArrayList;

import javax.inject.Inject;

public class OffersActivity extends AppCompatActivity {

    OffersAdapter offersAdapter;
    RecyclerView offers_recycler;
    Toolbar toolbar;
    ImageView cartBar;
    OfferComponent offerComponent;
    @Inject
    GetAllOffersUseCase getAllOffersUseCase;
    ActivityOffersBinding activityOffersBinding;
    String lang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initializeInjector();
        super.onCreate(savedInstanceState);
        offerComponent.inject(this);
        activityOffersBinding = DataBindingUtil.setContentView(this, R.layout.activity_offers);


        setSupportActionBar(activityOffersBinding.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        lang = LanguageManager.returnLanguage();
        getAllOffersUseCase.execute(new getAllOffersObserver(), lang);
        cartBar = activityOffersBinding.cart;
        offers_recycler = activityOffersBinding.offersRecycler;


        cartBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCartActivity();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    public void startCartActivity() {
        Intent i = new Intent(this, CartActivity.class);
        startActivity(i);
    }

    private void initializeInjector() {
        this.offerComponent = ((AndroidApplication) getApplicationContext())
                .getApplicationComponent().offerComponent().create();
    }

    private final class getAllOffersObserver extends DefaultObserver<Response> {
        @Override
        public void onComplete() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(Response response) {
            hideProgressBar();
            if(response.error.toString().equals("get_offers_successfully")) {
                ArrayList<Offer> offers = (ArrayList<Offer>) response.success;
                if(offers.size()>0) {
                    activityOffersBinding.offersRecycler.setVisibility(View.VISIBLE);
                    offersAdapter = new OffersAdapter(getBaseContext(), offers);
                    offers_recycler.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false));
                    offers_recycler.setAdapter(offersAdapter);
                }
                else
                {
                    activityOffersBinding.empty.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    private void showProgressBar()
    {
        activityOffersBinding.progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar()
    {
        activityOffersBinding.progressBar.setVisibility(View.GONE);
    }
}

