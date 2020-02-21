package com.example.data2.network;

import com.example.data2.entities.APIResponse;
import com.example.data2.entities.beauticians.BeauticiansData;
import com.example.data2.entities.offers.OfferData;
import com.example.data2.entities.products.ProductCategoryData;
import com.example.data2.entities.products.ProductData;
import com.example.data2.entities.products.ProductDetailsData;
import com.example.data2.entities.services.ServiceCategoryData;
import com.example.data2.entities.services.ServiceData;
import com.example.data2.entities.services.ServiceDetailsData;
import com.example.data2.entities.user.UserData;
import com.example.data2.entities.user.UserMobile;

import io.reactivex.Observable;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIInterface {

  //Register Api
   @Headers("Authorization: Basic bW9oYW1tZWRnaG9uZW1AZ21haWwuY29tOlRoZXNwYWY3ZzloaHNkN2ZzZnNkZg==")
    @POST("/api/customers/register")
    Observable<UserMobile> register(@Query("full_name") String name, @Query("mobile") String mobile);

   //SignIn Api
  @Headers("Authorization: Basic bW9oYW1tZWRnaG9uZW1AZ21haWwuY29tOlRoZXNwYWY3ZzloaHNkN2ZzZnNkZg==")
    @POST("/api/customers/login")
  UserData signIn(@Query("full_name") String name, @Query("mobile") String mobile);

    //Send Activation Code Api
  @Headers("Authorization: Basic bW9oYW1tZWRnaG9uZW1AZ21haWwuY29tOlRoZXNwYWY3ZzloaHNkN2ZzZnNkZg==")
  @POST("api/customers/send_activation_code")
  Observable<UserMobile> sendActivationCode(@Query("mobile") String mobile);

  //Activate
    @Headers("Authorization: Basic bW9oYW1tZWRnaG9uZW1AZ21haWwuY29tOlRoZXNwYWY3ZzloaHNkN2ZzZnNkZg==")
    @POST("api/customers/activation")
    Observable<UserData> activation(@Query("mobile") String mobile,@Query("activation_code") String code);


    //SetPassword
    @Headers("Authorization: Basic bW9oYW1tZWRnaG9uZW1AZ21haWwuY29tOlRoZXNwYWY3ZzloaHNkN2ZzZnNkZg==")
    @POST("api/customers/set_password")
    Observable<APIResponse> setPassword(@Query("mobile") String mobile,@Query("new_password") String password);


    //ForgetPassword
    @Headers("Authorization: Basic bW9oYW1tZWRnaG9uZW1AZ21haWwuY29tOlRoZXNwYWY3ZzloaHNkN2ZzZnNkZg==")
    @POST("api/customers/forgot_password")
    Observable<UserMobile> forgetPassword(@Query("mobile") String mobile);

    //ResetPassword
    @Headers("Authorization: Basic bW9oYW1tZWRnaG9uZW1AZ21haWwuY29tOlRoZXNwYWY3ZzloaHNkN2ZzZnNkZg==")
    @POST("api/customers/reset_password")
    Observable<APIResponse> resetPassword(@Query("forgetting_password_code") String code,@Query("mobile") String mobile,@Query("new_password") String password);

    //GetAllOffers
  @Headers("Authorization: Basic bW9oYW1tZWRnaG9uZW1AZ21haWwuY29tOlRoZXNwYWY3ZzloaHNkN2ZzZnNkZg==")
  @POST("api/offers/get_all")
  Observable<OfferData> getAllOffers(@Query("lang") String lang);

  //GetAllServicesCategories
  @Headers("Authorization: Basic bW9oYW1tZWRnaG9uZW1AZ21haWwuY29tOlRoZXNwYWY3ZzloaHNkN2ZzZnNkZg==")
  @POST("api/services_categories/get_all")
  Observable<ServiceCategoryData> getAllServicesCategories(@Query("lang") String lang);

  //GetAllServices
  @Headers("Authorization: Basic bW9oYW1tZWRnaG9uZW1AZ21haWwuY29tOlRoZXNwYWY3ZzloaHNkN2ZzZnNkZg==")
  @POST("api/services/get_all")
  Observable<ServiceData> getAllServices(@Query("lang") String lang,@Query("category_id") String category_id);

  //GetServiceDetails
  @Headers("Authorization: Basic bW9oYW1tZWRnaG9uZW1AZ21haWwuY29tOlRoZXNwYWY3ZzloaHNkN2ZzZnNkZg==")
  @POST("api/services/get_details")
  Observable<ServiceDetailsData> getServiceDetails(@Query("lang") String lang, @Query("service_id") int id);

  //GetProductsCategories
  @Headers("Authorization: Basic bW9oYW1tZWRnaG9uZW1AZ21haWwuY29tOlRoZXNwYWY3ZzloaHNkN2ZzZnNkZg==")
  @POST("api/products_categories/get_all")
  Observable<ProductCategoryData> getProductCategories(@Query("lang") String lang);

  //GetProducts
  @Headers("Authorization: Basic bW9oYW1tZWRnaG9uZW1AZ21haWwuY29tOlRoZXNwYWY3ZzloaHNkN2ZzZnNkZg==")
  @POST("api/products/get_all")
  Observable<ProductData> getAllProducts(@Query("lang") String lang,@Query("category_id") String category_id);

  //GetProductDetails
  @Headers("Authorization: Basic bW9oYW1tZWRnaG9uZW1AZ21haWwuY29tOlRoZXNwYWY3ZzloaHNkN2ZzZnNkZg==")
  @POST("api/products/get_details")
  Observable<ProductDetailsData> getProductDetails(@Query("lang") String lang,@Query("product_id") String product_id);

  //Get Beauticians
  @Headers("Authorization: Basic bW9oYW1tZWRnaG9uZW1AZ21haWwuY29tOlRoZXNwYWY3ZzloaHNkN2ZzZnNkZg==")
  @POST("api/specialists/get_all")
  Observable<BeauticiansData> getBeauticians(@Query("lang") String lang);


  //SetFavourite
  @Headers("Authorization: Basic bW9oYW1tZWRnaG9uZW1AZ21haWwuY29tOlRoZXNwYWY3ZzloaHNkN2ZzZnNkZg==")
  @POST("api/favorites/add_favorite")
  Observable<APIResponse> setFavorite(@Query("customer_id") String customer_id,@Query("service_id") String service_id);

  //RemoveFavourite
  @Headers("Authorization: Basic bW9oYW1tZWRnaG9uZW1AZ21haWwuY29tOlRoZXNwYWY3ZzloaHNkN2ZzZnNkZg==")
  @POST("api/favorites/remove_favorite")
  Observable<APIResponse> removeFavorite(@Query("customer_id") String customer_id,@Query("service_id") String service_id);

  //cart
//  @Headers("Authorization: Basic bW9oYW1tZWRnaG9uZW1AZ21haWwuY29tOlRoZXNwYWY3ZzloaHNkN2ZzZnNkZg==")
//  @POST("api/favorites/remove_favorite")
//  Observable<APIResponse> removeFavorite(@Query("customer_id") String customer_id,@Query("service_id") String service_id);

    //GetUserFavourite
    @Headers("Authorization: Basic bW9oYW1tZWRnaG9uZW1AZ21haWwuY29tOlRoZXNwYWY3ZzloaHNkN2ZzZnNkZg==")
    @POST("api/customers/get_customer_favorites")
    Observable<ServiceData> getUserFavoriites(@Query("lang") String lang, @Query("customer_id") String customer_id);

}
