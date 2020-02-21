package com.example.domain.usecases.user_usecases;

import com.example.domain.entities.Response;
import com.example.domain.entities.User;
import com.example.domain.entities.UserModel;
import com.example.domain.executors.PostExecutionThread;
import com.example.domain.executors.ThreadExecutor;
import com.example.domain.repository.UserRepository;
import com.example.domain.usecases.UseCase;

import javax.inject.Inject;

import io.reactivex.Observable;

public class SignInUseCase extends UseCase<Response, UserModel> {

    private UserRepository userRepository;

    @Inject
    public SignInUseCase(UserRepository userRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
    }

    @Override
    protected Observable<Response> buildUseCaseObservable(UserModel userModel) {
        return userRepository.signIn(userModel);
    }
}
