package com.example.thespa;

import android.animation.ObjectAnimator;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableBoolean;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.thespa.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;
    public ObservableBoolean loginVisible;
    ObjectAnimator objectAnimator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        activityMainBinding.setMainactivity(this);
        loginVisible = new ObservableBoolean();
        objectAnimator = ObjectAnimator.ofFloat(activityMainBinding.textView,"y",300);

    }

    public void startLogin()
    {
        objectAnimator.setDuration(400);
        objectAnimator.start();
        loginVisible.set(true);
    }
}
