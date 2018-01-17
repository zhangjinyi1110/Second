package com.example.zjy.second;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zjy.second.presenter.LoginPresenter;
import com.example.zjy.second.utils.SharedPreferencesUtils;
import com.example.zjy.second.view.LoginView;

public class LoginActivity extends AppCompatActivity implements LoginView {
    private EditText name;
    private EditText pwd;
    private TextView forgot;
    private Button loginBtn;
    private Button registerBtn;
    private TextView jumpText;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        MainActivity.setStatusBarColor(this,0xff0555ff);
        initView();
        presenter = new LoginPresenter(this);
        if(!SharedPreferencesUtils.getParam(this,"token","").equals("")){
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            Log.i("LoginActivity",(String)SharedPreferencesUtils.getParam(this,"token",""));
            finish();
        }
    }

    private void initView(){
        name = (EditText)findViewById(R.id.login_name);
        pwd = (EditText)findViewById(R.id.login_pwd);
        forgot = (TextView) findViewById(R.id.login_forgot);
        loginBtn = (Button)findViewById(R.id.login_ok);
        registerBtn = (Button)findViewById(R.id.login_register);
        jumpText = (TextView)findViewById(R.id.login_jump_over);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"用户名不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(pwd.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"密码不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    presenter.login(name.getText().toString(),pwd.getText().toString());
                }
            }
        });
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        jumpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void success(String msg,String token) {
        SharedPreferencesUtils.setParam(this,"token",token);
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        Log.i("LoginActivity","userInfo"+token);
        startActivity(intent);
        finish();
    }

    @Override
    public void failure(String msg) {
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
    }
}
