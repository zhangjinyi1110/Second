package com.example.zjy.second.fragment;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.example.zjy.second.R;

public class PhotoFragment extends Fragment {
    private View view;
    private PhotoView photoView;
    private String path;
    private int num;

    public PhotoFragment(String path,int num) {
        this.path = path;
        this.num = num;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_photo, container, false);
        photoView = (PhotoView)view.findViewById(R.id.photo_view);
//        Log.i("PhotoFragment",path);
//        photoView.setImageBitmap(BitmapFactory.decodeFile(path));
        Glide.with(this).load(path).into(photoView);
        photoView.enable();
        return view;
    }
}
