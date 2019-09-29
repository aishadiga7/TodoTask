package com.example.todo.data;

import android.text.format.DateUtils;
import android.util.Log;

import androidx.cardview.widget.CardView;

import com.example.todo.common.Constants;
import com.example.todo.data.remote.ApiService;
import com.example.todo.data.remote.model.TodoResponse;
import com.example.todo.utils.CalenderUtils;
import com.example.todo.views.uimodel.LaterTaskModel;
import com.example.todo.views.uimodel.TodayTaskModel;
import com.example.todo.views.uimodel.TodoUIModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/*
This class will iomplement the repository interface and doers the actual logic of it
 */
public class MainRepositoryImpl implements Repository {


    private ApiService apiService;
    List<TodoUIModel> todayTaskList = new ArrayList<>();
    List<TodoUIModel> laterTaskList = new ArrayList<>();

    public MainRepositoryImpl(ApiService apiService) {
        this.apiService = apiService;
    }


    @Override
    public void getTodoTaskList(Callback<List<TodoResponse>> callback) {
        apiService.getTodoData().enqueue(new retrofit2.Callback<List<TodoResponse>>() {
            @Override
            public void onResponse(Call<List<TodoResponse>> call, Response<List<TodoResponse>> response) {
                if (response != null && response.isSuccessful()) {
                    setTaskBasedOnDate(response.body());
                    if (callback != null) {
                        callback.onSuccess(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<TodoResponse>> call, Throwable error) {
                if (callback != null) {
                    callback.onError(error);
                }
            }
        });
    }


    public void setTaskBasedOnDate(List<TodoResponse> response) {
        for (TodoResponse todoResponse: response) {
            TodoUIModel todoUIModel  =  new TodoUIModel();
            if (CalenderUtils.isToday(CalenderUtils.getDate(todoResponse.getTaskScheduledDate()).getTime())) {
                Log.d("date:", "today");
                todoUIModel.setTaskDescription(todoResponse.getTaskDescription());
                todoUIModel.setDate(CalenderUtils.convertStringDateToAnotherStringDate(todoResponse.getTaskScheduledDate()));
                todoUIModel.setStatus(todoResponse.getTaskStatus());
                todayTaskList.add(todoUIModel);
            } else {
                Log.d("date:", "later");
                todoUIModel.setTaskDescription(todoResponse.getTaskDescription());
                todoUIModel.setDate(CalenderUtils.convertStringDateToAnotherStringDate(todoResponse.getTaskScheduledDate()));
                todoUIModel.setStatus(todoResponse.getTaskStatus());
                laterTaskList.add(todoUIModel);
            }
        }
    }


    @Override
    public List<TodoUIModel> getTodayTasks(List<TodoResponse> response) {
        return todayTaskList;
    }

    @Override
    public List<TodoUIModel> getLaterTasks(List<TodoResponse> response) {
        return laterTaskList;
    }

    @Override
    public List<TodoUIModel> getPendingItems(List<TodoUIModel> response) {
        List<TodoUIModel> pendingItemList = new ArrayList<>();
        if (response != null) {
            for (TodoUIModel todoUIModel : response) {
                if (todoUIModel.getStatus().equalsIgnoreCase(Constants.TASK_STATUS_PENDING)) {
                    pendingItemList.add(todoUIModel);
                }
            }
            return pendingItemList;
        }
        return null;
    }

    @Override
    public List<TodoUIModel> getCompleted(List<TodoUIModel> response) {
        List<TodoUIModel> completedItemList = new ArrayList<>();
        if (response != null) {
            for (TodoUIModel todoUIModel : response) {
                if (todoUIModel.getStatus().equalsIgnoreCase(Constants.TASK_STATUS_COMPLETED)) {
                    completedItemList.add(todoUIModel);
                }
            }
            return completedItemList;
        }
        return null;
    }


}
