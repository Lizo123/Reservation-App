package com.example.thespa.dependencies.components;


import com.example.thespa.dependencies.PerActivity;
import com.example.thespa.view.beauticians.BeauticiansActivity;
import com.example.thespa.view.home.HomeActivity;

import dagger.Subcomponent;

@PerActivity
@Subcomponent
public interface HomeComponent {

    // Factory that is used to create instances of this subcomponent
    @Subcomponent.Factory
    interface Factory {
        HomeComponent create();
    }

    void inject(HomeActivity homeActivity);
}
