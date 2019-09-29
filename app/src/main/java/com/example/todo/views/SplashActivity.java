package com.example.todo.views;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.todo.R;
import com.example.todo.views.navigation.Navigator;
import com.example.todo.views.navigation.Router;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final Navigator navigator = new Router(this);
        new Handler().postDelayed(()-> {
                navigator.launchHome();
        }, 5000);


    }
}
