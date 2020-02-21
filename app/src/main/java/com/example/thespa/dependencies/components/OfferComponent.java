package com.example.thespa.dependencies.components;

import com.example.thespa.dependencies.PerActivity;
import com.example.thespa.view.offers.OffersActivity;

import dagger.Subcomponent;


@PerActivity
@Subcomponent
public interface OfferComponent {

    // Factory that is used to create instances of this subcomponent
    @Subcomponent.Factory
    interface Factory {
        OfferComponent create();
    }

    void inject(OffersActivity offersActivity);
}
