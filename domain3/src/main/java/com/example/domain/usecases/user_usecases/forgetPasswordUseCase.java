package com.example.domain.usecases.user_usecases;

import com.example.domain.executors.PostExecutionThread;
import com.example.domain.executors.ThreadExecutor;
import com.example.domain.repository.UserRepository;
import com.example.domain.usecases.UseCase;

import javax.inject.Inject;

import io.reactivex.Observable;

public class forgetPasswordUseCase extends UseCase<String,String> {

    UserRepository userRepository;

    @Inject
    public forgetPasswordUseCase(UserRepository userRepository,ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
    }

    @Override
    protected Observable<String> buildUseCaseObservable(String s) {
        return userRepository.forgetPassword(s);
    }
}
