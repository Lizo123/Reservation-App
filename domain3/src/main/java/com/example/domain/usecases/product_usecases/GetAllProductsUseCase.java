package com.example.domain.usecases.product_usecases;

import com.example.domain.entities.Product;
import com.example.domain.entities.Response;
import com.example.domain.executors.PostExecutionThread;
import com.example.domain.executors.ThreadExecutor;
import com.example.domain.repository.ProductRepository;
import com.example.domain.request_entities.WithId;
import com.example.domain.usecases.UseCase;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetAllProductsUseCase extends UseCase<Response, WithId> {

    ProductRepository productRepository;

    @Inject
    public GetAllProductsUseCase(ProductRepository productRepository,ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.productRepository = productRepository;
    }

    @Override
    protected Observable<Response> buildUseCaseObservable(WithId withId) {
        return productRepository.getAllProducts(withId.getLang(),withId.getId());
    }
}
