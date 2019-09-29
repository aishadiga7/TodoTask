package com.example.todo.views.adapter;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.todo.R;
import com.example.todo.common.Constants;
import com.example.todo.views.homescreen.TodayFragment;

public class ViewpagerAdapter extends FragmentPagerAdapter {
    private Context mContext;


    public ViewpagerAdapter(@NonNull FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        TodayFragment fragment;
        Bundle bundle = new Bundle();
        switch (position) {
            case 0 :
                bundle.putInt(Constants.TAB_TYPE_BUNDLE_TYPE, Constants.TAB_TODAY);
                break;
            case 1:
                bundle.putInt(Constants.TAB_TYPE_BUNDLE_TYPE, Constants.TAB_LATER);
                break;
        }
        fragment = new TodayFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0)
            return mContext.getString(R.string.today_tab_title);
        else
            return mContext.getString(R.string.later_tab_title);
    }
}
