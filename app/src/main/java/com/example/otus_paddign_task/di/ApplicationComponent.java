package com.example.otus_paddign_task.di;

import android.app.Application;

import com.example.otus_paddign_task.di.modules.NetworkModule;
import com.example.otus_paddign_task.di.modules.RepositoryModule;
import com.example.otus_paddign_task.di.modules.ServerUrl;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class, RepositoryModule.class})
public interface ApplicationComponent {
    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder application(Application application);

        @BindsInstance
        Builder serverUrl(@ServerUrl String serverUrl);

        ApplicationComponent build();
    }
}
