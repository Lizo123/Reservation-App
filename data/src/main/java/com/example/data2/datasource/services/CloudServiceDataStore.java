package com.example.data2.datasource.services;

import com.example.data2.entities.services.ServiceCategoryData;
import com.example.data2.entities.services.ServiceData;
import com.example.data2.entities.services.ServiceDetailsData;
import com.example.data2.network.APIClient;
import com.example.data2.network.APIInterface;

import javax.inject.Inject;

import io.reactivex.Observable;

public class CloudServiceDataStore implements ServiceDataStore {


    APIInterface apiInterface;

    @Inject
    public CloudServiceDataStore() {
        this.apiInterface = APIClient.getClient().create(APIInterface.class);

    }

    @Override
    public Observable<ServiceCategoryData> getServicesCategories(String lang) {
        return apiInterface.getAllServicesCategories(lang);
    }

    @Override
    public Observable<ServiceData> getAllServices(String lang,String category_id) {
        return apiInterface.getAllServices(lang,category_id);
    }

    @Override
    public Observable<ServiceDetailsData> getServiceDetails(String lang, int serviceId) {
        return apiInterface.getServiceDetails(lang,serviceId);
    }
}
