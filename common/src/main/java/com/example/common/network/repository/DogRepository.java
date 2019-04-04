package com.example.common.network.repository;

import com.example.common.data.DogResponse;
import com.example.common.network.api.DogApiService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DogRepository {
    private DogApiService dogApiService;
    private List<String> dogList = new ArrayList<>();

    public int dogCount;
    private static final int DELAY_SEC = 3;

    public DogRepository(DogApiService dogApiService) {
        this.dogApiService = dogApiService;
        createDogArray();
    }

    public Observable<DogResponse> getRandom(){
        return dogApiService.getRandom();
    }

    public Observable<List<String>> getDogList(){
        return getRandom().map(dogResponse -> dogResponse.getMessage());
    }
    public void createDogArray(){
        getDogList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(strings -> {
                    setDogList(strings);
                    setDogCount(strings.size());
                });

    }
    public void setDogCount(int dogCount) {
        this.dogCount = dogCount;
    }
    public void setDogList(List<String> dogList) {
        this.dogList = dogList;
    }
    public int getDogCount() {
        return dogCount;
    }
    public Observable<List<String>> getDogs(int startPos, int size){
        return Observable.just(dogList.subList(startPos, size))
                .delay(DELAY_SEC, TimeUnit.SECONDS);
    }
}
