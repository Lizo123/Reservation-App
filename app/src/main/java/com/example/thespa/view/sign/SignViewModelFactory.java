package com.example.thespa.view.sign;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.domain.usecases.user_usecases.RegisterAccountUseCase;
import com.example.domain.usecases.user_usecases.SignInUseCase;

import javax.inject.Inject;

public class SignViewModelFactory implements ViewModelProvider.Factory {

    @Inject
    SignInUseCase signInUseCase;
    @Inject
    RegisterAccountUseCase registerAccountUseCase;


    @Inject
    public SignViewModelFactory(SignInUseCase signInUseCase, RegisterAccountUseCase registerAccountUseCase) {
        this.signInUseCase = signInUseCase;
        this.registerAccountUseCase = registerAccountUseCase;

    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SignViewModel(signInUseCase,registerAccountUseCase);
    }
}
