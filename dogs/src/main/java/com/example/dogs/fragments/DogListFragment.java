package com.example.dogs.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.common.base.BaseFragment;
import com.example.common.di.CommonApplication;
import com.example.common.di.CommonComponent;
import com.example.dogs.R;
import com.example.dogs.fragments.di.DaggerDogComponent;
import com.example.dogs.fragments.di.DogComponent;
import com.example.dogs.fragments.pagging.DogViewModel;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DogListFragment extends BaseFragment {
    public static DogComponent dogComponent;
    @Inject
    protected DogViewModel dogViewModel;
    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.dogs_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        dogViewModel.getDogs();

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void inject() {
        CommonApplication commonApplication = (CommonApplication)getRoot().getApplication();

        dogComponent = DaggerDogComponent
                .builder()
                .commonComponent(commonApplication.component())
                .root(this)
                .build();

        dogComponent.inject(this);
    }

}
