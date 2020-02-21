package com.example.data2.reprositoryimpl;

import com.example.data2.datasource.offers.CloudOfferDataStore;
import com.example.data2.datasource.products.CloudProductDataStore;
import com.example.data2.entities.products.ProductCategoryData;
import com.example.data2.entities.products.ProductData;
import com.example.data2.entities.products.ProductDetailsData;
import com.example.data2.mapper.RemoteToLocalMapper;
import com.example.domain.entities.Product;
import com.example.domain.entities.ProductCategory;
import com.example.domain.entities.Response;
import com.example.domain.repository.ProductRepository;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class ProductRepositoryImpl implements ProductRepository {

    @Inject
    CloudProductDataStore cloudProductDataStore;

    @Inject
    RemoteToLocalMapper remoteToLocalMapper;

    @Inject
    public ProductRepositoryImpl() {
    }

    @Override
    public Observable<Response> getProductCategories(String lang) {
        return cloudProductDataStore.getProductsCategories(lang).map(new Function<ProductCategoryData, Response>() {
            @Override
            public Response apply(ProductCategoryData productCategoryData) throws Exception {
                return new Response(remoteToLocalMapper.fromRemoteToLocalProductCategoryList(productCategoryData.getData()),productCategoryData.getResponseMessage());
            }
        });
    }

    @Override
    public Observable<Response> getAllProducts(String lang, String categoryId) {
        return cloudProductDataStore.getAllProducts(lang, categoryId).map(new Function<ProductData, Response>() {
            @Override
            public Response apply(ProductData productData) throws Exception {
                return new Response(remoteToLocalMapper.fromRemoteToLocalProductList(productData.getData()),productData.getResponseMessage());
            }
        });
    }

    @Override
    public Observable<Product> getProductDetails(String lang, String productId) {
        return cloudProductDataStore.getProductDetails(lang, productId).map(new Function<ProductDetailsData, Product>() {
            @Override
            public Product apply(ProductDetailsData productDetailsData) throws Exception {
                return remoteToLocalMapper.fromRemoteToLocalProduct(productDetailsData.getData());
            }
        });
    }
}
