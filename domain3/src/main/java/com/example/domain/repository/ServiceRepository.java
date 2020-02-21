package com.example.domain.repository;

import com.example.domain.entities.Response;
import com.example.domain.entities.Service;
import com.example.domain.entities.ServiceCategory;

import java.util.ArrayList;

import io.reactivex.Observable;

public interface ServiceRepository {

    Observable<Response> getServicesCategories(String lang);

    Observable<Response> getAllServices(String lang, String category_id);

    Observable<Service> getServiceDetails(String lang,int serviceId);
}
