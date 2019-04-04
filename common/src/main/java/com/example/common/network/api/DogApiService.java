package com.example.common.network.api;


import com.example.common.data.DogResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface DogApiService {
    @GET("breed/hound/images")
    Observable<DogResponse> getRandom();
}
