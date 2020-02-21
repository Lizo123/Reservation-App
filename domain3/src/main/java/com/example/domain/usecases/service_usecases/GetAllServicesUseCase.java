package com.example.domain.usecases.service_usecases;

import com.example.domain.entities.Response;
import com.example.domain.entities.Service;
import com.example.domain.executors.PostExecutionThread;
import com.example.domain.executors.ThreadExecutor;
import com.example.domain.repository.ServiceRepository;
import com.example.domain.request_entities.WithId;
import com.example.domain.usecases.UseCase;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetAllServicesUseCase extends UseCase<Response, WithId> {


    private ServiceRepository serviceRepository;

    @Inject
    public GetAllServicesUseCase(ServiceRepository serviceRepository,ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.serviceRepository = serviceRepository;
    }

    @Override
    protected Observable<Response> buildUseCaseObservable(WithId withId) {
        return serviceRepository.getAllServices(withId.getLang(),withId.getId());
    }


}
