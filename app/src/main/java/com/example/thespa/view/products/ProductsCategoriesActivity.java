package com.example.thespa.view.products;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.example.domain.entities.Beautician;
import com.example.domain.entities.ProductCategory;
import com.example.domain.entities.Response;
import com.example.domain.usecases.DefaultObserver;
import com.example.domain.usecases.product_usecases.GetProductCategoriesUseCases;
import com.example.thespa.R;

import com.example.thespa.databinding.ActivityProductsCategoriesBinding;
import com.example.thespa.dependencies.AndroidApplication;
import com.example.thespa.dependencies.components.ProductComponent;
import com.example.thespa.preferences.LanguageManager;
import com.example.thespa.view.BaseActivity;

import java.util.ArrayList;

import javax.inject.Inject;

public class ProductsCategoriesActivity extends BaseActivity {

    ActivityProductsCategoriesBinding activityProductsCategoriesBinding;
    ProductCategoriesAdapter productAdapter;

    RecyclerView recyclerView;
    @Inject
    GetProductCategoriesUseCases getProductCategoriesUseCases;
    ProductComponent productComponent;
    String lang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initializeInjector();
        super.onCreate(savedInstanceState);
        activityProductsCategoriesBinding = DataBindingUtil.setContentView(this, R.layout.activity_products_categories);
        setSupportActionBar(activityProductsCategoriesBinding.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        productComponent.inject(this);
        lang = LanguageManager.returnLanguage();
       getProductCategoriesUseCases.execute(new getProductCategoriesObserver(),lang);

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

    private final class getProductCategoriesObserver extends DefaultObserver<Response> {
        @Override
        public void onComplete() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(Response response) {
            hideProgressBar();
            if(response.error.toString().equals("get_products_categories_successfully")) {
                ArrayList<ProductCategory> productCategories = (ArrayList<ProductCategory>) response.success;
                if(productCategories.size()>0) {
                    activityProductsCategoriesBinding.recycler.setVisibility(View.VISIBLE);
            productAdapter = new ProductCategoriesAdapter(getBaseContext(), productCategories,"");
            activityProductsCategoriesBinding.recycler.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false));
            activityProductsCategoriesBinding.recycler.setAdapter(productAdapter);
                }
                else
                {
                    activityProductsCategoriesBinding.empty.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    private void initializeInjector() {
        this.productComponent = ((AndroidApplication) getApplicationContext())
                .getApplicationComponent().productComponent().create();
    }
    private void showProgressBar()
    {
        activityProductsCategoriesBinding.progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar()
    {
        activityProductsCategoriesBinding.progressBar.setVisibility(View.GONE);
    }
}
