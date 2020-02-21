package com.example.data2.reprositoryimpl;

import com.example.data2.datasource.user.CloudUserDataStore;
import com.example.data2.entities.APIResponse;
import com.example.data2.entities.user.UserData;
import com.example.data2.entities.user.UserMobile;
import com.example.data2.mapper.RemoteToLocalMapper;
import com.example.domain.entities.ActivationCode;
import com.example.domain.entities.Response;
import com.example.domain.entities.User;
import com.example.domain.entities.UserModel;
import com.example.domain.repository.UserRepository;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class UserRepositoryImpl implements UserRepository {

    @Inject
    CloudUserDataStore cloudUserDataStore;

    @Inject
    RemoteToLocalMapper remoteToLocalMapper;

    @Inject
    public UserRepositoryImpl() {

    }

    @Override
    public Observable<Response> signIn(UserModel userModel) {
        return cloudUserDataStore.signIn(userModel).map(new Function<UserData, Response>() {
            @Override
            public Response apply(UserData userData) throws Exception {
               return new Response(remoteToLocalMapper.fromRemoteToLocalUser(userData.getData()),userData.getResponseMessage());
            }
        });
    }

    @Override
    public Observable<Response> Register(UserModel userModel) {
        return cloudUserDataStore.register(userModel).map(new Function<UserMobile, Response>() {
            @Override
            public Response apply(UserMobile userMobile) throws Exception {
                return new Response(userMobile.getData(),userMobile.getResponseMessage());
            }
        });}

    @Override
    public Observable<Response> sendVerificationCode(String mobile) {
        return cloudUserDataStore.sendActivationCode(mobile).map(new Function<UserMobile, Response>() {

            @Override
            public Response apply(UserMobile userMobile) throws Exception {
                return new Response(userMobile.getData(),userMobile.getResponseMessage());
            }
        });
    }

    @Override
    public Observable<Response> activate(ActivationCode activationCode) {
        return cloudUserDataStore.activate(activationCode).map(new Function<UserData, Response>() {
            @Override
            public Response apply(UserData userData) throws Exception {
                return new Response(remoteToLocalMapper.fromRemoteToLocalUser(userData.getData()),userData.getResponseMessage());
            }
        });
    }

    @Override
    public Observable<String> setPassword(ActivationCode activationCode) {
        return cloudUserDataStore.setPassword(activationCode).map(new Function<APIResponse, String>() {
            @Override
            public String apply(APIResponse apiResponse) throws Exception {
                return apiResponse.getResponseMessage();
            }
        });
    }

    @Override
    public Observable<String> forgetPassword(String mobile) {
        return cloudUserDataStore.forgetPassword(mobile).map(new Function<UserMobile, String>() {
            @Override
            public String apply(UserMobile userMobile) throws Exception {
                return userMobile.getData();
            }
        });
    }

    @Override
    public Observable<String> resetPassword(ActivationCode activationCode) {
        return cloudUserDataStore.resetPassword(activationCode).map(new Function<APIResponse, String>() {
            @Override
            public String apply(APIResponse apiResponse) throws Exception {
                return apiResponse.getResponseMessage();
            }
        });
    }


}
