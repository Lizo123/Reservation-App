package com.example.thespa.products;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.thespa.R;
import com.example.thespa.models.Brush;

import java.util.ArrayList;

public class ProductOfProductsActivity extends AppCompatActivity {

    BrushAdapter productAdapter;
    ArrayList<Brush> products;
    Brush product;
    Brush product1;
    Brush product2;
    RecyclerView recyclerView;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_of_products);
        toolbar = findViewById(R.id.toolbar);
        recyclerView = this.findViewById(R.id.cart_recycler);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        products = new ArrayList<>();
        product = new Brush("Brushes","Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
        product1 =  new Brush("Lips","Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
        product2 =  new Brush("Eyes","Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
        products.add(product);
        products.add(product1);
        products.add(product2);

        productAdapter = new BrushAdapter(this,products);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(productAdapter);
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
}
