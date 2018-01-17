package com.example.zjy.second.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zjy.second.LoginActivity;
import com.example.zjy.second.R;

public class FirstFragment extends Fragment {
    private View view;
    private int num;
    private TextView btn;

    public FirstFragment(int num) {
        this.num = num;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_first, container, false);
        switch(num){
            case 0:
                view.setBackgroundResource(R.drawable.icon_first1);
                break;
            case 1:
                view.setBackgroundResource(R.drawable.icon_first2);
                break;
            case 2:
                view.setBackgroundResource(R.drawable.icon_first3);
                break;
        }
        btn = (TextView)view.findViewById(R.id.first_go);
        if(num==2){
            btn.setVisibility(View.VISIBLE);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            });
        }
        return view;
    }
}
