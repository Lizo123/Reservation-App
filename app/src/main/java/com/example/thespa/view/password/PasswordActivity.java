package com.example.thespa.view.password;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableBoolean;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.domain.entities.ActivationCode;
import com.example.domain.usecases.DefaultObserver;
import com.example.domain.usecases.user_usecases.ResetPasswordUseCase;
import com.example.domain.usecases.user_usecases.SetPasswordUseCase;
import com.example.domain.usecases.user_usecases.forgetPasswordUseCase;
import com.example.thespa.R;
import com.example.thespa.databinding.ActivityPasswordBinding;
import com.example.thespa.dependencies.AndroidApplication;
import com.example.thespa.dependencies.components.SignComponent;
import com.example.thespa.view.BaseActivity;
import com.example.thespa.view.home.HomeActivity;

import javax.inject.Inject;

public class PasswordActivity extends BaseActivity {
    ActivityPasswordBinding activityPasswordBinding;
    public ObservableBoolean isLogin = new ObservableBoolean(false);
    String mobile;
    MutableLiveData<String> forgetPassMobile = new MutableLiveData<>();
    SignComponent signComponent;
    @Inject
    SetPasswordUseCase setPasswordUseCase;
    @Inject
    forgetPasswordUseCase forgetPasswordUseCase;
    @Inject
    ResetPasswordUseCase resetPasswordUseCase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initializeInjector();
        super.onCreate(savedInstanceState);
        activityPasswordBinding = DataBindingUtil.setContentView(this, R.layout.activity_password);
        activityPasswordBinding.setActivity(this);
        signComponent.inject(this);
        isLogin.set(getIntent().getBooleanExtra("isLogin",false));
        mobile = getIntent().getStringExtra("mobile");
        forgetPassMobile.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });

    }

    public void register()
    {
        goToHome();
        if(checkInputValidation())
        {
            if(activityPasswordBinding.edit1.getText().toString().equals(activityPasswordBinding.edit2.getText().toString())) {
                setPasswordUseCase.execute(new setPasswordObserver(), new ActivationCode(mobile, "", activityPasswordBinding.edit1.getText().toString()));
            }
            else
            {
                activityPasswordBinding.edit2.setError(getResources().getString(R.string.confirmation_not_correct));
            }
        }
    }
    public void forgetPassword()
    {
        ForgetPassDialog.createDialog(forgetPassMobile);
        activityPasswordBinding.text1.setText(getResources().getString(R.string.enter_new_password));
        activityPasswordBinding.text2.setText(getResources().getString(R.string.enter_code));

    }

    public void login()
    {
        if(checkInputValidation()) {
            resetPasswordUseCase.execute(new resetPasswordObserver(), new ActivationCode(forgetPassMobile.getValue(),
                    activityPasswordBinding.edit2.getText().toString(), activityPasswordBinding.edit1.getText().toString()));
        }

    }

    private void initializeInjector() {
        this.signComponent = ((AndroidApplication) getApplicationContext())
                .getApplicationComponent().signComponent().create();
    }

    Boolean checkInputValidation() {
        Boolean checkName = false;
        Boolean checkMobile = false;
        Boolean valid = false;

        checkName = checkEmpty(activityPasswordBinding.edit1);
        checkMobile = checkEmpty(activityPasswordBinding.edit2);


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

    private final class setPasswordObserver extends DefaultObserver<String> {
        @Override
        public void onComplete() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(String s) {
            goToHome();
        }
    }

    private final class resetPasswordObserver extends DefaultObserver<String> {
        @Override
        public void onComplete() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(String s) {
            goToHome();
        }
    }
    
    private void goToHome()
    {
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }

}
