package com.example.otus_paddign_task.di;

import android.app.Application;

import com.example.common.di.CommonApplication;
import com.example.common.di.CommonComponent;
import com.example.otus_paddign_task.R;
import com.example.otus_paddign_task.di.app.ApplicationComponent;
import com.example.otus_paddign_task.di.app.DaggerApplicationComponent;

public class App extends Application implements CommonApplication {
    private static ApplicationComponent applicationComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        makeComponent();
    }

    private void makeComponent() {
        applicationComponent = DaggerApplicationComponent
                .builder()
                .application(this)
                .serverUrl(getString(R.string.server_url))
                .build();
    }

    @Override
    public CommonComponent component() {
        return applicationComponent;
    }
}
