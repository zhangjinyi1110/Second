package com.example.zjy.second.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ZJY on 2017/12/13.
 */

public class ReturnBean {
    /**
     * code : 200
     * msg : 登录成功
     * 0 : {"token":"513b7a98545d3b9703dfc2ca0ae8c328"}
     */

    private int code;
    private String msg;
    @SerializedName("0")
    private _$0Bean _$0;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public _$0Bean get_$0() {
        return _$0;
    }

    public void set_$0(_$0Bean _$0) {
        this._$0 = _$0;
    }

    public static class _$0Bean {
        /**
         * token : 513b7a98545d3b9703dfc2ca0ae8c328
         */

        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
