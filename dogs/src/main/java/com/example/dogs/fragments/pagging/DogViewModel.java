package com.example.dogs.fragments.pagging;

import com.example.common.base.BaseViewModel;
import com.example.common.data.DogResponse;
import com.example.common.network.repository.DogRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class DogViewModel extends BaseViewModel {
    private DogRepository dogRepository;
    public List<String> message = new ArrayList<>();

    public DogViewModel(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }
    public void getDogs(){
        addDisposible(dogRepository.getRandom()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dogResponse -> message = dogResponse.getMessage(),
                        throwable -> throwable.printStackTrace()));
    }
}
