package com.example.thespa.view.beauticians;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.MenuItem;
import android.view.View;

import com.example.domain.entities.Beautician;
import com.example.domain.entities.Response;
import com.example.domain.usecases.DefaultObserver;
import com.example.domain.usecases.beautician_usescases.GetBeauticiansUseCase;
import com.example.thespa.R;
import com.example.thespa.dependencies.AndroidApplication;
import com.example.thespa.dependencies.components.BeauticianComponent;
import com.example.thespa.preferences.LanguageManager;
import com.example.thespa.view.BaseActivity;
import com.example.thespa.view.carts.CartActivity;
import com.example.thespa.databinding.ActivityBeauticiansBinding;

import java.util.ArrayList;

import javax.inject.Inject;

public class BeauticiansActivity extends BaseActivity {

    ActivityBeauticiansBinding activityBeauticiansBinding;

    BeauticianComponent beauticianComponent;
    BeauticiansAdapter beauticiansAdapter;
    @Inject
    GetBeauticiansUseCase getBeauticiansUseCase;
    String lang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initializeInjector();
        super.onCreate(savedInstanceState);
        beauticianComponent.inject(this);
        activityBeauticiansBinding = DataBindingUtil.setContentView(this, R.layout.activity_beauticians);
        activityBeauticiansBinding.setActivity(this);
        setSupportActionBar(activityBeauticiansBinding.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        lang = LanguageManager.returnLanguage();
        getBeauticiansUseCase.execute(new getAllBeauticiansObserver(),lang);

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

    private void initializeInjector() {
        this.beauticianComponent = ((AndroidApplication) getApplicationContext())
                .getApplicationComponent().beauticianComponent().create();
    }

    private final class getAllBeauticiansObserver extends DefaultObserver<Response> {
        @Override
        public void onComplete() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(Response response) {
            hideProgressBar();
            if(response.error.toString().equals("get_specialists_successfully")) {
                ArrayList<Beautician> beauticians = (ArrayList<Beautician>) response.success;
                if(beauticians.size()>0) {
                    activityBeauticiansBinding.recycler.setVisibility(View.VISIBLE);
                    beauticiansAdapter = new BeauticiansAdapter(getBaseContext(), beauticians);
                    activityBeauticiansBinding.recycler.setLayoutManager(new GridLayoutManager(getBaseContext()
                            , 2));
                    activityBeauticiansBinding.recycler.setAdapter(beauticiansAdapter);
                }
                else
                {
                    activityBeauticiansBinding.empty.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    private void showProgressBar()
    {
        activityBeauticiansBinding.progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar()
    {
        activityBeauticiansBinding.progressBar.setVisibility(View.GONE);
    }
}
