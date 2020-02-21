package com.example.domain.repository;

import com.example.domain.entities.ActivationCode;
import com.example.domain.entities.Response;
import com.example.domain.entities.User;
import com.example.domain.entities.UserModel;

import javax.inject.Inject;

import io.reactivex.Observable;


public interface UserRepository {

    Observable<Response> signIn(UserModel userModel);

    Observable<Response> Register(UserModel userModel);

    Observable<Response> sendVerificationCode(String mobile);

    Observable<Response> activate(ActivationCode activationCode);

    Observable<String> setPassword(ActivationCode activationCode);

    Observable<String> forgetPassword(String mobile);

    Observable<String> resetPassword(ActivationCode activationCode);


}
