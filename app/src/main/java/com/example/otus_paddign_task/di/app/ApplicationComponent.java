package com.example.otus_paddign_task.di.app;

import android.app.Application;

import com.example.common.di.CommonComponent;
import com.example.common.di.modules.NetworkModule;
import com.example.common.di.modules.RepositoryModule;
import com.example.common.di.modules.ServerUrl;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class, RepositoryModule.class})
public interface ApplicationComponent extends CommonComponent {
    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder application(Application application);

        @BindsInstance
        Builder serverUrl(@ServerUrl String serverUrl);

        ApplicationComponent build();
    }
}
