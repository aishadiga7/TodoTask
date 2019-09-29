package com.example.todo.common;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.todo.di.Injector;
import com.example.todo.viewmodel.HomeViewModel;

public class AppViewModelFacotry implements ViewModelProvider.Factory {


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
       if (modelClass == HomeViewModel.class) {
           return (T) new HomeViewModel(Injector.getRepository());
       }
       return null;
    }
}
