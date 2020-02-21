package com.example.thespa.view.bookAppointment.schedule;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.thespa.R;
import com.example.thespa.view.bookAppointment.specialities.BookSpecialities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ScheduleActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView next;
    Calendar calendar;
    TextView Date;
    RadioButton radio1;
    RadioButton radio2;
    RadioButton radio3;
    RadioButton radio4;
    Spinner spinner1;
    Spinner spinner2;
    int count1 = 1;
    int count2 = 1;
    int count3 = 1;
    int count4 = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.cities, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner1.setSelection(position);
                if(spinner1.getSelectedItem().toString().equals("Riyadh"))
                    setSelection(R.array.riyadh_streets);
                else if(spinner1.getSelectedItem().toString().equals("Jeddah"))
                    setSelection(R.array.jeddah_streets);
                else
                    setSelection(R.array.dammam_streets);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        calendar = Calendar.getInstance();
        Date = findViewById(R.id.Date);
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
    void setSelection(int selection)
    {
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                selection, R.layout.spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner2.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
