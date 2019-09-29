package com.example.todo.di;

import com.example.todo.common.ApiConstants;
import com.example.todo.common.App;
import com.example.todo.common.AppViewModelFacotry;
import com.example.todo.data.MainRepositoryImpl;
import com.example.todo.data.Repository;
import com.example.todo.data.remote.ApiService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Injector {
    public static Repository repository;
    public static ApiService apiService;
    public static AppViewModelFacotry appViewModelFacotry;



    public static void init() {

        initRetrofit();
        initViewModelFactory();
        initRepository();
    }

    private static void initRepository() {
        if(repository == null)
            repository = new MainRepositoryImpl(apiService);
    }


    private static void initViewModelFactory() {
        appViewModelFacotry = new AppViewModelFacotry();
    }

    public static AppViewModelFacotry getAppViewModelFacotry() {
        return appViewModelFacotry;
    }

    private static void initRetrofit() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.level(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public static Repository getRepository() {
        return repository;
    }

    public static ApiService getApiService() {
        return apiService;
    }
}
