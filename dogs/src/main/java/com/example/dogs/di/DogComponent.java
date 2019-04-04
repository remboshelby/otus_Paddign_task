package com.example.dogs.di;

import com.example.common.di.CommonComponent;
import com.example.dogs.fragments.DogListFragment;

import dagger.BindsInstance;
import dagger.Component;

@Component(dependencies = CommonComponent.class, modules = {DogModule.class})
public interface DogComponent {
    void inject(DogListFragment dogListFragment);

    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder root(DogListFragment dogListFragment);

        Builder commonComponent(CommonComponent commonComponent);

        DogComponent build();
    }
}
