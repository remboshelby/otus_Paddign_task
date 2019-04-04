package com.example.dogs.fragments.pagging;

import com.example.common.base.BaseViewModel;
import com.example.common.data.DogResponse;
import com.example.common.network.repository.DogRepository;

import java.util.ArrayList;
import java.util.List;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class DogViewModel extends BaseViewModel {
    private static final int PER_PAGE = 10;

    private DogRepository dogRepository;
//    public List<String> message = new ArrayList<>();

    private LiveData<PagedList<String>> dogs;

    private LiveData<Boolean> isLoading;

    public DogViewModel(DogRepository dogRepository) {
        this.dogRepository = dogRepository;

        DogDataSourceFactory dogDataSourceFactory = new DogDataSourceFactory(dogRepository, getCompositeDisposable());

        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(PER_PAGE)
                .build();

        dogs = new LivePagedListBuilder<>(dogDataSourceFactory, config).build();

        isLoading = Transformations.switchMap(dogDataSourceFactory.getDogDataSourceMutableLiveData(), DogDataSource::getIsLoading);
    }

    public LiveData<PagedList<String>> getDogs() {
        return dogs;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    //    public void getDogs(){
//        addDisposible(dogRepository.getRandom()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(dogResponse -> message = dogResponse.getMessage(),
//                        throwable -> throwable.printStackTrace()));
//    }
    @Override
    public void onViewCreated() {
    }
}
