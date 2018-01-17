package com.example.zjy.second.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.zjy.second.PublishActivity;
import com.example.zjy.second.R;
import com.example.zjy.second.adapter.FragmentAdapter;
import com.example.zjy.second.bean.CategoryBean;
import com.example.zjy.second.loader.GlideImageLoader;
import com.example.zjy.second.presenter.MainFragmentPresenter;
import com.example.zjy.second.utils.SharedPreferencesUtils;
import com.example.zjy.second.view.MainFragmentView;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment implements MainFragmentView {
    private View view;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FragmentAdapter adapter;
    private Banner banner;
    private FloatingActionButton floatingActionButton;
    private MainFragmentPresenter presenter;
    private List<String> titles;
    private List<Fragment> fragments;
    private ProgressBar bar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main, container, false);
        initView();
        presenter = new MainFragmentPresenter(this);
        presenter.getBanner();
        presenter.getCategory();
        return view;
    }

    private void initView() {
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.floating);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SharedPreferencesUtils.getParam(getContext(),"token","").equals("")){
                    Toast.makeText(getActivity().getApplicationContext(),"请先登录",Toast.LENGTH_SHORT).show();
                    Log.i("SharedPreferencesUtils","SharedPreferencesUtils");
                }else{
                    Intent intent = new Intent(getActivity(), PublishActivity.class);
                    getActivity().startActivity(intent);
                }
            }
        });
        banner = (Banner) view.findViewById(R.id.banner);
        viewPager = (ViewPager) view.findViewById(R.id.classify_pager);
        tabLayout = (TabLayout) view.findViewById(R.id.classify_tab);
        fragments = new ArrayList<>();
        titles = new ArrayList<>();
        adapter = new FragmentAdapter(getFragmentManager(), fragments, titles);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        bar = (ProgressBar)view.findViewById(R.id.main_bar);
    }

    @Override
    public void setBanner(List<String> list) {
        banner.setImages(list).setImageLoader(new GlideImageLoader()).start();
    }

    @Override
    public void setCategory(List<CategoryBean> list) {
//        titles.addAll(list);
        for(int i = 0 ; i<list.size() ; i++){
            titles.add(list.get(i).getName());
            tabLayout.addTab(tabLayout.newTab().setText(titles.get(i)));
            ClassifyFragment classifyFragment = new ClassifyFragment(list.get(i).getId());
            fragments.add(classifyFragment);
//            Log.i("MainFragment",list.get(i).getId()+list.get(i).getName()+"+++++");
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showBar() {
        bar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideBar() {
        bar.setVisibility(View.GONE);
    }
}
