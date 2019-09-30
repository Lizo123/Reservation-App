package com.example.thespa.products_activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.thespa.R;
import com.example.thespa.cart_activity.CartAdapter;
import com.example.thespa.models.Cart;
import com.example.thespa.models.Product;

import java.util.ArrayList;

public class ProductsActivity extends AppCompatActivity {

    ProductAdapter productAdapter;
    ArrayList<Product> products;
    Product product;
    Product product1;
    Product product2;
    RecyclerView recyclerView;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        toolbar = findViewById(R.id.toolbar);
        recyclerView = this.findViewById(R.id.cart_recycler);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        products = new ArrayList<>();
        product = new Product("Brushes","ssssssfsrfsfffffffffffffffffffff");
        product1 =  new Product("Lips","ssssssfsrfsfffffffffffffffffffff");
        product2 =  new Product("Eyes","ssssssfsrfsfffffffffffffffffffff");
        products.add(product);
        products.add(product1);
        products.add(product2);

        productAdapter = new ProductAdapter(this,products);
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
