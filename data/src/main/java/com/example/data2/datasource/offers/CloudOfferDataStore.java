package com.example.data2.datasource.offers;

import com.example.data2.datasource.offers.OfferDataStore;
import com.example.data2.entities.offers.OfferData;
import com.example.data2.network.APIClient;
import com.example.data2.network.APIInterface;

import javax.inject.Inject;

import io.reactivex.Observable;

public class CloudOfferDataStore implements OfferDataStore {

    APIInterface apiInterface;

    @Inject
    public CloudOfferDataStore() {
        this.apiInterface = APIClient.getClient().create(APIInterface.class);

    }

    @Override
    public Observable<OfferData> getAllOffers(String lang) {
        return apiInterface.getAllOffers(lang);
    }
}
