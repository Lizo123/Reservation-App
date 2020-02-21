package com.example.thespa.dependencies.components;


import com.example.thespa.dependencies.PerActivity;
import com.example.thespa.view.beauticians.BeauticiansActivity;
import com.example.thespa.view.offers.OffersActivity;

import dagger.Subcomponent;

@PerActivity
@Subcomponent
public interface BeauticianComponent {

    // Factory that is used to create instances of this subcomponent
    @Subcomponent.Factory
    interface Factory {
        BeauticianComponent create();
    }

    void inject(BeauticiansActivity beauticiansActivity);
}
