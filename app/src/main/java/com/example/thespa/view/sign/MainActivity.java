package com.example.thespa.view.sign;

import android.animation.ObjectAnimator;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableBoolean;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.os.Bundle;
import android.arch.lifecycle.ViewModelProviders;
import android.view.View;
import android.widget.Toast;

import com.example.domain.entities.UserModel;
import com.example.thespa.R;
import com.example.thespa.databinding.ActivityMainBinding;
import com.example.thespa.dependencies.AndroidApplication;
import com.example.thespa.dependencies.components.SignComponent;
import com.example.thespa.preferences.UserSession;
import com.example.thespa.view.BaseActivity;
import com.example.thespa.view.home.HomeActivity;
import com.example.thespa.view.password.PasswordActivity;
import com.example.thespa.view.verification.VerificationActivity;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    ActivityMainBinding activityMainBinding;
    public ObservableBoolean loginVisible;
    ObjectAnimator objectAnimator;
    SignViewModel signViewModel;
    SignComponent signComponent;
    MutableLiveData<String> verify;
    Boolean isLogin = false;
    @Inject
    UserSession session;

    @Inject
    SharedPreferences sharedPreferences;
    MutableLiveData<String> error = new MutableLiveData<>();
    MutableLiveData<String> loggedAndVerified = new MutableLiveData<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initializeInjector();
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        signComponent.inject(this);
        loginVisible = new ObservableBoolean();
        objectAnimator = ObjectAnimator.ofFloat(activityMainBinding.textView, "y", 300);
        signViewModel = ViewModelProviders.of(this, getApplicationComponent().signViewModelFactory()).get(SignViewModel.class);

        activityMainBinding.setMainactivity(this);
        activityMainBinding.setViewModel(signViewModel);
        signViewModel.setError(error);
        if(getIntent()!=null)
        {
            if(getIntent().getStringExtra("source").equals("home"))
            {
                startLogin();
            }
        }


        // User Session Manager


        // Check if UserResponse is Already Logged In
        if (session.isUserLoggedIn()) {
            goToHome();
        }
        verify = new MutableLiveData<String>();
        verify.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String mobile) {
                showToast("You registered Successfully");
                startVerification(mobile);

            }
        });
        error.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                hideProgressBar();
                showToast(s);
            }
        });
        loggedAndVerified.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                showToast("You logged in Successfully");
                if (s.equals("Active")) {
                    goToPassword();
                } else startVerification(activityMainBinding.mobile.getText().toString());
            }
        });


    }

    public void startLogin() {
        objectAnimator.setDuration(400);
        objectAnimator.start();
        loginVisible.set(true);
    }

    public void login() {
        isLogin = true;
        showProgressBar();
        if (checkInputValidation()) {
            signViewModel.signIn(new UserModel(activityMainBinding.name.getText().toString(),
                    activityMainBinding.mobile.getText().toString()), loggedAndVerified);
        }
    }

    public void register() {
        if (checkInputValidation()) {
            showProgressBar();
            signViewModel.register(new UserModel(activityMainBinding.name.getText().toString(),
                    activityMainBinding.mobile.getText().toString()), verify);
        }
    }

    Boolean checkInputValidation() {
        Boolean checkName = false;
        Boolean checkMobile = false;
        Boolean valid = false;

        checkName = checkEmpty(activityMainBinding.name);
        checkMobile = checkEmpty(activityMainBinding.mobile);


        //Check input validation
        if (checkName && checkMobile) {

            valid = true;

        }

        return valid;
    }

    public Boolean checkEmpty(TextInputEditText editText) {

        Boolean check;
        if (editText != null && !editText.getText().toString().isEmpty()) {
            check = true;

        } else {
            editText.setError(getString(R.string.required));
            check = false;
        }
        return check;
    }


    private void initializeInjector() {
        this.signComponent = ((AndroidApplication) getApplicationContext())
                .getApplicationComponent().signComponent().create();
    }


    public SignComponent getComponent() {
        return signComponent;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        signViewModel.destroyDispose();
    }

    public void startVerification(String mobile) {
        Intent i = new Intent(this, VerificationActivity.class);
        i.putExtra("mobile", mobile);
        i.putExtra("isLogin", isLogin);
        startActivity(i);
    }

    public void goToHome() {
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }

    public void goToPassword() {
        Intent i = new Intent(this, PasswordActivity.class);
        i.putExtra("mobile", activityMainBinding.mobile.getText().toString());
        i.putExtra("isLogin", true);
        startActivity(i);
    }

    public void showToast(String error) {

        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    public void showProgressBar() {
        activityMainBinding.progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar()
    {
        activityMainBinding.progressBar.setVisibility(View.GONE);
    }
}
