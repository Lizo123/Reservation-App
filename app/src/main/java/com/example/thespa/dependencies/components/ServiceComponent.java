package com.example.thespa.dependencies.components;


import com.example.thespa.dependencies.PerActivity;
import com.example.thespa.view.services.ServicesActivity;
import com.example.thespa.view.services.ServicesCategoriesActivity;

import dagger.Subcomponent;

@PerActivity
@Subcomponent
public interface ServiceComponent {

    @Subcomponent.Factory
    interface Factory {
        ServiceComponent create();
    }

    void inject(ServicesCategoriesActivity servicesActivity);
    void inject(ServicesActivity servicesActivity);
}
