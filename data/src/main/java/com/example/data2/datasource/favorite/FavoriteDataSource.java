package com.example.data2.datasource.favorite;

import com.example.data2.entities.APIResponse;
import com.example.data2.entities.services.ServiceData;
import com.example.domain.entities.Response;

import io.reactivex.Observable;

public interface FavoriteDataSource {

    Observable<APIResponse> setFavorite(String customer_id, String service_id);

    Observable<APIResponse> removeFavorite(String customer_id, String service_id);

    Observable<ServiceData> getUserFavorites(String lang, String customer_id);
}
