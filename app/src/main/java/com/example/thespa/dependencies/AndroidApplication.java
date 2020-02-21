package com.example.thespa.dependencies;

import android.app.Application;

import com.bumptech.glide.request.target.ViewTarget;
import com.example.thespa.R;
import com.example.thespa.dependencies.components.ApplicationComponent;
import com.example.thespa.dependencies.components.DaggerApplicationComponent;
import com.example.thespa.dependencies.modules.ApplicationModule;

public class AndroidApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override public void onCreate() {
        super.onCreate();
        this.initializeInjector();
        ViewTarget.setTagId(R.id.glide_tag);
    }

    private void initializeInjector() {

        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }

}
