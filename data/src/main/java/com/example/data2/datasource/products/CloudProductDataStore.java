package com.example.data2.datasource.products;

import com.example.data2.entities.products.ProductCategoryData;
import com.example.data2.entities.products.ProductData;
import com.example.data2.entities.products.ProductDetailsData;
import com.example.data2.network.APIClient;
import com.example.data2.network.APIInterface;

import javax.inject.Inject;

import io.reactivex.Observable;

public class CloudProductDataStore implements ProductDataStore {

    APIInterface apiInterface;

    @Inject
    CloudProductDataStore() {
        this.apiInterface = APIClient.getClient().create(APIInterface.class);

    }
    @Override
    public Observable<ProductCategoryData> getProductsCategories(String lang) {
        return apiInterface.getProductCategories(lang);
    }

    @Override
    public Observable<ProductData> getAllProducts(String lang, String categoryId) {
        return apiInterface.getAllProducts(lang,categoryId);
    }

    @Override
    public Observable<ProductDetailsData> getProductDetails(String lang, String productId) {
        return apiInterface.getProductDetails(lang, productId);
    }
}
