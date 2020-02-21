package com.example.thespa.view.bookAppointment.services;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.thespa.R;
import com.example.thespa.view.bookAppointment.appointment_summary.AppointmentSummaryActivity;

public class BookServicesActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView next;
    RadioButton radio1;
    RadioButton radio2;
    RadioButton radio3;
    RadioButton radio4;
    int count1 = 1;
    int count2 = 1;
    int count3 = 1;
    int count4 = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_services);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next();
            }
        });
        radio1 = findViewById(R.id.radio1);
        radio1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(radio1.isChecked())
                {
                   count1+=1;
                }
                if(count1%2==0) {

                    radio1.setChecked(true);
                    radio2.setChecked(false);
                    radio3.setChecked(false);
                    radio4.setChecked(false);
                }
                else
                    {
                        radio1.setChecked(false);
                        radio2.setChecked(false);
                        radio3.setChecked(false);
                        radio4.setChecked(false);
                    }


            }

        });
        radio2 = findViewById(R.id.radio2);
        radio2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radio2.isChecked()) {
                    count2 += 1;
                }
                if (count2 % 2 == 0) {
                    radio2.setChecked(true);
                    radio1.setChecked(false);
                    radio3.setChecked(false);
                    radio4.setChecked(false);
                } else {
                    radio2.setChecked(false);
                    radio1.setChecked(false);
                    radio3.setChecked(false);
                    radio4.setChecked(false);
                }
            }
        });
        radio3 = findViewById(R.id.radio3);
        radio3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radio3.isChecked())
                {
                    count3+=1;
                }
                if(count3%2==0) {
                    radio1.setChecked(false);
                    radio2.setChecked(false);
                    radio3.setChecked(true);
                    radio4.setChecked(false);
                }
                else{
                    radio1.setChecked(false);
                    radio2.setChecked(false);
                    radio3.setChecked(false);
                    radio4.setChecked(false);
                }
            }

        });

        radio4 = findViewById(R.id.radio4);
        radio4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radio4.isChecked())
                {
                    count4+=1;
                }
                if(count4%2==0) {
                    radio1.setChecked(false);
                    radio2.setChecked(false);
                    radio3.setChecked(false);
                    radio4.setChecked(true);
                }
                else{
                    radio1.setChecked(false);
                    radio2.setChecked(false);
                    radio3.setChecked(false);
                    radio4.setChecked(false);
                }
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
    public void next()
    {
        Intent i = new Intent(this, AppointmentSummaryActivity.class);
        startActivity(i);
    }
}
