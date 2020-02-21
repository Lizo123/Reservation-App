package com.example.domain.repository;

import com.example.domain.entities.Service;

import java.util.ArrayList;

import io.reactivex.Observable;

public interface FavoriteRepository {

    Observable<String> setFavorite(String customer_id, String service_id);


    Observable<String> removeFavorite(String customer_id, String service_id);


    Observable<ArrayList<Service>> getUserFavorites(String lang, String customer_id);
}
