package com.example.domain.usecases.beautician_usescases;

import com.example.domain.entities.Beautician;
import com.example.domain.entities.Response;
import com.example.domain.executors.PostExecutionThread;
import com.example.domain.executors.ThreadExecutor;
import com.example.domain.repository.BeauticianRepository;
import com.example.domain.usecases.UseCase;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetBeauticiansUseCase extends UseCase<Response,String> {

    private BeauticianRepository beauticianRepository;

    @Inject
    public GetBeauticiansUseCase(BeauticianRepository beauticianRepository,ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.beauticianRepository = beauticianRepository;
    }

    @Override
    protected Observable<Response> buildUseCaseObservable(String s) {
        return beauticianRepository.getBeauticians(s);
    }
}
