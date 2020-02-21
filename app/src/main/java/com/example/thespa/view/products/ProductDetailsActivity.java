package com.example.thespa.view.products;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.bumptech.glide.Glide;
import com.example.domain.entities.Product;
import com.example.domain.request_entities.WithId;
import com.example.domain.usecases.DefaultObserver;
import com.example.domain.usecases.product_usecases.GetProductDetailsUseCase;
import com.example.thespa.R;
import com.example.thespa.databinding.ActivityProductDetailsBinding;
import com.example.thespa.dependencies.AndroidApplication;
import com.example.thespa.dependencies.components.ProductComponent;
import com.example.thespa.preferences.LanguageManager;

import javax.inject.Inject;

public class ProductDetailsActivity extends AppCompatActivity {


    ActivityProductDetailsBinding activityProductDetailsBinding;
    Toolbar toolbar;
    @Inject
    GetProductDetailsUseCase getProductDetailsUseCase;
    ProductComponent productComponent;
    String lang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initializeInjector();
        super.onCreate(savedInstanceState);
        activityProductDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_product_details);
        activityProductDetailsBinding.setActivity(this);

        setSupportActionBar(activityProductDetailsBinding.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        productComponent.inject(this);
        lang = LanguageManager.returnLanguage();
        getProductDetailsUseCase.execute(new getProductDetailsObserver(),new WithId(lang,getIntent().getStringExtra("product_id")));
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

    private final class getProductDetailsObserver extends DefaultObserver<Product> {
        @Override
        public void onComplete() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(Product product) {
            activityProductDetailsBinding.setModel(product);
            String url = "https://appthespa.com/"+product.getImage();
            Glide.with(getBaseContext())
                    .load(url)
                    .into(activityProductDetailsBinding.image);

        }
    }
    private void initializeInjector() {
        this.productComponent = ((AndroidApplication) getApplicationContext())
                .getApplicationComponent().productComponent().create();
    }
}
