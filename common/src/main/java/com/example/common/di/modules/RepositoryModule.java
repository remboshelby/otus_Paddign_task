package com.example.common.di.modules;

import com.example.common.network.api.DogApiService;
import com.example.common.network.repository.DogRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {
    @Provides
    DogRepository dogRepository(DogApiService dogApiService){
        return new DogRepository(dogApiService);
    }
}
