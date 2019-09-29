package com.example.todo.data;

import com.example.todo.data.remote.model.TodoResponse;
import com.example.todo.views.uimodel.LaterTaskModel;
import com.example.todo.views.uimodel.TodayTaskModel;
import com.example.todo.views.uimodel.TodoUIModel;

import java.util.List;

/*
This interface contains methods to interact with the inner layer
 */
public interface Repository {
    void getTodoTaskList(Callback<List<TodoResponse>> callback);

    List<TodoUIModel> getTodayTasks(List<TodoResponse> response);

    List<TodoUIModel> getLaterTasks(List<TodoResponse> response);

    List<TodoUIModel> getPendingItems(List<TodoUIModel> response);

    List<TodoUIModel> getCompleted(List<TodoUIModel> response);

}
