package com.example.otus_paddign_task.di;

import android.app.Application;

import com.example.otus_paddign_task.R;

public class App extends Application {
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
}
