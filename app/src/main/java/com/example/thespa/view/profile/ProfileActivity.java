package com.example.thespa.view.profile;

import android.databinding.DataBindingUtil;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.thespa.R;
import com.example.thespa.databinding.ActivityProfileBinding;
import com.example.thespa.view.home.HomeActivity;

public class ProfileActivity extends AppCompatActivity {

    ActivityProfileBinding activityProfileBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityProfileBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        activityProfileBinding.setActivity(this);

    }
    public void goToHome()
    {
        if(checkInputValidation()) {
            finish();
        }
    }
    Boolean checkInputValidation()
    {
        Boolean checkName= false;
        Boolean checkMobile= false;
        Boolean valid = false;

        checkName = checkEmpty(activityProfileBinding.name);
        checkMobile = checkEmpty(activityProfileBinding.mobile);


        //Check input validation
        if (checkName && checkMobile) {

            valid = true;

        }

        return valid;
    }
    public Boolean checkEmpty(TextInputEditText editText) {

        Boolean check;
        if (editText!=null && !editText.getText().toString().isEmpty()) {
            check = true;

        }
        else {
            editText.setError(getString(R.string.required));
            check = false;
        }
        return check;
    }
}
