package com.example.data2.reprositoryimpl;

import com.example.data2.datasource.beauticians.CloudBeauticianDataSource;
import com.example.data2.datasource.favorite.CloudFavoriteDataSource;
import com.example.data2.datasource.favorite.FavoriteDataSource;
import com.example.data2.entities.APIResponse;
import com.example.data2.entities.services.ServiceData;
import com.example.data2.mapper.RemoteToLocalMapper;
import com.example.domain.entities.Service;
import com.example.domain.repository.FavoriteRepository;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class FavoriteRepositoryImpl implements FavoriteRepository {

    @Inject
    CloudFavoriteDataSource cloudFavoriteDataSource;


    @Inject
    RemoteToLocalMapper remoteToLocalMapper;

    @Inject
    public FavoriteRepositoryImpl() {
    }

    @Override
    public Observable<String> setFavorite(String customer_id, String service_id) {
        return cloudFavoriteDataSource.setFavorite(customer_id,service_id).map(new Function<APIResponse, String>() {
            @Override
            public String apply(APIResponse apiResponse) throws Exception {
                return apiResponse.getResponseMessage();
            }
        });
    }

    @Override
    public Observable<String> removeFavorite(String customer_id, String service_id) {
        return cloudFavoriteDataSource.removeFavorite(customer_id, service_id).map(new Function<APIResponse, String>() {
            @Override
            public String apply(APIResponse apiResponse) throws Exception {
                return apiResponse.getResponseMessage();
            }
        });
    }

    @Override
    public Observable<ArrayList<Service>> getUserFavorites(String lang, String customer_id) {
        return cloudFavoriteDataSource.getUserFavorites(lang,customer_id).map(new Function<ServiceData, ArrayList<Service>>() {
            @Override
            public ArrayList<Service> apply(ServiceData serviceData) throws Exception {
                return remoteToLocalMapper.fromRemoteToLocalListOfServices(serviceData.getData());
            }
        });
    }
}
