package com.example.zjy.second.ui;

import android.content.Context;
import android.util.AttributeSet;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

/**
 * Created by ZJY on 2017/11/23.
 */

public class NewSearchView extends MaterialSearchView {
    private boolean flag;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public NewSearchView(Context context) {
        super(context);
        flag = false;
    }

    public NewSearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        flag = false;
    }

    public NewSearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        flag = false;
    }
}
