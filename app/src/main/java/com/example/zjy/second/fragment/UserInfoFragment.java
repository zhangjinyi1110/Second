package com.example.zjy.second.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.donkingliang.imageselector.utils.ImageSelectorUtils;
import com.example.zjy.second.PublishActivity;
import com.example.zjy.second.R;
import com.example.zjy.second.bean.UserBean;
import com.example.zjy.second.presenter.UserInfoFragmentPresenter;
import com.example.zjy.second.utils.SharedPreferencesUtils;
import com.example.zjy.second.view.UserInfoFragmentView;

import java.net.URL;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.http.Url;

public class UserInfoFragment extends Fragment implements UserInfoFragmentView {
    private View view;
    private View bg;
    private CircleImageView image;
    private CircleImageView okBtn;
    private EditText name;
    private EditText weChat;
    private EditText address;
    private EditText phone;
    private EditText email;
    private UserInfoFragmentPresenter presenter;
    private boolean flag = false;
    private UserInfoBroadcastReceiver broadcastReceiver;
    private IntentFilter intentFilter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_userinfo, container, false);

        broadcastReceiver = new UserInfoBroadcastReceiver();
        intentFilter = new IntentFilter("android.intent.action.USER_IMAGE");
        getActivity().registerReceiver(broadcastReceiver,intentFilter);

        name = (EditText)view.findViewById(R.id.info_user_name);
        weChat = (EditText)view.findViewById(R.id.info_user_weixin);
        address = (EditText)view.findViewById(R.id.info_user_address);
        phone = (EditText)view.findViewById(R.id.info_user_phone);
        email = (EditText)view.findViewById(R.id.info_user_email);

        bg = (View)view.findViewById(R.id.info_user_bg);
        if(!SharedPreferencesUtils.getParam(getContext(),"userInfoBg","").equals("")){
            bg.setBackground(Drawable.createFromPath((String)SharedPreferencesUtils.getParam(getContext(),"userInfoBg","")));
        }
        bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.MAIN");
                intent.putExtra("action","select_bg");
                getActivity().sendBroadcast(intent);
            }
        });

        image = (CircleImageView)view.findViewById(R.id.info_user_image);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.MAIN");
                intent.putExtra("action","phone");
                getActivity().sendBroadcast(intent);
            }
        });

        okBtn = (CircleImageView)view.findViewById(R.id.info_user_ok);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag){
                    stop();
                    UserBean userBean = new UserBean(name.getText().toString(),address.getText().toString(),weChat.getText().toString(),phone.getText().toString(),email.getText().toString());
                    presenter.upUserInfo(userBean);
                }else{
                    start();
                    okBtn.setImageResource(R.drawable.icon_ok);
                    flag = true;
                }
            }
        });

        presenter = new UserInfoFragmentPresenter(this);
        if(!SharedPreferencesUtils.getParam(getContext(),"token","").equals("")){
            presenter.getUserInfo();
        }

        stop();

        return view;
    }

    private void start(){
        name.setFocusable(true);
        name.setFocusableInTouchMode(true);
        name.requestFocus();
        weChat.setFocusable(true);
        weChat.setFocusableInTouchMode(true);
//        weChat.requestFocus();
        email.setFocusable(true);
        email.setFocusableInTouchMode(true);
//        email.requestFocus();
        address.setFocusable(true);
        address.setFocusableInTouchMode(true);
//        address.requestFocus();
        phone.setFocusable(true);
        phone.setFocusableInTouchMode(true);
//        phone.requestFocus();
    }

    private void stop(){
        name.setFocusable(false);
        weChat.setFocusable(false);
        email.setFocusable(false);
        address.setFocusable(false);
        phone.setFocusable(false);
    }

    @Override
    public void setUserInfo(UserBean userInfo) {
        name.setText(userInfo.getName());
        weChat.setText(userInfo.getWeChat());
        email.setText(userInfo.getEmail());
        address.setText(userInfo.getAddress());
        if(userInfo.getPhone()==null){
            phone.setText("");
        }else if(userInfo.getPhone().equals("0")){
            phone.setText("");
        }else{
            phone.setText(userInfo.getPhone());
        }
        Glide.with(getActivity()).load(userInfo.getPic()).into(image);
    }

    @Override
    public void check(String msg) {
        Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void success(String msg) {
        Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
        okBtn.setImageResource(R.drawable.icon_redact);
        flag = false;
    }

    @Override
    public void failure(String msg) {
        presenter.getUserInfo();
        stop();
        okBtn.setImageResource(R.drawable.icon_redact);
        flag = false;
        Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void imageSuccess(String msg,String path) {
        image.setImageBitmap(BitmapFactory.decodeFile(path));
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.putExtra("action","userImage");
        intent.putExtra("path",path);
        getActivity().sendBroadcast(intent);
        Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void imageFailure(String msg) {
        Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
    }

    public class UserInfoBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getStringExtra("action").equals("bg")){
                String image = intent.getStringExtra("image");
                bg.setBackground(Drawable.createFromPath(image));
                SharedPreferencesUtils.setParam(getContext(),"userInfoBg",image);
            }else{
                String image = intent.getStringExtra("image");
//            Toast.makeText(getContext(),image,Toast.LENGTH_SHORT).show();
                String token = (String) SharedPreferencesUtils.getParam(getContext(),"token","");
                Log.i("UserInfoFragment",token);
                presenter.upImage(token,image);
            }
        }
    }

    @Override
    public void onDestroy() {
        getActivity().unregisterReceiver(broadcastReceiver);
        super.onDestroy();
    }
}
