package com.example.todo.common;

import android.app.Application;

import com.example.todo.di.Injector;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
       Injector.init();
    }
}
