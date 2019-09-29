package com.example.todo.views.homescreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.todo.R;
import com.example.todo.common.Constants;
import com.example.todo.databinding.TodayFragmentBinding;
import com.example.todo.di.Injector;
import com.example.todo.viewmodel.HomeViewModel;
import com.example.todo.views.adapter.ToDoListAdapter;
import com.example.todo.views.uimodel.TodoUIModel;

import java.util.List;

public class TodayFragment extends Fragment {

    private final ToDoListAdapter adapter = new ToDoListAdapter();
    private HomeViewModel homeViewModel;
    private TodayFragmentBinding binding;
    private int tabType;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            tabType = getArguments().getInt(Constants.TAB_TYPE_BUNDLE_TYPE);
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding =  DataBindingUtil.inflate(inflater, R.layout.today_fragment, container, false);
        return binding.getRoot();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initPendingRecyclerView();
        initCompletedItemsRecyclerView();
        homeViewModel = ViewModelProviders.of(getActivity(), Injector.getAppViewModelFacotry()).get(HomeViewModel.class);
        if (tabType == Constants.TAB_TODAY) {
            homeViewModel.getTodayLiveData().observe(this, new Observer<List<TodoUIModel>>() {
                @Override
                public void onChanged(List<TodoUIModel> todoUIModels) {
                   /* ((ToDoListAdapter)binding.rvPendingItems.getAdapter()).setList(todoUIModels);
                    ((ToDoListAdapter)binding.rvCompletedItems.getAdapter()).setList(todoUIModels);*/
                }
            });
        } else if (tabType == Constants.TAB_LATER) {
            homeViewModel.getLaterLiveData().observe(this, new Observer<List<TodoUIModel>>() {
                @Override
                public void onChanged(List<TodoUIModel> todoUIModels) {
                    ((ToDoListAdapter)binding.rvPendingItems.getAdapter()).setList(homeViewModel.getPendingItemList(todoUIModels));
                    ((ToDoListAdapter)binding.rvCompletedItems.getAdapter()).setList(homeViewModel.getCompletedItemList(todoUIModels));
                }
            });
        }
    }

    private void initCompletedItemsRecyclerView() {
        if (binding.rvCompletedItems != null) {
            binding.rvCompletedItems.setLayoutManager(new LinearLayoutManager(this.getContext()));
            binding.rvCompletedItems.setAdapter(adapter);
            binding.rvCompletedItems.setItemAnimator(new DefaultItemAnimator());
        }
    }


    private void initPendingRecyclerView() {
        if (binding.rvPendingItems != null) {
            binding.rvPendingItems.setLayoutManager(new LinearLayoutManager(this.getContext()));
            binding.rvPendingItems.setAdapter(adapter);
            binding.rvPendingItems.setItemAnimator(new DefaultItemAnimator());
        }
    }

}
