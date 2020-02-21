package com.example.thespa.view.bookAppointment.payment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thespa.R;
import com.example.thespa.view.bookAppointment.CreditCardPayment;
import com.example.thespa.view.bookAppointment.PaymentSuccess;

public class PaymentMethods extends AppCompatActivity {

    Toolbar toolbar;
    TextView next;
    Button cash;
    Button card;
    String method = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_methods);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        cash = findViewById(R.id.cash);
        card = findViewById(R.id.card);
        cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                card.setBackground(getResources().getDrawable(R.drawable.rounded_border_edittext));
                card.setTextColor(getResources().getColor(R.color.colorPrimary));
                cash.setBackground(getResources().getDrawable(R.drawable.rounded_solid_border));
                cash.setTextColor(getResources().getColor(R.color.White));
                method = "cash";
            }
        });
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cash.setBackground(getResources().getDrawable(R.drawable.rounded_border_edittext));
                card.setBackground(getResources().getDrawable(R.drawable.rounded_solid_border));
                cash.setTextColor(getResources().getColor(R.color.colorPrimary));
                card.setTextColor(getResources().getColor(R.color.White));
                method = "card";
            }
        });
        next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToConfirmPayment();
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

    public void goToConfirmPayment()
    {
        if(method.equals("card")) {
            Intent i = new Intent(this, CreditCardPayment.class);
            startActivity(i);
        }

        else if(method.equals("cash")){
            Intent i = new Intent(this, PaymentSuccess.class);
            startActivity(i);
        }
        else
            Toast.makeText(this, "You have to choose a payment method", Toast.LENGTH_SHORT).show();
    }

}
