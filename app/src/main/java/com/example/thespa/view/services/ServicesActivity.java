package com.example.thespa.view.services;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.domain.entities.Beautician;
import com.example.domain.entities.Response;
import com.example.domain.entities.Service;
import com.example.domain.request_entities.WithId;
import com.example.domain.usecases.DefaultObserver;
import com.example.domain.usecases.favorite_usecases.AddFavoriteUseCase;
import com.example.domain.usecases.favorite_usecases.RemoveFavoriteUseCase;
import com.example.domain.usecases.service_usecases.GetAllServicesUseCase;
import com.example.thespa.R;
import com.example.thespa.databinding.ActivityServicesBinding;
import com.example.thespa.dependencies.AndroidApplication;
import com.example.thespa.dependencies.components.ServiceComponent;
import com.example.thespa.preferences.LanguageManager;
import com.example.thespa.preferences.UserSession;
import com.example.thespa.view.BaseActivity;
import com.example.thespa.view.carts.CartActivity;

import java.util.ArrayList;

import javax.inject.Inject;

public class ServicesActivity extends BaseActivity {

    @Inject
    GetAllServicesUseCase getAllServicesUseCase;
    @Inject
    AddFavoriteUseCase addFavoriteUseCase;
    @Inject
    RemoveFavoriteUseCase removeFavoriteUseCase;

    ActivityServicesBinding activityServicesBinding;

    ServiceComponent serviceComponent;

    ServicesAdapter servicesAdapter;
    String lang;
    @Inject
    UserSession userSession;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initializeInjector();
        super.onCreate(savedInstanceState);
        activityServicesBinding = DataBindingUtil.setContentView(this,R.layout.activity_services);
        serviceComponent.inject(this);
        setSupportActionBar(activityServicesBinding.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        lang = LanguageManager.returnLanguage();
        activityServicesBinding.cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCartActivity();
            }
        });
        getAllServicesUseCase.execute(new getAllServicesObserver(),new WithId(lang,getIntent().getStringExtra("category_id")));
    }

    private void initializeInjector() {
        this.serviceComponent = ((AndroidApplication) getApplicationContext())
                .getApplicationComponent().serviceComponent().create();
    }

    private final class getAllServicesObserver extends DefaultObserver<Response> {
        @Override
        public void onComplete() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(Response response) {

            hideProgressBar();
            if(response.error.toString().equals("get_services_successfully")) {
                ArrayList<Service> services = (ArrayList<Service>) response.success;
                if(services.size()>0) {
                    activityServicesBinding.recycler.setVisibility(View.VISIBLE);
            servicesAdapter = new ServicesAdapter(getBaseContext(), services, addFavoriteUseCase, removeFavoriteUseCase,userSession.isUserLoggedIn());
            activityServicesBinding.recycler.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false));
            activityServicesBinding.recycler.setAdapter(servicesAdapter);
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

}
