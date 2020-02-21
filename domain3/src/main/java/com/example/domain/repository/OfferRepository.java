package com.example.domain.repository;

import com.example.domain.entities.Offer;
import com.example.domain.entities.Response;

import java.util.ArrayList;

import io.reactivex.Observable;

public interface OfferRepository {

    Observable<Response> getAllOffers(String lang);
}
