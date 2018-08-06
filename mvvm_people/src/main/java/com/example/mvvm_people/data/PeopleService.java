package com.example.mvvm_people.data;

import retrofit2.http.GET;
import retrofit2.http.Url;
import io.reactivex.Observable;

public interface PeopleService {
    @GET Observable<PeopleResponse> fetchPeople(@Url String url);
}

