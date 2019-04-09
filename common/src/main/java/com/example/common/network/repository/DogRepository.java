package com.example.common.network.repository;

import com.example.common.data.DogResponse;
import com.example.common.network.api.DogApiService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class DogRepository {
    private DogApiService dogApiService;
    private List<String> dogList = new ArrayList<>();

    public int dogCount;
    private static final int DELAY_SEC = 3;

    public DogRepository(DogApiService dogApiService) {
        this.dogApiService = dogApiService;
    }

    public Observable<DogResponse> getRandom() {
        return dogApiService.getRandom();
    }

    public Observable<List<String>> getDogList() {
        return getRandom().map(new Function<DogResponse, List<String>>() {
            @Override
            public List<String> apply(DogResponse dogResponse) throws Exception {
                return dogResponse.getMessage();
            }
        });
    }

    public void createDogArray() {
        getDogList().doOnNext(strings -> {
            setDogList(strings);
            setDogCount(strings.size());
        });
    }

    public int getDogCount() {
        return dogCount;
    }

    public Observable<List<String>> getDogs(int startPos, int size) {
        return getDogList().doOnNext(strings -> {
            setDogList(strings);
            setDogCount(strings.size());
        }).map(new Function<List<String>, List<String>>() {
            @Override
            public List<String> apply(List<String> strings) throws Exception {
                return strings.subList(startPos, size);
            }
        });
    }

    public void setDogCount(int dogCount) {
        this.dogCount = dogCount;
    }

    public void setDogList(List<String> dogList) {
        this.dogList.addAll(dogList);
    }
}
