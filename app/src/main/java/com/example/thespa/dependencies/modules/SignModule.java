package com.example.thespa.dependencies.modules;

import com.example.thespa.dependencies.components.SignComponent;
import com.example.thespa.view.sign.SignViewModel;
import com.example.thespa.view.sign.SignViewModelFactory;

import dagger.Module;

@Module(subcomponents = SignComponent.class)
public class SignModule {

    SignViewModel signViewModel;
    SignViewModelFactory signViewModelFactory;




}
