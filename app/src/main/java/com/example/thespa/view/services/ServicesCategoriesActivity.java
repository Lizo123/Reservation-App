package com.example.thespa.view.services;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.domain.entities.Beautician;
import com.example.domain.entities.Response;
import com.example.domain.entities.Service;
import com.example.domain.entities.ServiceCategory;
import com.example.domain.usecases.DefaultObserver;
import com.example.domain.usecases.service_usecases.GetServiceCategoriesUseCase;
import com.example.thespa.R;
import com.example.thespa.databinding.ActivityServicesCategoriesBinding;
import com.example.thespa.dependencies.components.ServiceComponent;
import com.example.thespa.dependencies.AndroidApplication;
import com.example.thespa.preferences.LanguageManager;
import com.example.thespa.view.BaseActivity;
import com.example.thespa.view.carts.CartActivity;


import java.util.ArrayList;

import javax.inject.Inject;

public class  ServicesCategoriesActivity extends BaseActivity {

    ActivityServicesCategoriesBinding activityServicesBinding;

    ImageView cartBar;
    RecyclerView recyclerView;
    ServiceComponent serviceComponent;
    @Inject
    GetServiceCategoriesUseCase getServiceCategoriesUseCase;
    ServicesCategoriesAdapter servicesAdapter;
    String lang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initializeInjector();
        super.onCreate(savedInstanceState);
        serviceComponent.inject(this);
        activityServicesBinding = DataBindingUtil.setContentView(this, R.layout.activity_services_categories);
        activityServicesBinding.setActivity(this);
        setSupportActionBar(activityServicesBinding.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        lang = LanguageManager.returnLanguage();
        getServiceCategoriesUseCase.execute(new getServicesCategoriesObserver(),lang);
        cartBar = activityServicesBinding.cart;
        recyclerView = activityServicesBinding.recycler;


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

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }

    public void startCartActivity()
    {
        Intent i = new Intent(this, CartActivity.class);
        startActivity(i);
    }


    private void initializeInjector() {
        this.serviceComponent = ((AndroidApplication) getApplicationContext())
                .getApplicationComponent().serviceComponent().create();
    }
    private final class getServicesCategoriesObserver extends DefaultObserver<Response> {
        @Override
        public void onComplete() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(Response response) {
            hideProgressBar();
            if(response.error.toString().equals("get_services_categories_successfully")) {
                ArrayList<ServiceCategory> serviceCategories = (ArrayList<ServiceCategory>) response.success;
                if(serviceCategories.size()>0) {
                    activityServicesBinding.recycler.setVisibility(View.VISIBLE);
            servicesAdapter = new ServicesCategoriesAdapter(getBaseContext(), serviceCategories);
            recyclerView.setLayoutManager(new GridLayoutManager(getBaseContext()
                    , 2));
            recyclerView.setAdapter(servicesAdapter);
                }
                else
                {
                    activityServicesBinding.empty.setVisibility(View.VISIBLE);
                }
            }
        }
    }
    private void showProgressBar()
    {
        activityServicesBinding.progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar()
    {
        activityServicesBinding.progressBar.setVisibility(View.GONE);
    }
}
