package com.example.thespa.view.bookAppointment.specialities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.domain.entities.Beautician;
import com.example.thespa.R;
import com.example.thespa.view.bookAppointment.services.BookServicesActivity;

import java.util.ArrayList;

public class BookSpecialities extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView beauticians_recycler;
    RecyclerView hair_specialist_recycler;
    RecyclerView message_girls_recycler;
    SpecialitiesAdapter specialitiesAdapter;
    ArrayList<Beautician> beauticians;
    Beautician beautician;
    Beautician beautician1;
    Beautician beautician2;
    TextView next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_specialities);
        toolbar = findViewById(R.id.toolbar);
        next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToServices();
            }
        });
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        beauticians_recycler= findViewById(R.id.beauticians_recycler);
        hair_specialist_recycler = findViewById(R.id.hair_specialist_recycler);
        message_girls_recycler = findViewById(R.id.message_girls_recycler);
        beauticians_recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        hair_specialist_recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        message_girls_recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        beauticians = new ArrayList<>();

        beauticians.add(beautician);
        beauticians.add(beautician1);
        beauticians.add(beautician2);

        specialitiesAdapter = new SpecialitiesAdapter(this,beauticians);

        beauticians_recycler.setAdapter(specialitiesAdapter);
        hair_specialist_recycler.setAdapter(specialitiesAdapter);
        message_girls_recycler.setAdapter(specialitiesAdapter);
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

   public void goToServices()
   {
       Intent i = new Intent(this, BookServicesActivity.class);
       startActivity(i);
   }
}
