package com.example.zjy.second;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zjy.second.bean.ReturnBean;
import com.example.zjy.second.presenter.RegisterPresenter;
import com.example.zjy.second.view.RegisterView;

public class RegisterActivity extends AppCompatActivity implements RegisterView {
    private EditText name;
    private EditText pwd;
    private EditText repwd;
    private Button registerBtn;
    private Button jumpBtn;
    private TextView jumpText;
    private RegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        MainActivity.setStatusBarColor(this,0xff0555ff);
        initView();
        presenter = new RegisterPresenter(this);
    }

    private void initView(){
        name = (EditText)findViewById(R.id.register_name);
        pwd = (EditText)findViewById(R.id.register_pwd);
        repwd = (EditText)findViewById(R.id.register_repwd);
        registerBtn = (Button)findViewById(R.id.register_ok);
        jumpBtn = (Button)findViewById(R.id.register_jump);
        jumpText = (TextView)findViewById(R.id.register_jump_over);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"用户名不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(pwd.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"密码不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!repwd.getText().toString().equals(pwd.getText().toString())){
                    Toast.makeText(getApplicationContext(),"两次密码不一致",Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    presenter.register(name.getText().toString(),pwd.getText().toString());
                }
            }
        });
        jumpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        jumpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void success(String msg) {
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
        intent.putExtra("username",name.getText().toString());
        intent.putExtra("password",pwd.getText().toString());
        startActivity(intent);
        finish();
    }

    @Override
    public void failure(String msg) {
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
    }
}
