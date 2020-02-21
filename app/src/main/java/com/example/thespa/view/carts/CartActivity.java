package com.example.thespa.view.carts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.thespa.R;
import com.example.thespa.view.bookAppointment.BookAppointment;
import com.example.thespa.view.models.Cart;
import com.example.thespa.view.services.ServicesCategoriesActivity;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    CartAdapter cartAdapter;


    RecyclerView recyclerView;
    Toolbar toolbar;
    Button booking_button;
    LinearLayout add_services;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        toolbar = findViewById(R.id.toolbar);
        recyclerView = this.findViewById(R.id.cart_recycler);
        booking_button = findViewById(R.id.booking_button);
        add_services = findViewById(R.id.add_services);
        add_services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addServices();
            }
        });
        booking_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startBooking();
            }
        });
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


//        cartAdapter = new CartAdapter(this,carts);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        recyclerView.setAdapter(cartAdapter);
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

    public void startBooking()
    {
        Intent i = new Intent(this, BookAppointment.class);
        startActivity(i);
    }

    public void addServices()
    {
        Intent i = new Intent(this, ServicesCategoriesActivity.class);
        startActivity(i);
    }
}
