package com.example.domain.usecases.user_usecases;

import com.example.domain.entities.ActivationCode;
import com.example.domain.executors.PostExecutionThread;
import com.example.domain.executors.ThreadExecutor;
import com.example.domain.repository.UserRepository;
import com.example.domain.usecases.UseCase;

import javax.inject.Inject;

import io.reactivex.Observable;

public class SetPasswordUseCase extends UseCase<String, ActivationCode> {

    private UserRepository userRepository;


    @Inject
    public SetPasswordUseCase(UserRepository userRepository,ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
    }

    @Override
    protected Observable<String> buildUseCaseObservable(ActivationCode activationCode) {
        return userRepository.setPassword(activationCode);
    }
}
