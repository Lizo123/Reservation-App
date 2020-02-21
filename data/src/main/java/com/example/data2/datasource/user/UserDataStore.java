package com.example.data2.datasource.user;

import com.example.data2.entities.APIResponse;
import com.example.data2.entities.user.UserData;
import com.example.data2.entities.user.UserMobile;
import com.example.domain.entities.ActivationCode;
import com.example.domain.entities.UserModel;

import io.reactivex.Observable;

public interface UserDataStore {

    Observable<UserData> signIn(UserModel userModel);

    Observable<UserMobile> register(UserModel userModel);

    Observable<UserMobile> sendActivationCode(String mobile);

    Observable<UserData> activate(ActivationCode activationCode);

    Observable<APIResponse> setPassword(ActivationCode activationCode);

    Observable<UserMobile> forgetPassword(String mobile);

    Observable<APIResponse> resetPassword(ActivationCode activationCode);
}
