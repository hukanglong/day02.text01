package com.example.day02text01.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class FragAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private List<Fragment> mList;

    public FragAdapter(@NonNull FragmentManager fm, Context context, List<Fragment> list) {
        super(fm);
        mContext = context;
        mList = list;
    }

    public FragAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }
}
