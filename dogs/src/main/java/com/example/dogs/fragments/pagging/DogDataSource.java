package com.example.dogs.fragments.pagging;

import com.example.common.data.DogResponse;
import com.example.common.network.repository.DogRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PositionalDataSource;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class DogDataSource extends PositionalDataSource<String> {
    private DogRepository dogRepository;
    private CompositeDisposable compositeDisposable;

    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public DogDataSource(DogRepository dogRepository, CompositeDisposable compositeDisposable) {
        this.dogRepository = dogRepository;
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<String> callback) {

        dogRepository.createDogArray();

        compositeDisposable.add(dogRepository.getDogs(params.requestedStartPosition, params.requestedLoadSize)
        .observeOn(Schedulers.io())
        .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(List<String> dogs) throws Exception {
                        callback.onResult(dogs, params.requestedStartPosition, dogRepository.getDogCount());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                }));

    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<String> callback) {
        compositeDisposable.add(dogRepository.getDogs(params.startPosition, params.loadSize)
        .observeOn(Schedulers.io())
        .subscribeOn(AndroidSchedulers.mainThread())
        .subscribe(dogs -> callback.onResult(dogs)));
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }


}
