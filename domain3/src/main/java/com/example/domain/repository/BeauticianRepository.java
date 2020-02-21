package com.example.domain.repository;

import com.example.domain.entities.Beautician;
import com.example.domain.entities.Response;

import java.util.ArrayList;

import io.reactivex.Observable;

public interface BeauticianRepository {


    Observable<Response> getBeauticians(String lang);
}
