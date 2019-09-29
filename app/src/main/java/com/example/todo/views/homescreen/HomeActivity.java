package com.example.todo.views.homescreen;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.example.todo.R;
import com.example.todo.databinding.ActivityHomeBinding;
import com.example.todo.di.Injector;
import com.example.todo.viewmodel.HomeViewModel;
import com.example.todo.views.adapter.ViewpagerAdapter;

public class HomeActivity extends AppCompatActivity {
    private HomeViewModel homeViewModel;
    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        setUpViewPager();
        homeViewModel = ViewModelProviders.of(this, Injector.getAppViewModelFacotry()).get(HomeViewModel.class);
        homeViewModel.getTodoTaskData();
    }

    private void setUpViewPager() {
        ViewpagerAdapter viewpagerAdapter = new ViewpagerAdapter(getSupportFragmentManager(), this);
        binding.viewPager.setAdapter(viewpagerAdapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.unbind();
    }
}
