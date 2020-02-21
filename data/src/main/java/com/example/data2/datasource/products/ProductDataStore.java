package com.example.data2.datasource.products;

import com.example.data2.entities.products.ProductCategoryData;
import com.example.data2.entities.products.ProductData;
import com.example.data2.entities.products.ProductDetailsData;

import io.reactivex.Observable;
import retrofit2.http.Query;

public interface ProductDataStore {

    Observable<ProductCategoryData> getProductsCategories(String lang);

    Observable<ProductData> getAllProducts(String lang,String categoryId);

    Observable<ProductDetailsData> getProductDetails(String lang,String productId);
}
