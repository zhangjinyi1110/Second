package com.example.zjy.second;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.zjy.second.bean.CommentBean;
import com.example.zjy.second.bean.GoodsBean;
import com.example.zjy.second.presenter.GoodsPresenter;
import com.example.zjy.second.utils.SharedPreferencesUtils;
import com.example.zjy.second.view.GoodsView;

import org.w3c.dom.Text;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.R.attr.id;
import static android.R.attr.label;
import static android.R.attr.radius;

public class GoodsActivity extends AppCompatActivity implements GoodsView {
    private Toolbar toolbar;
    private ImageView cover;
    private CircleImageView userImage;
    private TextView userName;
    private TextView goodsName;
    private TextView goodsPrice;
    private TextView goodsDescribe;
    private ViewGroup imageGroup;
    private ImageView browse;
    private TextView browseNum;
    private ImageView enshrine;
    private TextView enshrineNum;
    private ImageView comment;
    private TextView commentNum;
    private TextView time;
    private ScrollView scrollView;
    private ViewGroup commentGroup;
    private GoodsPresenter presenter;
    private Intent intent;
    private View bar;
    private TextView tell;
    //    private View edit;
//    private EditText commentText;
//    private TextView sendBtn;
//    private int id;
    private final int TYPE_COMMENT = 0;
    private final int TYPE_REPLY = 1;

