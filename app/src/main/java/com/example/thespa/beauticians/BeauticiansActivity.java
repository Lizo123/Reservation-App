package com.example.thespa.beauticians;

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

public class BeauticiansActivity extends AppCompatActivity {

    LinearLayout maya;
    Toolbar toolbar;
    ImageView cartBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beauticians);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        cartBar = findViewById(R.id.cart);
        maya = findViewById(R.id.maya);
        maya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToDetails();
            }
        });
        cartBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCartActivity();
            }
        });
    }

    public void goToDetails()
    {
        Intent i = new Intent(this,BeauticiansDetails.class);
        startActivity(i);
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
