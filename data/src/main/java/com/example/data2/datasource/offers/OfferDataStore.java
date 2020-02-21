package com.example.data2.datasource.offers;

import com.example.data2.entities.offers.OfferData;

import io.reactivex.Observable;

public interface OfferDataStore {

    Observable<OfferData> getAllOffers(String lang);


}
