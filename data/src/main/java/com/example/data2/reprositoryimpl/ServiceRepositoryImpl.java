package com.example.data2.reprositoryimpl;

import com.example.data2.datasource.services.CloudServiceDataStore;
import com.example.data2.entities.services.ServiceCategoryData;
import com.example.data2.entities.services.ServiceData;
import com.example.data2.entities.services.ServiceDetailsData;
import com.example.data2.mapper.RemoteToLocalMapper;
import com.example.domain.entities.Response;
import com.example.domain.entities.Service;
import com.example.domain.entities.ServiceCategory;
import com.example.domain.repository.ServiceRepository;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class ServiceRepositoryImpl implements ServiceRepository {


    @Inject
    CloudServiceDataStore cloudServiceDataStore;

    @Inject
    RemoteToLocalMapper remoteToLocalMapper;

    @Inject
    public ServiceRepositoryImpl() {

    }


    @Override
    public Observable<Response> getServicesCategories(String lang) {
        return cloudServiceDataStore.getServicesCategories(lang).map(new Function<ServiceCategoryData, Response>() {
            @Override
            public Response apply(ServiceCategoryData serviceCategoryData) throws Exception {
                return new Response(remoteToLocalMapper.fromRemoteToLocalServiceCategoryList(serviceCategoryData.getData()),serviceCategoryData.getResponseMessage());
            }
        });
    }

    @Override
    public Observable<Response> getAllServices(String lang, String category_id) {
        return cloudServiceDataStore.getAllServices(lang,category_id).map(new Function<ServiceData, Response>() {
            @Override
            public Response apply(ServiceData serviceData) throws Exception {
                return new Response(remoteToLocalMapper.fromRemoteToLocalListOfServices(serviceData.getData()),serviceData.getResponseMessage());
            }
        });
    }

    @Override
    public Observable<Service> getServiceDetails(String lang, int serviceId) {
        return cloudServiceDataStore.getServiceDetails(lang,serviceId).map(new Function<ServiceDetailsData, Service>() {
            @Override
            public Service apply(ServiceDetailsData serviceData) throws Exception {
                return remoteToLocalMapper.fromRemoteToLocalService(serviceData.getData());
            }
        });
    }
}
