package com.example.todo.views.navigation;

import android.app.Activity;
import android.content.Intent;

import com.example.todo.views.homescreen.HomeActivity;

public class Router implements Navigator {

    Activity activity;

    public Router(Activity activity) {
        this.activity = activity;
    }


    @Override
    public void launchHome() {
        activity.startActivity(new Intent(activity,HomeActivity.class));
    }
}
