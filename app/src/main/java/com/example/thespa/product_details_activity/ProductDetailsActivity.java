package com.example.thespa.product_details_activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.thespa.R;
import com.example.thespa.models.Brush;
import com.example.thespa.models.Product;
import com.example.thespa.products_activity.ProductAdapter;

import java.util.ArrayList;

public class ProductDetailsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

    }

}
