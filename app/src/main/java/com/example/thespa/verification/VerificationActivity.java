package com.example.thespa.verification;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.thespa.R;
import com.example.thespa.home.HomeActivity;

public class VerificationActivity extends AppCompatActivity {

    Button continue_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        continue_button = findViewById(R.id.continue_button);
        continue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToHome();
            }
        });
    }
    public void goToHome()
    {
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }
}
