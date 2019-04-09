package com.example.dogs.di;

import com.example.common.network.repository.DogRepository;
import com.example.dogs.fragments.DogListFragment;
import com.example.dogs.fragments.pagging.DogViewModel;

import javax.inject.Singleton;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import dagger.Module;
import dagger.Provides;

@Module
public class DogModule {
    @Provides
    @DogScope
    public DogViewModel provideDogViewModel(DogListFragment host,
                                            final DogRepository dogRepository){
        return ViewModelProviders.of(host, new ViewModelProvider.Factory() {
            @NonNull
            @Singleton
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new DogViewModel(dogRepository);
            }
        }).get(DogViewModel.class);
    }

}
