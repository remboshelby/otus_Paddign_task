package com.example.common.di;

import com.example.common.network.repository.DogRepository;

public interface CommonComponent {
    DogRepository dogRepository();
}
