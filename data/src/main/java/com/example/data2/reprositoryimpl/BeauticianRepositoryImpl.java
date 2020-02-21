package com.example.data2.reprositoryimpl;

import com.example.data2.datasource.beauticians.CloudBeauticianDataSource;
import com.example.data2.datasource.offers.CloudOfferDataStore;
import com.example.data2.entities.beauticians.BeauticiansData;
import com.example.data2.mapper.RemoteToLocalMapper;
import com.example.domain.entities.Beautician;
import com.example.domain.entities.Response;
import com.example.domain.repository.BeauticianRepository;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class BeauticianRepositoryImpl implements BeauticianRepository {


    @Inject
    CloudBeauticianDataSource cloudBeauticianDataSource;

    @Inject
    RemoteToLocalMapper remoteToLocalMapper;

    @Inject
    public BeauticianRepositoryImpl() {
    }


    @Override
    public Observable<Response> getBeauticians(String lang) {
        return cloudBeauticianDataSource.getBeauticians(lang).map(new Function<BeauticiansData,Response>() {
            @Override
            public Response apply(BeauticiansData beauticiansData) throws Exception {
                return new Response(remoteToLocalMapper.fromRemoteToLocalBeauticiansList(beauticiansData.getData()),beauticiansData.getResponseMessage());
            }
        });
    }
}
