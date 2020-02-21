package com.example.thespa.view.bookAppointment.appointment_summary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.thespa.R;
import com.example.thespa.view.bookAppointment.payment.PaymentMethods;
import com.example.thespa.view.services.ServicesCategoriesActivity;

public class AppointmentSummaryActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView next;
    LinearLayout add_services;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_summary);
        toolbar = findViewById(R.id.toolbar);
        add_services = findViewById(R.id.add_services);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToConfirmPayment();
            }
        });
        add_services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addServices();
            }
        });
    }
    public void goToConfirmPayment()
    {
        Intent i = new Intent(this, PaymentMethods.class);
        startActivity(i);
    }

    public void addServices()
    {
        Intent i = new Intent(this, ServicesCategoriesActivity.class);
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
}
