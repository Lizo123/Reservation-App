package com.example.thespa.bookAppointment.schedule;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.thespa.R;
import com.example.thespa.bookAppointment.specialities.BookSpecialities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ScheduleActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView next;
    Calendar calendar;
    TextView Date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        calendar = Calendar.getInstance();
        Date = findViewById(R.id.Date);
        final DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
            }
        };
       Date.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
              new DatePickerDialog(ScheduleActivity.this,dateSetListener,calendar.get(Calendar.YEAR),
                       calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
           updateLabel();
           }
       });
        next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSpecialities();
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

    public void goToSpecialities()
    {
        Intent i = new Intent(this, BookSpecialities.class);
        startActivity(i);
    }
    public void updateLabel()
    {
        String format="MM/dd/yy";
        SimpleDateFormat sdf= new SimpleDateFormat(format, Locale.US);
        Date.setText(sdf.format(calendar.getTime()));
    }
}
