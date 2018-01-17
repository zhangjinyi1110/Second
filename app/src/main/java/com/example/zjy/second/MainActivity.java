package com.example.zjy.second;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.donkingliang.imageselector.utils.ImageSelectorUtils;
import com.example.zjy.second.application.MyApplication;
import com.example.zjy.second.bean.UserBean;
import com.example.zjy.second.fragment.AboutFragment;
import com.example.zjy.second.fragment.CollectFragment;
import com.example.zjy.second.fragment.ContactFragment;
import com.example.zjy.second.fragment.MainFragment;
import com.example.zjy.second.fragment.MessageFragment;
import com.example.zjy.second.fragment.PublishFragment;
import com.example.zjy.second.fragment.ResultFragment;
import com.example.zjy.second.fragment.SearchFragment;
import com.example.zjy.second.fragment.SynthesizeFragment;
import com.example.zjy.second.fragment.UserInfoFragment;
import com.example.zjy.second.presenter.MainPresenter;
import com.example.zjy.second.utils.SharedPreferencesUtils;
import com.example.zjy.second.view.MainView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements MainView {
    private Toolbar toolbar;
    //    private NewSearchView searchView;
    private Fragment mContent;
    private FragmentManager fragmentManager;
    private MainFragment mainFragment;
    private SynthesizeFragment synthesizeFragment;
    private SearchFragment searchFragment;
    private ResultFragment resultFragment;
    //    private UserInfoFragment userInfoFragment;
    private AboutFragment aboutFragment;
    private ContactFragment contactFragment;
    private PublishFragment publishFragment;
    private CollectFragment collectFragment;
    private MessageFragment messageFragment;
    private MainPresenter presenter;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private TextView title;
    private View headLayout;
    private CircleImageView userImage;
    private MenuItem menuItem;
    private TextView redact;
    private ImageView searchBtn;
    private MainBroadcastReceiver broadcastReceiver;
    private IntentFilter intentFilter;
    private TextView exit;
    private TextView collectNum;
//    private TextView

    public static final int TO_MAIN = 0;
    public static final int TO_SEARCH = 1;
    public static final int TO_RESULT = 2;
    public static final int PUBLISH = 100;

    private final int REQUEST_CODE = 500;
    private final int SELECT_BG = 235;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setStatusBarColor(this,0xff0555ff);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_muen);

        broadcastReceiver = new MainBroadcastReceiver();
        intentFilter = new IntentFilter("android.intent.action.MAIN");
        registerReceiver(broadcastReceiver, intentFilter);

        title = (TextView) findViewById(R.id.main_title);

        exit = (TextView) findViewById(R.id.exit_btn);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SharedPreferencesUtils.getParam(getApplicationContext(), "token", "").equals("")) {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    showExitDialog();
                }
            }
        });
        redact = (TextView) findViewById(R.id.redact_btn);
        redact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (redact.getText().toString().equals("编辑")) {
                    redact.setText("取消");
                    if (mContent == publishFragment) {
                        Intent intent = new Intent("android.intent.action.PUBLISH_REMOVE");
                        intent.putExtra("action", "remove");
                        sendBroadcast(intent);
                    } else if (mContent == collectFragment) {
                        Intent intent = new Intent("android.intent.action.COLLECT_REMOVE");
                        intent.putExtra("action", "remove");
                        sendBroadcast(intent);
                    }
                } else if (redact.getText().toString().equals("取消")) {
                    redact.setText("编辑");
                    if (mContent == publishFragment) {
                        Intent intent = new Intent("android.intent.action.PUBLISH_REMOVE");
                        intent.putExtra("action", "ok");
                        sendBroadcast(intent);
                    } else if (mContent == collectFragment) {
                        Intent intent = new Intent("android.intent.action.COLLECT_REMOVE");
                        intent.putExtra("action", "ok");
                        sendBroadcast(intent);
                    }
                }
            }
        });

        presenter = new MainPresenter(this);
        presenter.init();
        ((MyApplication) getApplicationContext()).setPresenter(presenter);

        initFragment();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        navigationView = (NavigationView) findViewById(R.id.navigation);
        headLayout = navigationView.inflateHeaderView(R.layout.navigation_header);
        userImage = (CircleImageView) headLayout.findViewById(R.id.user_image);
        userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!SharedPreferencesUtils.getParam(getApplicationContext(), "token", "").equals("")) {
                    hideRedact();
                    switchContent(mContent, new UserInfoFragment());
                    title.setText("我的");
                    drawerLayout.closeDrawers();
                    hideSearch();
                    exit.setVisibility(View.VISIBLE);
                    if (SharedPreferencesUtils.getParam(getApplicationContext(), "token", "").equals("")) {
                        exit.setText("登录");
                    } else {
                        exit.setText("退出登陆");
                    }
                }else{
                    Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        hideRedact();
                        showSearch();
                        switchContent(mContent, mainFragment);
                        title.setText("Second");
                        break;
                    case R.id.synthesize:
                        hideRedact();
//                        hideSearch();
                        switchContent(mContent, synthesizeFragment);
                        title.setText("综  合");
                        break;
//                    case R.id.exchange:
//                        hideRedact();
////                        hideSearch();
//                        title.setText("关于我们");
//                        break;
                    case R.id.publish:
                        if(SharedPreferencesUtils.getParam(MainActivity.this,"token","").equals("")){
                            Toast.makeText(getApplicationContext(),"请先登录",Toast.LENGTH_SHORT).show();
                            return false;
                        }
                        showRedact();
                        switchContent(mContent, publishFragment);
                        hideSearch();
                        title.setText("我的发布");
                        break;
                    case R.id.message:
                        if(SharedPreferencesUtils.getParam(MainActivity.this,"token","").equals("")){
                            Toast.makeText(getApplicationContext(),"请先登录",Toast.LENGTH_SHORT).show();
                            return false;
                        }
                        hideRedact();
                        title.setText("回复我的");
                        switchContent(mContent, new MessageFragment());
                        hideSearch();
                        break;
                    case R.id.collect:
                        if(SharedPreferencesUtils.getParam(MainActivity.this,"token","").equals("")){
                            Toast.makeText(getApplicationContext(),"请先登录",Toast.LENGTH_SHORT).show();
                            return false;
                        }
                        showRedact();
                        switchContent(mContent, collectFragment);
                        Intent intent = new Intent("android.intent.action.COLLECT_REMOVE");
                        intent.putExtra("action", "update");
                        sendBroadcast(intent);
                        Log.i("MainActivity", "collect");
                        hideSearch();
                        title.setText("我的收藏");
                        break;
                    case R.id.contact:
                        hideRedact();
                        switchContent(mContent, contactFragment);
                        hideSearch();
                        title.setText("联系我们");
                        break;
                    case R.id.about:
                        hideRedact();
                        switchContent(mContent, aboutFragment);
                        hideSearch();
                        title.setText("关于我们");
                        break;
                }
                exit.setVisibility(View.GONE);
                drawerLayout.closeDrawers();
                return false;
            }
        });

        searchBtn = (ImageView) findViewById(R.id.main_search);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initFragment() {
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        mainFragment = new MainFragment();
        mContent = mainFragment;
        synthesizeFragment = new SynthesizeFragment();
        searchFragment = new SearchFragment();
        resultFragment = new ResultFragment();
//        userInfoFragment = new UserInfoFragment();
        aboutFragment = new AboutFragment();
        contactFragment = new ContactFragment();
        publishFragment = new PublishFragment();
        collectFragment = new CollectFragment();
        messageFragment = new MessageFragment();
        transaction.replace(R.id.context, mContent).commit();
    }

    private void showExitDialog() {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog = new AlertDialog.Builder(MainActivity.this);
        normalDialog.setTitle("退出登陆");
        normalDialog.setMessage("是否要退出登录?");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        presenter.exit((String) SharedPreferencesUtils.getParam(MainActivity.this, "token", ""));
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

    public void switchContent(Fragment from, Fragment to) {
//        if(to!=userInfoFragment){
//            showSearch();
//        }
        if (mContent != to) {
            mContent = to;
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            if (!to.isAdded()) {    // 先判断是否被add过
                transaction.hide(from).add(R.id.context, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
                Log.i("MainActivity", "add");
            } else {
                transaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
                Log.i("MainActivity", "show");
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (mContent == resultFragment) {
                    switchContent(mContent, mainFragment);
                    title.setText("Second");
                    getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu_logo);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
                break;
        }
        return false;
    }

    @Override
    public void toFragment(int code) {
        if (code == TO_MAIN) {
            switchContent(mContent, mainFragment);
            title.setText("Second");
        } else if (code == TO_SEARCH) {
            switchContent(mContent, searchFragment);
            title.setText("搜索");
        } else if (code == TO_RESULT) {
            switchContent(mContent, resultFragment);
            title.setText("搜索结果");
        }
    }

    @Override
    public void setDate(UserBean userBean) {
        Glide.with(this).load(userBean.getPic()).error(R.drawable.image).into(userImage);
    }

    @Override
    public void exitSuccess(String msg) {
        SharedPreferencesUtils.setParam(this, "token", "");
        exit.setVisibility(View.GONE);
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        switchContent(mContent, mainFragment);
        title.setText("Second");
        showSearch();
        userImage.setImageResource(R.drawable.image);
//        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
//        startActivity(intent);
//        finish();
    }

    @Override
    public void exitFailure(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PUBLISH:
                if (resultCode == RESULT_OK) {
                    Intent intent = new Intent("android.intent.action.CLASSIFY");
                    sendBroadcast(intent);
                }
                break;
            case REQUEST_CODE:
                if (data != null) {
                    ArrayList<String> images = data.getStringArrayListExtra(ImageSelectorUtils.SELECT_RESULT);
                    Intent intent = new Intent("android.intent.action.USER_IMAGE");
                    intent.putExtra("image", images.get(0));
                    intent.putExtra("action", "userImage");
                    sendBroadcast(intent);
                }
                break;
            case SELECT_BG:
                if (data != null) {
                    ArrayList<String> images = data.getStringArrayListExtra(ImageSelectorUtils.SELECT_RESULT);
                    Intent intent = new Intent("android.intent.action.USER_IMAGE");
                    intent.putExtra("image", images.get(0));
                    intent.putExtra("action", "bg");
                    sendBroadcast(intent);
                }
                break;
        }
    }

    private void hideSearch() {
//        menuItem.setVisible(false);
        searchBtn.setVisibility(View.GONE);
    }

    private void showSearch() {
//        menuItem.setVisible(true);
        searchBtn.setVisibility(View.VISIBLE);
    }

    private void hideRedact() {
        if (redact.getText().toString().equals("取消")) {
            redact.setText("编辑");
            if (mContent == publishFragment) {
                Intent intent = new Intent("android.intent.action.PUBLISH_REMOVE");
                intent.putExtra("action", "ok");
                sendBroadcast(intent);
            } else if (mContent == collectFragment) {
                Intent intent = new Intent("android.intent.action.COLLECT_REMOVE");
                intent.putExtra("action", "ok");
                sendBroadcast(intent);
            }
        }
        redact.setVisibility(View.GONE);
    }

    private void showRedact() {
        if (redact.getText().toString().equals("取消")) {
            redact.setText("编辑");
            if (mContent == publishFragment) {
                Intent intent = new Intent("android.intent.action.PUBLISH_REMOVE");
                intent.putExtra("action", "ok");
                sendBroadcast(intent);
            } else if (mContent == collectFragment) {
                Intent intent = new Intent("android.intent.action.COLLECT_REMOVE");
                intent.putExtra("action", "ok");
                sendBroadcast(intent);
            }
        }
        redact.setVisibility(View.VISIBLE);
    }

    public class MainBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
//            showPopup();
            if (intent.getStringExtra("action").equals("userImage")) {
                userImage.setImageBitmap(BitmapFactory.decodeFile(intent.getStringExtra("path")));
                return;
            } else if (intent.getStringExtra("action").equals("select_bg")) {
                ImageSelectorUtils.openPhoto(MainActivity.this, SELECT_BG, true, 0);
            } else {
                ImageSelectorUtils.openPhoto(MainActivity.this, REQUEST_CODE, true, 0);
            }
        }
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(broadcastReceiver);
        super.onDestroy();
    }

    public static void setStatusBarColor(Activity activity, int statusColor) {
        Window window = activity.getWindow();
        //取消状态栏透明
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //添加Flag把状态栏设为可绘制模式
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //设置状态栏颜色
        window.setStatusBarColor(statusColor);
        //设置系统状态栏处于可见状态
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        //让view不根据系统窗口来调整自己的布局
        ViewGroup mContentView = (ViewGroup) window.findViewById(Window.ID_ANDROID_CONTENT);
        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {
            ViewCompat.setFitsSystemWindows(mChildView, false);
            ViewCompat.requestApplyInsets(mChildView);
        }
    }
}
