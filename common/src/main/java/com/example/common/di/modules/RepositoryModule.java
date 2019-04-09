package com.example.common.di.modules;

import com.example.common.network.api.DogApiService;
import com.example.common.network.repository.DogRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {
    @Singleton
    @Provides
    DogRepository dogRepository(DogApiService dogApiService){
        return new DogRepository(dogApiService);
    }
}