    private int scroll;
//    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        MainActivity.setStatusBarColor(this,0xff0555ff);
        toolbar = (Toolbar) findViewById(R.id.goods_tool);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_back);
        intent = getIntent();
        presenter = new GoodsPresenter(this);
        initView();
        presenter.getGoods(intent.getIntExtra("id", 0));
        presenter.getComment(intent.getIntExtra("id", 0));
        Log.i("GoodsActivity", intent.getIntExtra("id", 0) + "+++");
    }

    private void initView() {
        cover = (ImageView) findViewById(R.id.goods_cover_image);
        userImage = (CircleImageView) findViewById(R.id.goods_user_image);
        userName = (TextView) findViewById(R.id.goods_user_name);
        goodsName = (TextView) findViewById(R.id.goods_title);
        goodsPrice = (TextView) findViewById(R.id.goods_price);
        goodsDescribe = (TextView) findViewById(R.id.goods_describe);
        imageGroup = (ViewGroup) findViewById(R.id.goods_image_group);
        browse = (ImageView) findViewById(R.id.goods_option_browse);
        browseNum = (TextView) findViewById(R.id.goods_option_browse_num);
        enshrine = (ImageView) findViewById(R.id.goods_option_enshrine);
        enshrineNum = (TextView) findViewById(R.id.goods_option_enshrine_num);
        comment = (ImageView) findViewById(R.id.goods_option_comment);
        commentNum = (TextView) findViewById(R.id.goods_option_comment_num);
        time = (TextView) findViewById(R.id.goods_data);
        scrollView = (ScrollView) findViewById(R.id.goods_scroll);
        commentGroup = (ViewGroup) findViewById(R.id.goods_comment_group);
        bar = (View)findViewById(R.id.goods_bar);
        tell = (TextView)findViewById(R.id.goods_tell);
//        edit = (View)findViewById(R.id.goods_edit);
//        commentText = (EditText)findViewById(R.id.goods_content_text);
//        sendBtn = (TextView)findViewById(R.id.goods_send);

        enshrine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SharedPreferencesUtils.getParam(getApplicationContext(),"token","").equals("")){
                    Toast.makeText(getApplicationContext(),"请先登录",Toast.LENGTH_SHORT).show();
                }else{
                    presenter.collect((String) SharedPreferencesUtils.getParam(GoodsActivity.this, "token", ""), intent.getIntExtra("id", 0));
                }
            }
        });
        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SharedPreferencesUtils.getParam(getApplicationContext(),"token","").equals("")){
                    Toast.makeText(getApplicationContext(),"请先登录",Toast.LENGTH_SHORT).show();
                }else{
                    showPopup(0, intent.getIntExtra("id", 0), TYPE_COMMENT);
                }
            }
        });
    }

    @Override
    public void setDate(final GoodsBean goodsBean) {
//        Glide.with(this).load(goodsBean.getUrl().get(0)).into(cover);
        presenter.getCover(goodsBean.getUrl().get(0));
        Glide.with(this).load(goodsBean.getUser().getPic()).into(userImage);
        userName.setText(goodsBean.getUser().getName());
        goodsName.setText(goodsBean.getTitle());
        goodsPrice.setText(" ￥" + goodsBean.getPrice() + " ");
        tell.setText("联系方式："+goodsBean.getTell());
        goodsDescribe.setText(goodsBean.getDescription());
        for (int i = 1; i < goodsBean.getUrl().size(); i++) {
            ImageView imageView = new ImageView(this);
            Glide.with(this).load(goodsBean.getUrl().get(i)).into(imageView);
            final int finalI = i;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(GoodsActivity.this,PhotoActivity.class);
                    intent.putExtra("sum",goodsBean.getUrl().size());
                    intent.putExtra("flag","f");
                    intent.putExtra("num", finalI);
                    for(int i = 0 ; i<goodsBean.getUrl().size() ; i++){
                        intent.putExtra("path"+i,goodsBean.getUrl().get(i));
                    }
                    startActivity(intent);
                }
            });
            imageGroup.addView(imageView);
        }
        browseNum.setText(goodsBean.getClick() + "");
        enshrineNum.setText(goodsBean.getCollect_num() + "");
        commentNum.setText(goodsBean.getComment_num()+"");
    }

    @Override
    public void setCover(final Bitmap bitmap) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                cover.setImageBitmap(bitmap);
            }
        });
    }

    @Override
    public void collectSuccess(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void collectFailure(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void comment(final List<CommentBean> list) {
        commentNum.setText(list.size() + "");
        for (int i = commentGroup.getChildCount(); i < list.size(); i++) {
            View view = getLayoutInflater().inflate(R.layout.goods_recycler_comment, null);
            CircleImageView imageView = (CircleImageView) view.findViewById(R.id.goods_comment_user_image);
            TextView userName = (TextView) view.findViewById(R.id.goods_comment_user_name);
            TextView time = (TextView) view.findViewById(R.id.goods_comment_time);
            TextView content = (TextView) view.findViewById(R.id.goods_comment_text);
            TextView textView = (TextView) view.findViewById(R.id.goods_comment_reply_btn);
            TextView re_content = (TextView)view.findViewById(R.id.goods_comment_re_content);
            View re_content_bg = (View)view.findViewById(R.id.goods_comment_re_content_bg);

            Glide.with(this).load(list.get(i).getUser().getPic()).into(imageView);
            userName.setText(list.get(i).getUser().getName());
            time.setText(list.get(i).getCreate_time());
            content.setText("回复:"+list.get(i).getContent());
            final int finalI = i;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    id = list.get(finalI).getId();
//                    edit.setVisibility(View.VISIBLE);
//                    commentText.requestFocus();
                    showPopup(list.get(finalI).getId(), list.get(finalI).getGoods_id(), TYPE_REPLY);
                }
            });
            re_content.setText(list.get(i).getRe_content());
            if(re_content.getText().toString().equals("")){
                re_content_bg.setVisibility(View.GONE);
            }else{
                re_content_bg.setVisibility(View.VISIBLE);
            }
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    showDeleteCommentDialog(list.get(finalI).getId(),list.get(finalI).getGoods_id(),view);
                    return false;
                }
            });
            commentGroup.addView(view);
            if (list.get(i).getId() == intent.getIntExtra("commentId", -1)) {
                scroll = view.getTop();
                Log.i("Goods",scroll+"+++");
                scrollView.scrollTo(0, scroll);
            }
        }
    }

    @Override
    public void replySuccess(String msg) {
        presenter.getComment(intent.getIntExtra("id", 0));
//        flag = false;
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void replyFailure(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void commentSuccess(String msg) {
        presenter.getComment(intent.getIntExtra("id", 0));
//        flag = false;
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void commentFailure(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void deleteSuccess(String msg,View position) {
        commentGroup.removeView(position);
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void deleteFailure(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showBar() {
        bar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideBar() {
        bar.setVisibility(View.GONE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                setResult(RESULT_OK);
                finish();
                break;
        }
        return false;
    }

    private void showDeleteCommentDialog(final int id, final int goods_id, final View position) {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog = new AlertDialog.Builder(GoodsActivity.this);
        normalDialog.setTitle("删除评论");
        normalDialog.setMessage("是否删除该评论?");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String token = (String) SharedPreferencesUtils.getParam(GoodsActivity.this, "token", "");
                        presenter.deleteComment(token, id, goods_id,position);
                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        normalDialog.show();
    }

    private void showPopup(final int id, final int goods_id, final int type) {
        View view = LayoutInflater.from(this).inflate(R.layout.comment_popup, null);
        WindowManager manager = this.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int width = (int) (outMetrics.widthPixels * 0.75);
        final PopupWindow popupWindow = new PopupWindow(view, width, LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        CoordinatorLayout layout = (CoordinatorLayout) findViewById(R.id.goods_layout);
        popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);
        final WindowManager.LayoutParams params = this.getWindow().getAttributes();
        params.alpha = 0.7f;
        this.getWindow().setAttributes(params);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                params.alpha = 1f;
                GoodsActivity.this.getWindow().setAttributes(params);
            }
        });
        final EditText content = (EditText) view.findViewById(R.id.goods_content_text);
        View cancel = (View) view.findViewById(R.id.goods_cancel);
        View send = (View) view.findViewById(R.id.goods_send);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String token = (String) SharedPreferencesUtils.getParam(GoodsActivity.this, "token", "");
                if (type == TYPE_COMMENT) {
                    presenter.addComment(token, goods_id, content.getText().toString());
                } else {
                    presenter.reply(token, id, content.getText().toString(), goods_id);
                }
                popupWindow.dismiss();
            }
        });
    }
}
