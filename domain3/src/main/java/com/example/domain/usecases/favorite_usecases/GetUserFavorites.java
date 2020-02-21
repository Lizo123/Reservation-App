package com.example.domain.usecases.favorite_usecases;

import com.example.domain.entities.Service;
import com.example.domain.executors.PostExecutionThread;
import com.example.domain.executors.ThreadExecutor;
import com.example.domain.repository.FavoriteRepository;
import com.example.domain.request_entities.WithId;
import com.example.domain.usecases.UseCase;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetUserFavorites extends UseCase<ArrayList<Service>, WithId> {
    FavoriteRepository favoriteRepository;

    @Inject
    public GetUserFavorites(FavoriteRepository favoriteRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.favoriteRepository = favoriteRepository;
    }

    @Override
    protected Observable<ArrayList<Service>> buildUseCaseObservable(WithId withId) {
        return favoriteRepository.getUserFavorites(withId.getLang(),withId.getId());
    }
}
