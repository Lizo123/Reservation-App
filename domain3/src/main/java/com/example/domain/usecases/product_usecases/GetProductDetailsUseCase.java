package com.example.domain.usecases.product_usecases;

import com.example.domain.entities.Product;
import com.example.domain.executors.PostExecutionThread;
import com.example.domain.executors.ThreadExecutor;
import com.example.domain.repository.ProductRepository;
import com.example.domain.request_entities.WithId;
import com.example.domain.usecases.UseCase;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetProductDetailsUseCase extends UseCase<Product, WithId> {

    ProductRepository productRepository;

    @Inject
    public GetProductDetailsUseCase(ProductRepository productRepository,ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.productRepository = productRepository;
    }

    @Override
    protected Observable<Product> buildUseCaseObservable(WithId withId) {
        return productRepository.getProductDetails(withId.getLang(),withId.getId());
    }
}
