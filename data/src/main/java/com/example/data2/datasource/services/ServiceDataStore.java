package com.example.data2.datasource.services;

import com.example.data2.entities.services.ServiceCategoryData;
import com.example.data2.entities.services.ServiceData;
import com.example.data2.entities.services.ServiceDetailsData;

import io.reactivex.Observable;

public interface ServiceDataStore {


    Observable<ServiceCategoryData> getServicesCategories(String lang);

    Observable<ServiceData> getAllServices(String lang, String category_id);


    Observable<ServiceDetailsData> getServiceDetails(String lang, int serviceId);
}
