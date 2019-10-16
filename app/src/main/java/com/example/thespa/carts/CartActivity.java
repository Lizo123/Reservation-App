package com.example.thespa.carts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.thespa.R;
import com.example.thespa.bookAppointment.BookAppointment;
import com.example.thespa.models.Cart;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    CartAdapter cartAdapter;
    ArrayList<Cart> carts;
    Cart cart;
    Cart cart1;
    Cart cart2;
    RecyclerView recyclerView;
    Toolbar toolbar;
    Button booking_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        toolbar = findViewById(R.id.toolbar);
        recyclerView = this.findViewById(R.id.cart_recycler);
        booking_button = findViewById(R.id.booking_button);
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
        carts = new ArrayList<>();
        cart = new Cart("75 min","60 SR");
        cart1 =  new Cart("75 min","60 SR");
        cart2 =  new Cart("75 min","60 SR");
        carts.add(cart);
        carts.add(cart1);
        carts.add(cart2);

        cartAdapter = new CartAdapter(this,carts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(cartAdapter);
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

}
