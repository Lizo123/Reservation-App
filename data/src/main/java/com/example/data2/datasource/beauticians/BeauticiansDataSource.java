package com.example.data2.datasource.beauticians;

import com.example.data2.entities.beauticians.BeauticiansData;

import java.util.ArrayList;

import io.reactivex.Observable;

public interface BeauticiansDataSource {

    Observable<BeauticiansData> getBeauticians(String lang);
}
