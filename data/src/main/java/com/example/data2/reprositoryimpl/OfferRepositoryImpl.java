package com.example.data2.reprositoryimpl;

import com.example.data2.datasource.offers.CloudOfferDataStore;
import com.example.data2.entities.offers.OfferData;
import com.example.data2.mapper.RemoteToLocalMapper;
import com.example.domain.entities.Offer;
import com.example.domain.entities.Response;
import com.example.domain.repository.OfferRepository;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class OfferRepositoryImpl implements OfferRepository {

    @Inject
    CloudOfferDataStore cloudOfferDataStore;

    @Inject
    RemoteToLocalMapper remoteToLocalMapper;

    @Inject
    public OfferRepositoryImpl() {
    }

    @Override
    public Observable<Response> getAllOffers(String lang) {
        return cloudOfferDataStore.getAllOffers(lang).map(new Function<OfferData,Response>() {
            @Override
            public Response apply(OfferData offerData) throws Exception {
                return new Response(remoteToLocalMapper.fromRemoteToLocalListOfOffers(offerData.getData()),offerData.getResponseMessage());
            }
        });
    }
}
