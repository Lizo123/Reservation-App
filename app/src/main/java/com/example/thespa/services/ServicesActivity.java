package com.example.thespa.services;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.thespa.R;
import com.example.thespa.carts.CartActivity;
import com.example.thespa.massage.MassageActivity;

public class ServicesActivity extends AppCompatActivity {

    Toolbar toolbar;
    ImageView cart;
    LinearLayout massage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        toolbar = findViewById(R.id.toolbar);
        cart = findViewById(R.id.cart);
        setSupportActionBar(toolbar);
        massage= findViewById(R.id.massage);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCartActivity();
            }
        });
        massage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoToDetails();
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

    public void GoToDetails()
    {
        Intent i = new Intent(this, MassageActivity.class);
        startActivity(i);
    }

}
