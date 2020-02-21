package com.example.thespa.view.sign;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.SharedPreferences;

import com.example.domain.entities.Response;
import com.example.domain.entities.User;
import com.example.domain.entities.UserModel;
import com.example.domain.usecases.DefaultObserver;
import com.example.domain.usecases.user_usecases.RegisterAccountUseCase;
import com.example.domain.usecases.user_usecases.SignInUseCase;
import com.example.thespa.preferences.UserSession;

import javax.inject.Inject;

public class SignViewModel extends ViewModel {


    @Inject
    public SignInUseCase signInUseCase;
    @Inject
    public RegisterAccountUseCase registerAccountUseCase;
    @Inject
    UserSession session;

    @Inject
   SharedPreferences sharedPreferences;

    MutableLiveData<String> verify;
    MutableLiveData<String> error;
    UserModel userModel;
    MutableLiveData<String> loggedAndVerified ;

    @Inject
    SignViewModel(SignInUseCase signInUseCase, RegisterAccountUseCase registerAccountUseCase) {
        this.signInUseCase = signInUseCase;
        this.registerAccountUseCase = registerAccountUseCase;

    }

    void setError(MutableLiveData<String> error)
    {
        this.error = error;
    }

    void signIn(UserModel userModel, MutableLiveData<String> loggedAndVerified) {
        signInUseCase.execute(new signObserver(), userModel);
        this.loggedAndVerified = loggedAndVerified;
    }

    void register(UserModel userModel, MutableLiveData verify) {
        this.userModel = userModel;
        this.verify = verify;
        registerAccountUseCase.execute(new registerObserver(), userModel);
    }


    private final class signObserver extends DefaultObserver<Response> {

        @Override
        public void onComplete() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(Response response) {
            if(response.error.toString().equals("customer_registered_successfully")) {
                User user =(User) response.success;
                if(user.getIsActive().equals("1"))
                    loggedAndVerified.setValue("Active");
                else
                    loggedAndVerified.setValue("NotActive");
                session.createUserLoginSession(user.getName(),user.getEmail(), user.getId(),user.getNumber(),user.getIsActive());
            }
            else if(response.error.toString().equals("mobile_already_exists"))
            {
                error.setValue("Your Mobile is already exists");
            }
            else if(response.error.toString().equals("mobile_not_correct"))
            {
                error.setValue("your mobile isn't correct");
            }
        }
    }

    private final class registerObserver extends DefaultObserver<Response> {

        @Override
        public void onComplete() {


        }

        @Override
        public void onError(Throwable e) {
            error.setValue(e.toString());
        }

        @Override
        public void onNext(Response response) {
            if(response.error.toString().equals("customer_registered_successfully")) {
                verify.setValue(response.success.toString());
                session.createUserLoginSession(userModel.getName(), "", "",userModel.getNumber(),"");
            }
            else if(response.error.toString().equals("mobile_already_exists"))
            {
                error.setValue("Your Mobile is already exists");
            }
            else if(response.error.toString().equals("mobile_not_correct"))
            {
                error.setValue("your mobile isn't correct");
            }

        }
    }


    public void destroyDispose() {
        signInUseCase.dispose();
        registerAccountUseCase.dispose();

    }
}
