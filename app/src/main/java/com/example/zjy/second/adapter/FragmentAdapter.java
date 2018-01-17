package com.example.zjy.second.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by ZJY on 2017/11/23.
 */

public class FragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;
        private List<String> strings;

    public FragmentAdapter(FragmentManager fm, List<Fragment> list, List<String> strings) {
        super(fm);
        this.list = list;
        this.strings = strings;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return strings.get(position);
    }

}
