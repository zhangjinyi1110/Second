package com.example.zjy.second.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.zjy.second.PublishActivity;
import com.example.zjy.second.R;
import com.example.zjy.second.adapter.FragmentAdapter;
import com.example.zjy.second.bean.CategoryBean;
import com.example.zjy.second.presenter.SynthesizeFragmentPresenter;
import com.example.zjy.second.utils.SharedPreferencesUtils;
import com.example.zjy.second.view.SynthesizeFragmentView;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

public class SynthesizeFragment extends Fragment implements SynthesizeFragmentView {
    private View view;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FragmentAdapter adapter;
    private FloatingActionButton floatingActionButton;
    private List<String> titles;
    private List<Fragment> fragments;
    private SynthesizeFragmentPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_synthesize, container, false);
        initView();
        presenter = new SynthesizeFragmentPresenter(this);
        presenter.getCategory();
        return view;
    }

    private void initView() {
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.synthesize_floating);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SharedPreferencesUtils.getParam(getContext(),"token","").equals("")){
                    Toast.makeText(getContext(),"请先登录",Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(getActivity(), PublishActivity.class);
                    getActivity().startActivity(intent);
                }
            }
        });
        viewPager = (ViewPager) view.findViewById(R.id.synthesize_pager);
        tabLayout = (TabLayout) view.findViewById(R.id.synthesize_tab);
        fragments = new ArrayList<>();
        titles = new ArrayList<>();
        adapter = new FragmentAdapter(getFragmentManager(), fragments, titles);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void setCategory(List<CategoryBean> list) {
        for(int i = 0 ; i<list.size() ; i++){
            titles.add(list.get(i).getName());
            tabLayout.addTab(tabLayout.newTab().setText(titles.get(i)));
            ClassifyFragment classifyFragment = new ClassifyFragment(list.get(i).getId());
            fragments.add(classifyFragment);
//            Log.i("MainFragment",list.get(i).getId()+list.get(i).getName()+"+++++");
        }
        adapter.notifyDataSetChanged();
    }
}
