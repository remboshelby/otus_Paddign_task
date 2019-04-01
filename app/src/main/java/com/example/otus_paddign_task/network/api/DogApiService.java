package com.example.otus_paddign_task.network.api;

import com.example.otus_paddign_task.common.repository.DogRepository;
import com.example.otus_paddign_task.network.data.RandomDogData;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface DogApiService {
    @GET("/breed/Shiba/images/random")
    Observable<RandomDogData> getRandom();
}
