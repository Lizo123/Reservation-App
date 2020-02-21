package com.example.data2.datasource.favorite;

import com.example.data2.entities.APIResponse;
import com.example.data2.entities.services.ServiceData;
import com.example.data2.network.APIClient;
import com.example.data2.network.APIInterface;

import javax.inject.Inject;

import io.reactivex.Observable;

public class CloudFavoriteDataSource implements FavoriteDataSource {

    private APIInterface apiInterface;

    @Inject
    CloudFavoriteDataSource() {
        this.apiInterface = APIClient.getClient().create(APIInterface.class);

    }


    @Override
    public Observable<APIResponse> setFavorite(String customer_id, String service_id) {
        return apiInterface.setFavorite(customer_id,service_id);
    }

    @Override
    public Observable<APIResponse> removeFavorite(String customer_id, String service_id) {
        return apiInterface.removeFavorite(customer_id, service_id);
    }

    @Override
    public Observable<ServiceData> getUserFavorites(String lang, String customer_id) {
        return apiInterface.getUserFavoriites(lang,customer_id);
    }
}
