package com.example.thespa.bookAppointment;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Group;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thespa.R;
import com.example.thespa.bookAppointment.schedule.ScheduleActivity;

public class BookAppointment extends AppCompatActivity {

    TextView invite;
    MutableLiveData<String> friend;
    ContactsDialog contactsDialog;
    Context context;
    RecyclerView recyclerView;
    ContactsChoosenAdapter contactsChoosenAdapter;
    TextView add_to_list;
    EditText phone_text;
    Boolean permissionGranted = false;
    Toolbar toolbar;
    Button schedule;
    ImageView group_booking;
    ImageView individual_booking;
    Group group;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        schedule = findViewById(R.id.schedule);
        group_booking = findViewById(R.id.group_booking);
        individual_booking = findViewById(R.id.individual_booking);
        group = findViewById(R.id.group);
        context = this;
        friend = new MutableLiveData<>();
        invite = findViewById(R.id.invite);
        add_to_list = findViewById(R.id.add_to_list);
        phone_text = findViewById(R.id.phone_text);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        contactsChoosenAdapter = new ContactsChoosenAdapter(context);
        recyclerView.setAdapter(contactsChoosenAdapter);
        checkPermission();
        context = this;


        invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (permissionGranted) {
                    contactsDialog = new ContactsDialog(friend, context);
                    contactsDialog.createDialog();
                } else
                    Toast.makeText(context, "You have to allow to read your contacts", Toast.LENGTH_SHORT).show();
            }
        });
        add_to_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactsChoosenAdapter.addItem(phone_text.getText().toString());
            }
        });
        friend.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                contactsChoosenAdapter.addItem(s);
            }
        });
        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSchedule();
            }
        });
        group_booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                group.setVisibility(View.VISIBLE);

            }
        });
        individual_booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                group.setVisibility(View.GONE);

            }
        });
    }

    public Boolean checkPermission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                == PackageManager.PERMISSION_GRANTED) {
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    permissionGranted = true;
                }
            };
            Thread thread = new Thread(r);
            thread.start();
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS)) {
                    Toast.makeText(this, "Read contacts permission is required to function app correctly", Toast.LENGTH_LONG).show();
                } else {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.READ_CONTACTS},1);
                }

            }
        }
        return permissionGranted;
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

    public void goToSchedule()
    {
        Intent i = new Intent(this, ScheduleActivity.class);
        startActivity(i);
    }

}
