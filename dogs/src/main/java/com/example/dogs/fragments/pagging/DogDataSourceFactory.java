package com.example.dogs.fragments.pagging;

import com.example.common.network.repository.DogRepository;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import io.reactivex.disposables.CompositeDisposable;

public class DogDataSourceFactory extends DataSource.Factory<Integer, String> {
    private DogRepository dogRepository;
    private CompositeDisposable compositeDisposable;
    private MutableLiveData<DogDataSource> dogDataSourceMutableLiveData;

    public DogDataSourceFactory(DogRepository dogRepository, CompositeDisposable compositeDisposable) {
        this.dogRepository = dogRepository;
        this.compositeDisposable = compositeDisposable;
        this.dogDataSourceMutableLiveData = new MutableLiveData<>();
    }

    @Override
    public DataSource<Integer, String> create() {
        DogDataSource dogDataSource = new DogDataSource(dogRepository,compositeDisposable);
        dogDataSourceMutableLiveData.postValue(dogDataSource);
        return dogDataSource;
    }

    public MutableLiveData<DogDataSource> getDogDataSourceMutableLiveData() {
        return dogDataSourceMutableLiveData;
    }
}
