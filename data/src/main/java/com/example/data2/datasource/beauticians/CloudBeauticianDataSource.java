package com.example.data2.datasource.beauticians;

import com.example.data2.entities.beauticians.BeauticiansData;
import com.example.data2.network.APIClient;
import com.example.data2.network.APIInterface;

import javax.inject.Inject;

import io.reactivex.Observable;

public class CloudBeauticianDataSource implements BeauticiansDataSource {

    private APIInterface apiInterface;

    @Inject
    CloudBeauticianDataSource() {
        this.apiInterface = APIClient.getClient().create(APIInterface.class);

    }

    @Override
    public Observable<BeauticiansData> getBeauticians(String lang) {
        return apiInterface.getBeauticians(lang) ;
    }
}
