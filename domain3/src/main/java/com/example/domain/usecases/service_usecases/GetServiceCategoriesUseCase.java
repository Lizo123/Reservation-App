package com.example.domain.usecases.service_usecases;

import com.example.domain.entities.Response;
import com.example.domain.entities.ServiceCategory;
import com.example.domain.executors.PostExecutionThread;
import com.example.domain.executors.ThreadExecutor;
import com.example.domain.repository.ServiceRepository;
import com.example.domain.usecases.UseCase;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetServiceCategoriesUseCase extends UseCase<Response,String> {


    ServiceRepository serviceRepository;
    @Inject
    public GetServiceCategoriesUseCase(ServiceRepository serviceRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.serviceRepository = serviceRepository;
    }

    @Override
    protected Observable<Response> buildUseCaseObservable(String s) {
        return serviceRepository.getServicesCategories(s);
    }
}
