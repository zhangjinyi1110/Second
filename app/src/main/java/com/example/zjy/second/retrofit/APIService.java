package com.example.zjy.second.retrofit;

import com.example.zjy.second.bean.BannerBean;
import com.example.zjy.second.bean.CategoryBean;
import com.example.zjy.second.bean.CommentBean;
import com.example.zjy.second.bean.GoodsBean;
import com.example.zjy.second.bean.ReturnBean;
import com.example.zjy.second.bean.SearchNameBean;
import com.example.zjy.second.bean.UserBean;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by ZJY on 2017/11/29.
 */

public interface APIService {

    @GET("api/Banner/getBanner?id=1")
    Observable<BannerBean> getBanner();

    @GET("api/Category/getCate")
    Observable<List<CategoryBean>> getCategory();

    @GET("api/Goods/getAllInCategory")
    Observable<List<GoodsBean>> getAllInCategory(@Query("id") int id);

    @GET("api/Goods/getRecent")
    Observable<List<GoodsBean>> getRecent(@Query("count") int count);

    @GET("api/Goods/getGoods")
    Observable<List<GoodsBean>> getGoods(@Query("id") int id);

    @Multipart
    @POST("register")
    Observable<ReturnBean> register(@Part MultipartBody.Part username, @Part MultipartBody.Part password);

    @Multipart
    @POST("login")
    Observable<ReturnBean> login(@Part MultipartBody.Part username, @Part MultipartBody.Part password);

    @FormUrlEncoded
    @POST("api/user/getUser")
    Observable<UserBean> getUser(@Field("token") String token);

    @FormUrlEncoded
    @POST("logout")
    Observable<ReturnBean> logout(@Field("token") String token);

    //url；title；description；price；type_way；category_id；tell
    @Multipart
    @POST("api/Goods/upload")
    Observable<ReturnBean> upload(@Query("token") String token, @Part List<MultipartBody.Part> url, @Query("title") String title,
                                  @Query("description") String description, @Query("price") int price, @Query("type_way") int type_way,
                                  @Query("category_id") int category_id, @Query("tell") String tell);

//    @Multipart
//    @POST("api/Goods/upload")
//    Observable<ReturnBean> upload(@Query("token") String token, @Part MultipartBody.Part image, @Query("title") String title,
//                                  @Query("description") String description, @Query("price") int price, @Query("type_way") int type_way,
//                                  @Query("category_id") int category_id, @Query("tell") String tell);

//    @Multipart
//    @POST("api/Goods/upload")
//    Observable<ReturnBean> upload(@PartMap Map<String,String> map, @Part List<MultipartBody.Part> part);

    //token，name（昵称）,address, wechat（微信），phone,email
    @FormUrlEncoded
    @POST("api/user/edit")
    Observable<ReturnBean> upUser(@Field("token") String token, @Field("name") String name, @Field("address") String address,
                                  @Field("wechat") String wechat, @Field("phone") String phone, @Field("email") String email);

    @FormUrlEncoded
    @POST("api/User/setCollects")
    Observable<ReturnBean> setCollects(@Field("token") String token,@Field("goods_id") int goods_id);

    @GET("api/User/unsetCollects")
    Observable<ReturnBean> unsetCollects(@Query("token") String token,@Query("goods_id") int goods_id);

    @GET("api/User/getCollects")
    Observable<List<GoodsBean>> getCollects(@Query("token") String token);

    @FormUrlEncoded
    @POST("api/Goods/Search")
    Observable<List<SearchNameBean>> search(@Field("token") String token, @Field("search") String search);

    @Multipart
    @POST("api/User/editPic")
    Observable<ReturnBean> editPic(@Query("token") String token, @Part MultipartBody.Part image);

    @FormUrlEncoded
    @POST("api/Comment/add")
    Observable<ReturnBean> addComment(@Field("token") String token, @Field("content") String content,@Field("goods_id") int goods_id);

    @FormUrlEncoded
    @POST("api/Comment/recly")
    Observable<ReturnBean> reclyComment(@Field("token") String token, @Field("id") int id, @Field("content") String content,@Field("goods_id") int goods_id);

    @GET("api/Comment/delete")
    Observable<ReturnBean> deleteComment(@Query("token") String token,@Query("id") int id,@Query("goods_id") int goods_id);

    @GET("api/Comment/select")
    Observable<List<CommentBean>> selectComment(@Query("goods_id") int goods_id);

    @GET("api/Comment/getcomment")
    Observable<List<CommentBean>> getcomment(@Query("token") String token);

    @GET("api/Goods/getUpload")
    Observable<List<GoodsBean>> getUpload(@Query("token") String token);

    @GET("api/Goods/delMyGoods")
    Observable<ReturnBean> delMyGoods(@Query("token") String token,@Query("goods_id") int goods_id);

}
