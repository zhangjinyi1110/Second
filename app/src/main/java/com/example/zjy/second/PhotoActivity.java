package com.example.zjy.second;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zjy.second.adapter.FragmentAdapter;
import com.example.zjy.second.fragment.PhotoFragment;

import java.util.ArrayList;
import java.util.List;

public class PhotoActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private View header;
    private ImageView back;
    private ImageView delete;
    private TextView num;
    private FragmentAdapter adapter;
    private List<Fragment> list;
    private List<String> paths;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        MainActivity.setStatusBarColor(this,0xff333333);
        intent = getIntent();
        viewPager = (ViewPager)findViewById(R.id.photo_pager);
        list = new ArrayList<>();
        paths = new ArrayList<>();
        for(int i = 0 ; i<intent.getIntExtra("sum",0) ; i++){
            paths.add(intent.getStringExtra("path"+i));
            list.add(new PhotoFragment(paths.get(i),i+1));
        }
        adapter = new FragmentAdapter(getSupportFragmentManager(),list,paths);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(intent.getIntExtra("num",0));
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
                    num.setText((viewPager.getCurrentItem()+1)+"/"+intent.getIntExtra("sum",0));
                    Log.e("PhotoActivity", "onPageScrollStateChanged------>2");
                }
            }
        });
        header = (View)findViewById(R.id.photo_header);
        back = (ImageView)findViewById(R.id.photo_back);
        delete = (ImageView)findViewById(R.id.photo_delete);
        num = (TextView)findViewById(R.id.photo_num);
        num.setText((intent.getIntExtra("num",1)+1)+"/"+intent.getIntExtra("sum",0));

        if(intent.getStringExtra("flag").equals("f")){
            delete.setVisibility(View.GONE);
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNormalDialog();
            }
        });
    }

    private void showNormalDialog(){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(PhotoActivity.this);
        normalDialog.setTitle("删除图片");
        normalDialog.setMessage("是否删除该图片?");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        Intent intent = new Intent("android.intent.action.PUBLISH_ACTION");
                        intent.putExtra("deleteNum",viewPager.getCurrentItem());
                        sendBroadcast(intent);
                        finish();
                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        // 显示
        normalDialog.show();
    }
}
