package com.example.dogs.fragments.pagging;

import com.example.common.network.repository.DogRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.paging.PositionalDataSource;

public class DogDataSource extends PositionalDataSource<List<String>> {
    private DogRepository dogRepository;

    public DogDataSource(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<List<String>> callback) {

    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<List<String>> callback) {

    }
}
