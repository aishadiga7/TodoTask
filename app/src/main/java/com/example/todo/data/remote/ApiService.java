package com.example.todo.data.remote;


import com.example.todo.data.remote.model.TodoResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("v2/5a8e5b372f000048004f25fc")
    Call<List<TodoResponse>> getTodoData();
}
