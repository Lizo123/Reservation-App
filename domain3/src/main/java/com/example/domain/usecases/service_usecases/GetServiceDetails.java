package com.example.domain.usecases.service_usecases;

import com.example.domain.entities.Service;
import com.example.domain.executors.PostExecutionThread;
import com.example.domain.executors.ThreadExecutor;
import com.example.domain.repository.ServiceRepository;
import com.example.domain.request_entities.request_service;
import com.example.domain.usecases.UseCase;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetServiceDetails extends UseCase<Service, request_service> {

    ServiceRepository serviceRepository;

    @Inject
    public GetServiceDetails(ServiceRepository serviceRepository,ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.serviceRepository = serviceRepository;
    }

    @Override
    protected Observable<Service> buildUseCaseObservable(request_service request_service) {
        return serviceRepository.getServiceDetails(request_service.getLang(),request_service.getServiceId());
    }
}
