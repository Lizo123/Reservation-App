package com.example.domain.repository;

import com.example.domain.entities.Product;
import com.example.domain.entities.ProductCategory;
import com.example.domain.entities.Response;

import java.util.ArrayList;

import io.reactivex.Observable;

public interface ProductRepository {

    Observable<Response> getProductCategories(String lang);

    Observable<Response> getAllProducts(String lang,String categoryId);

    Observable<Product> getProductDetails(String lang,String productId);
}
