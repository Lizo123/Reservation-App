package com.example.domain.usecases.favorite_usecases;

import com.example.domain.executors.PostExecutionThread;
import com.example.domain.executors.ThreadExecutor;
import com.example.domain.repository.FavoriteRepository;
import com.example.domain.request_entities.WithId;
import com.example.domain.usecases.UseCase;

import javax.inject.Inject;

import io.reactivex.Observable;

public class RemoveFavoriteUseCase extends UseCase<String, WithId> {

    private FavoriteRepository favoriteRepository;

    @Inject
    public RemoveFavoriteUseCase(FavoriteRepository favoriteRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.favoriteRepository = favoriteRepository;
    }

    @Override
    protected Observable<String> buildUseCaseObservable(WithId withId) {
        return favoriteRepository.removeFavorite(withId.getLang(), withId.getId());
    }
}
