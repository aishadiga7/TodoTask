package com.example.todo.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.todo.data.Callback;
import com.example.todo.data.Repository;
import com.example.todo.data.remote.model.TodoResponse;
import com.example.todo.views.uimodel.LaterTaskModel;
import com.example.todo.views.uimodel.TodayTaskModel;
import com.example.todo.views.uimodel.TodoUIModel;

import java.util.List;

public class HomeViewModel extends BaseViewModel {

    private Repository repository;
    MutableLiveData<List<TodoUIModel>> todayLiveData = new MutableLiveData<>();
    MutableLiveData<List<TodoUIModel>> laterLiveData = new MutableLiveData<>();


    public HomeViewModel(Repository repository) {
        this.repository = repository;
    }

    public LiveData<List<TodoUIModel>> getTodayLiveData() {
        return todayLiveData;
    }

    public LiveData<List<TodoUIModel>> getLaterLiveData() {
        return laterLiveData;
    }

    public void getTodoTaskData() {
        setProgress(true);
        repository.getTodoTaskList(new Callback<List<TodoResponse>>() {
            @Override
            public void onSuccess(List<TodoResponse> result) {
              setProgress(false);
              todayLiveData.setValue(repository.getTodayTasks(result));
              laterLiveData.setValue(repository.getLaterTasks(result));
            }

            @Override
            public void onError(Throwable error) {
                setProgress(false);
            }
        });
    }

    public List<TodoUIModel> getPendingItemList(List<TodoUIModel> todoUIModel) {
        return repository.getPendingItems(todoUIModel);
    }

    public List<TodoUIModel> getCompletedItemList(List<TodoUIModel> todoUIModel) {
        return repository.getCompleted(todoUIModel);
    }



}
