package com.example.thespa.view.products;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.domain.entities.Beautician;
import com.example.domain.entities.Product;
import com.example.domain.entities.Response;
import com.example.domain.request_entities.WithId;
import com.example.domain.usecases.DefaultObserver;
import com.example.domain.usecases.product_usecases.GetAllProductsUseCase;
import com.example.thespa.R;
import com.example.thespa.databinding.ActivityProductsBinding;
import com.example.thespa.dependencies.AndroidApplication;
import com.example.thespa.dependencies.components.ProductComponent;
import com.example.thespa.preferences.LanguageManager;

import java.util.ArrayList;

import javax.inject.Inject;

public class ProductsActivity extends AppCompatActivity {

    ActivityProductsBinding activityProductsBinding;
    ProductsAdapter productAdapter;



    ProductComponent productComponent;
    @Inject
    GetAllProductsUseCase getAllProductsUseCase;
    String lang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initializeInjector();
        super.onCreate(savedInstanceState);
        activityProductsBinding =  DataBindingUtil.setContentView(this, R.layout.activity_products);
        setSupportActionBar(activityProductsBinding.toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        productComponent.inject(this);
        lang = LanguageManager.returnLanguage();
        getAllProductsUseCase.execute(new getProductCategoriesObserver(),new WithId(lang,getIntent().getStringExtra("category_id")));


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
            if (response.error.toString().equals("get_products_list_successfully")) {
                ArrayList<Product> products = (ArrayList<Product>) response.success;
                if (products.size() > 0) {
                    activityProductsBinding.recycler.setVisibility(View.VISIBLE);
                    productAdapter = new ProductsAdapter(getBaseContext(), products);
                    activityProductsBinding.recycler.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false));
                    activityProductsBinding.recycler.setAdapter(productAdapter);
                } else {
                    activityProductsBinding.empty.setVisibility(View.VISIBLE);
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
        activityProductsBinding.progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar()
    {
        activityProductsBinding.progressBar.setVisibility(View.GONE);
    }
}
