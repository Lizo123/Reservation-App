package com.example.domain.usecases.offerusecases;

import com.example.domain.entities.Offer;
import com.example.domain.entities.Response;
import com.example.domain.executors.PostExecutionThread;
import com.example.domain.executors.ThreadExecutor;
import com.example.domain.repository.OfferRepository;
import com.example.domain.usecases.UseCase;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetAllOffersUseCase extends UseCase<Response,String> {

    @Inject
    OfferRepository offerRepository;


    @Inject
    public GetAllOffersUseCase(OfferRepository offerRepository,ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.offerRepository = offerRepository;
    }

    @Override
    protected Observable<Response> buildUseCaseObservable(String s) {
        return offerRepository.getAllOffers(s);
    }
}
