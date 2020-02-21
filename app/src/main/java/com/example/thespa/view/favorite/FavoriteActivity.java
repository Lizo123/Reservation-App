package com.example.thespa.view.favorite;

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

import com.example.domain.entities.Response;
import com.example.domain.entities.Service;
import com.example.domain.usecases.DefaultObserver;
import com.example.domain.usecases.favorite_usecases.GetUserFavorites;
import com.example.thespa.R;
import com.example.thespa.databinding.ActivityFavoriteBinding;
import com.example.thespa.dependencies.AndroidApplication;
import com.example.thespa.dependencies.components.SignComponent;
import com.example.thespa.preferences.UserSession;
import com.example.thespa.view.carts.CartActivity;
import com.example.thespa.view.massage.massageAdapter;
import com.example.thespa.view.models.Cart;
import com.example.thespa.view.services.ServicesAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

public class FavoriteActivity extends AppCompatActivity {

    ActivityFavoriteBinding activityFavoriteBinding;

    SignComponent signComponent;
    @Inject
    UserSession userSession;
    @Inject
    GetUserFavorites getUserFavorites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initializeInjector();
        super.onCreate(savedInstanceState);

        activityFavoriteBinding = DataBindingUtil.setContentView(this,R.layout.activity_favorite);
        signComponent.inject(this);

        setSupportActionBar(activityFavoriteBinding.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        activityFavoriteBinding.cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCartActivity();
            }
        });


//        massageAdapter = new massageAdapter(this,carts);
//        activityFavoriteBinding.cartRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        activityFavoriteBinding.cartRecycler.setAdapter(massageAdapter);

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

        }
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
    private void initializeInjector() {
        this.signComponent = ((AndroidApplication) getApplicationContext())
                .getApplicationComponent().signComponent().create();
    }
    public void startCartActivity()
    {
        Intent i = new Intent(this, CartActivity.class);
        startActivity(i);
    }

}
