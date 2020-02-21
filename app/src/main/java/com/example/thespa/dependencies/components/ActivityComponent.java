package com.example.thespa.dependencies.components;

import android.app.Activity;

import com.example.thespa.dependencies.PerActivity;
import com.example.thespa.dependencies.modules.ActivityModule;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity activity();
}
