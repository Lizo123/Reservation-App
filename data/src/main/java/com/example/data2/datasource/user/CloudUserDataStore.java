package com.example.data2.datasource.user;


import android.util.Log;

import com.example.data2.entities.APIResponse;
import com.example.data2.entities.user.UserData;
import com.example.data2.entities.user.UserMobile;
import com.example.data2.network.APIClient;
import com.example.data2.network.APIInterface;
import com.example.domain.entities.ActivationCode;
import com.example.domain.entities.UserModel;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;


public class CloudUserDataStore implements UserDataStore {

    APIInterface apiInterface;

    @Inject
    public CloudUserDataStore() {
        this.apiInterface = APIClient.getClient().create(APIInterface.class);

    }

    @Override
    public Observable<UserData> signIn(final UserModel userModel) {

        return Observable.create(new ObservableOnSubscribe<UserData>() {
            @Override
            public void subscribe(ObservableEmitter<UserData> emitter) throws Exception {

                emitter.onNext(apiInterface.signIn(userModel.getName(), userModel.getNumber()));
            }
        });

    }

    @Override
    public Observable<UserMobile> register(final UserModel userModel) {
        Observable<UserMobile> response = apiInterface.register(userModel.getName(), userModel.getNumber()).doOnNext(new Consumer<UserMobile>() {
            @Override
            public void accept(UserMobile userMobile) throws Exception {
                Log.d("mobile",userMobile.getResponseMessage()+userMobile.getData());
            }
        }
        );
        return response;



    }

    @Override
    public Observable<UserMobile> sendActivationCode(String mobile) {
        return apiInterface.sendActivationCode(mobile).doOnNext(new Consumer<UserMobile>() {
            @Override
            public void accept(UserMobile userMobile) throws Exception {

            }
        });
    }

    @Override
    public Observable<UserData> activate(ActivationCode activationCode) {
        return apiInterface.activation(activationCode.getNumber(),activationCode.getCode()).doOnNext(new Consumer<UserData>() {
            @Override
            public void accept(UserData userData) throws Exception {

            }
        });
    }

    @Override
    public Observable<APIResponse> setPassword(ActivationCode activationCode) {
        return apiInterface.setPassword(activationCode.getNumber(),activationCode.getPassword()).doOnNext(new Consumer<APIResponse>() {
            @Override
            public void accept(APIResponse apiResponse) throws Exception {

            }
        });
    }

    @Override
    public Observable<UserMobile> forgetPassword(String mobile) {
        return apiInterface.forgetPassword(mobile).doOnNext(new Consumer<UserMobile>() {
            @Override
            public void accept(UserMobile userMobile) throws Exception {

            }
        });
    }

    @Override
    public Observable<APIResponse> resetPassword(ActivationCode activationCode) {
        return apiInterface.resetPassword(activationCode.getCode(),activationCode.getNumber(),activationCode.getPassword());
    }


}
