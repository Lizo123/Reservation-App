package com.example.thespa.favorite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.thespa.R;
import com.example.thespa.carts.CartActivity;
import com.example.thespa.massage.massageAdapter;
import com.example.thespa.models.Cart;

import java.util.ArrayList;

public class FavoriteActivity extends AppCompatActivity {
    com.example.thespa.massage.massageAdapter massageAdapter;
    ArrayList<Cart> carts;
    Cart cart;
    Cart cart1;
    Cart cart2;
    RecyclerView recyclerView;
    Toolbar toolbar;
    ImageView cartBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        toolbar = findViewById(R.id.toolbar);
        cartBar = findViewById(R.id.cart);
        recyclerView = this.findViewById(R.id.cart_recycler);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        cartBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCartActivity();
            }
        });
        carts = new ArrayList<>();
        cart = new Cart("75 min","60 SR");
        cart1 =  new Cart("75 min","60 SR");
        cart2 =  new Cart("75 min","60 SR");
        carts.add(cart);
        carts.add(cart1);
        carts.add(cart2);

        massageAdapter = new massageAdapter(this,carts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(massageAdapter);

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

}
