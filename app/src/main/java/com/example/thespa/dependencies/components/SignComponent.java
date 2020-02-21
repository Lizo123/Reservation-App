package com.example.thespa.dependencies.components;

import com.example.thespa.dependencies.PerActivity;
import com.example.thespa.view.favorite.FavoriteActivity;
import com.example.thespa.view.password.PasswordActivity;
import com.example.thespa.view.sign.MainActivity;
import com.example.thespa.view.verification.VerificationActivity;

import dagger.Subcomponent;

@PerActivity
@Subcomponent
public interface SignComponent {

    // Factory that is used to create instances of this subcomponent
    @Subcomponent.Factory
    interface Factory {
        SignComponent create();
    }

    void inject(MainActivity mainActivity);

    void inject(VerificationActivity verificationActivity);

    void inject(PasswordActivity passwordActivity);

    void inject(FavoriteActivity favoriteActivity);

}
