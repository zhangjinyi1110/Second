package com.example.zjy.second;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zjy.second.adapter.FragmentAdapter;
import com.example.zjy.second.fragment.FirstFragment;
import com.example.zjy.second.utils.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.List;

public class FirstActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private FragmentAdapter adapter;
    private List<Fragment> list;
//    private Intent intent;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        //隐藏状态栏
        //定义全屏参数
        int flag= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);
        setContentView(R.layout.activity_first);
        SharedPreferencesUtils.setParam(getApplicationContext(),"first","false");
//        intent = getIntent();
        viewPager = (ViewPager)findViewById(R.id.first_pager);
        list = new ArrayList<>();
        for(int i = 0 ; i<3 ; i++){
            list.add(new FirstFragment(i));
        }
        adapter = new FragmentAdapter(getSupportFragmentManager(),list,new ArrayList<String>());
        viewPager.setAdapter(adapter);
        imageView1 = (ImageView)findViewById(R.id.first_view1);
        imageView2 = (ImageView)findViewById(R.id.first_view2);
        imageView3 = (ImageView)findViewById(R.id.first_view3);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if(state == 0){
                    Log.e("PhotoActivity", "onPageScrollStateChanged------>0");
                }else if(state == 1){
                    Log.e("PhotoActivity", "onPageScrollStateChanged------>1");
                }else if(state == 2){
                    switch(viewPager.getCurrentItem()){
                        case 0:
                            imageView1.setImageResource(R.drawable.banner_on);
                            imageView2.setImageResource(R.drawable.banner_off);
                            imageView3.setImageResource(R.drawable.banner_off);
                            break;
                        case 2:
                            imageView1.setImageResource(R.drawable.banner_off);
                            imageView2.setImageResource(R.drawable.banner_off);
                            imageView3.setImageResource(R.drawable.banner_on);
                            break;
                        case 1:
                            imageView1.setImageResource(R.drawable.banner_off);
                            imageView2.setImageResource(R.drawable.banner_on);
                            imageView3.setImageResource(R.drawable.banner_off);
                            break;
                    }
                    Log.e("PhotoActivity", "onPageScrollStateChanged------>2");
                }
            }
        });
    }
}
