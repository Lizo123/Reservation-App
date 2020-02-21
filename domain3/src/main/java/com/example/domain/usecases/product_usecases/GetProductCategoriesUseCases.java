package com.example.domain.usecases.product_usecases;

import com.example.domain.entities.ProductCategory;
import com.example.domain.entities.Response;
import com.example.domain.executors.PostExecutionThread;
import com.example.domain.executors.ThreadExecutor;
import com.example.domain.repository.ProductRepository;
import com.example.domain.usecases.UseCase;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetProductCategoriesUseCases extends UseCase<Response,String> {


    private ProductRepository productRepository;

    @Inject
    public GetProductCategoriesUseCases(ProductRepository productRepository,ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.productRepository = productRepository;
    }

    @Override
    protected Observable<Response> buildUseCaseObservable(String s) {
        return productRepository.getProductCategories(s);
    }
}
