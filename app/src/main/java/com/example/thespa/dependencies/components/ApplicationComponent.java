package com.example.thespa.dependencies.components;


import android.content.Context;

import com.example.domain.executors.PostExecutionThread;
import com.example.domain.executors.ThreadExecutor;
import com.example.thespa.dependencies.modules.ApplicationModule;
import com.example.thespa.dependencies.modules.BeauticianModule;
import com.example.thespa.dependencies.modules.HomeModule;
import com.example.thespa.dependencies.modules.OfferModule;
import com.example.thespa.dependencies.modules.ProductModule;
import com.example.thespa.dependencies.modules.ServiceModule;
import com.example.thespa.dependencies.modules.SignModule;
import com.example.thespa.view.BaseActivity;
import com.example.thespa.view.sign.SignViewModelFactory;

import javax.inject.Singleton;

import dagger.Component;

@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = {ApplicationModule.class, SignModule.class, OfferModule.class, ServiceModule.class, ProductModule.class, BeauticianModule.class, HomeModule.class})
public interface ApplicationComponent {

    SignComponent.Factory signComponent();
    OfferComponent.Factory offerComponent();
    ServiceComponent.Factory serviceComponent();
    ProductComponent.Factory productComponent();
    BeauticianComponent.Factory beauticianComponent();
    HomeComponent.Factory homeComponent();

    void inject(BaseActivity baseActivity);

    //Exposed to sub-graphs.
    Context context();
    ThreadExecutor threadExecutor();
    PostExecutionThread postExecutionThread();



  SignViewModelFactory signViewModelFactory();
}
