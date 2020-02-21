package com.example.data2.datasource.user;

import com.example.data2.entities.APIResponse;
import com.example.data2.entities.user.UserData;
import com.example.data2.entities.user.UserMobile;
import com.example.domain.entities.ActivationCode;
import com.example.domain.entities.UserModel;

import io.reactivex.Observable;

public class DiskUserDataStore implements UserDataStore {

    @Override
    public Observable<UserData> signIn(UserModel userModel) {
        return null;
    }

    @Override
    public Observable<UserMobile> register(UserModel userModel) {
        return null;
    }

    @Override
    public Observable<UserMobile> sendActivationCode(String mobile) {
        return null;
    }

    @Override
    public Observable<UserData> activate(ActivationCode activationCode) {
        return null;
    }

    @Override
    public Observable<APIResponse> setPassword(ActivationCode activationCode) {
        return null;
    }

    @Override
    public Observable<UserMobile> forgetPassword(String mobile) {
        return null;
    }

    @Override
    public Observable<APIResponse> resetPassword(ActivationCode activationCode) {
        return null;
    }
}
