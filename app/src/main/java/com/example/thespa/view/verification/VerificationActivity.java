package com.example.thespa.view.verification;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.domain.entities.ActivationCode;
import com.example.domain.entities.Response;
import com.example.domain.entities.User;
import com.example.domain.usecases.user_usecases.ActivationUseCase;
import com.example.domain.usecases.DefaultObserver;
import com.example.domain.usecases.user_usecases.VerificationUseCase;
import com.example.thespa.R;
import com.example.thespa.databinding.ActivityVerificationBinding;
import com.example.thespa.dependencies.AndroidApplication;
import com.example.thespa.dependencies.components.SignComponent;
import com.example.thespa.preferences.UserSession;
import com.example.thespa.view.password.PasswordActivity;

import javax.inject.Inject;

public class VerificationActivity extends AppCompatActivity {

    ActivityVerificationBinding activityVerificationBinding;
    Button continue_button;
    @Inject
    VerificationUseCase verificationUseCase;
    SignComponent signComponent;
    @Inject
    ActivationUseCase activationUseCase;
    Boolean isLogin = false;
    String mobile = "";
    @Inject
    UserSession session;

    @Inject
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initializeInjector();
        super.onCreate(savedInstanceState);
        activityVerificationBinding = DataBindingUtil.setContentView(this, R.layout.activity_verification);
        activityVerificationBinding.setActivity(this);
        signComponent.inject(this);

        activityVerificationBinding.editTextOne.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                activityVerificationBinding.editTextOne.clearFocus();
                activityVerificationBinding.editTextTwo.requestFocus();
                activityVerificationBinding.editTextTwo.setCursorVisible(true);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        activityVerificationBinding.editTextTwo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                activityVerificationBinding.editTextTwo.clearFocus();
                activityVerificationBinding.editTextThree.requestFocus();
                activityVerificationBinding.editTextThree.setCursorVisible(true);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        activityVerificationBinding.editTextThree.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                activityVerificationBinding.editTextThree.clearFocus();
                activityVerificationBinding.editTextFour.requestFocus();
                activityVerificationBinding.editTextFour.setCursorVisible(true);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mobile = getIntent().getStringExtra("mobile");
        sendActivationCode(mobile);
        if(getIntent().getBooleanExtra("isLogin",false))
        {
            isLogin = true;
        }

        activityVerificationBinding.resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendActivationCode(mobile);
            }
        });
    }

    void activate(String mobile) {

        if(checkInputValidation())
        {
            showProgressBar();
            String code = activityVerificationBinding.editTextOne.getText().toString()+
                    activityVerificationBinding.editTextTwo.getText().toString()+
                    activityVerificationBinding.editTextThree.getText().toString()+
                    activityVerificationBinding.editTextFour.getText().toString();
            activationUseCase.execute(new activateObserver(),new ActivationCode(mobile,code,""));
        }


    }

    void sendActivationCode(String mobile) {
        verificationUseCase.execute(new sendCodeObserver(), mobile);
    }

    private final class activateObserver extends DefaultObserver<Response>{
        @Override
        public void onComplete() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(Response response) {
            hideProgressBar();
            if(response.error.toString().equals("account_successfully_activated")) {
                showToast(getResources().getString(R.string.account_activated));
                User user = (User) response.success;
                mobile = user.getNumber();
                session.createUserLoginSession(user.getName(), user.getEmail(), user.getId(), user.getNumber(), user.getIsActive());
                goToPassword();
            }
            else if(response.error.toString().equals("activation_code_or_mobile_not_correct"))
                showToast(getResources().getString(R.string.code_not_correct));
        }
    }

    private final class sendCodeObserver extends DefaultObserver<Response> {

        @Override
        public void onComplete() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(Response response) {
            activate((String) response.success);
        }

    }

    public void goToPassword() {
        Intent i = new Intent(this, PasswordActivity.class);
        i.putExtra("mobile",mobile);
        i.putExtra("isLogin",isLogin);
        startActivity(i);
    }



    private void initializeInjector() {
        this.signComponent = ((AndroidApplication) getApplicationContext())
                .getApplicationComponent().signComponent().create();
    }

    public boolean checkInputValidation()
    {
        Boolean check=false;
        if(!activityVerificationBinding.editTextOne.getText().toString().isEmpty()
            &&!activityVerificationBinding.editTextTwo.getText().toString().isEmpty()
        &&!activityVerificationBinding.editTextThree.getText().toString().isEmpty()
        &&!activityVerificationBinding.editTextFour.getText().toString().isEmpty())
        {
            check = true;
        }
        return check;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        verificationUseCase.dispose();
    }

    public void showProgressBar()
    {
        activityVerificationBinding.progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar()
    {
        activityVerificationBinding.progressBar.setVisibility(View.GONE);
    }

    public void showToast(String s)
    {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

}
