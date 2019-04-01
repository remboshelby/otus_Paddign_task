package com.example.otus_paddign_task.network.repository;

import com.example.otus_paddign_task.network.api.DogApiService;
import com.example.otus_paddign_task.network.data.RandomDogData;

import io.reactivex.Observable;

public class DogRepository {
    private DogApiService dogApiService;

    public DogRepository(DogApiService dogApiService) {
        this.dogApiService = dogApiService;
    }
    public Observable<RandomDogData> getRandom(){
        return dogApiService.getRandom();
    }
}
