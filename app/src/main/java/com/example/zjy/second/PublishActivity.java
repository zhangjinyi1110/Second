package com.example.zjy.second;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.donkingliang.imageselector.utils.ImageSelectorUtils;
import com.example.zjy.second.bean.GoodsBean;
import com.example.zjy.second.presenter.PublishPresenter;
import com.example.zjy.second.utils.SharedPreferencesUtils;
import com.example.zjy.second.view.PublishView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PublishActivity extends AppCompatActivity implements PublishView {
    private Toolbar toolbar;
    private RadioGroup group;
    private EditText title;
    private EditText context;
    private View moneyView;
    private View classifyView;
    private View contactView;
    private Button publishBtn;
    private RadioButton sell;
    private RadioButton exchange;
    private RadioButton present;
    private TextView classifyText;
    private TextView contactText;
    private TextView moneyText;
    private PublishPresenter publishPresenter;
    private ViewGroup imageGroup1;
    private ViewGroup imageGroup2;
    private ImageView imageView1;
    private View view;
    private PublishBroadcastReceiver broadcastReceiver;
    private IntentFilter intentFilter;
//    private ImageView imageView2;
//    private ImageView imageView3;
//    private ImageView imageView4;
//    private ImageView imageView5;
//    private ImageView imageView6;

    private int classifyId;
    //    private String path;
    private List<String> list;
    private Uri imageUri;
    private String path;
    private int maxNum = 6;
    private int nowNum = 0;

    private final int TO_CLASSIFY = 0;
    private final int TAKE_PHONE = 1;
    private final int SELECT_PHONE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        MainActivity.setStatusBarColor(this,0xff0555ff);
        toolbar = (Toolbar) findViewById(R.id.publish_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.close);
        initView();
        publishPresenter = new PublishPresenter(this);
        list = new ArrayList<>();
        broadcastReceiver = new PublishBroadcastReceiver();
        intentFilter = new IntentFilter("android.intent.action.PUBLISH_ACTION");
        registerReceiver(broadcastReceiver,intentFilter);
    }

    private void initView() {
        view = this.getLayoutInflater().inflate(R.layout.popup_window, null);
        group = (RadioGroup) findViewById(R.id.publish_radio);
        sell = (RadioButton) findViewById(R.id.publish_radio_sell);
        exchange = (RadioButton) findViewById(R.id.publish_radio_exchange);
        present = (RadioButton) findViewById(R.id.publish_radio_present);
        title = (EditText) findViewById(R.id.publish_edit_title);
        context = (EditText) findViewById(R.id.publish_edit_context);
        moneyView = (View) findViewById(R.id.publish_money_option);
        moneyText = (TextView) findViewById(R.id.publish_money_text);
        contactView = (View) findViewById(R.id.publish_contact_option);
        contactText = (TextView) findViewById(R.id.publish_contact_text);
        classifyView = (View) findViewById(R.id.publish_classify_option);
        classifyText = (TextView) findViewById(R.id.publish_classify_text);
        publishBtn = (Button) findViewById(R.id.publish_btn);
        imageGroup1 = (ViewGroup)findViewById(R.id.publish_group1);
        imageGroup2 = (ViewGroup)findViewById(R.id.publish_group2);
        imageView1 = (ImageView) findViewById(R.id.goods_image1);
        WindowManager manager = this.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels-30;
        imageView1.setLayoutParams(new LinearLayout.LayoutParams(width/4,width/4));
        Log.i("PublishActivity",width+"+++++"+(view.getWidth()-10));
//        imageView2 = (ImageView) findViewById(R.id.goods_image2);
//        imageView3 = (ImageView) findViewById(R.id.goods_image3);
//        imageView4 = (ImageView) findViewById(R.id.goods_image4);
//        imageView5 = (ImageView) findViewById(R.id.goods_image5);
//        imageView6 = (ImageView) findViewById(R.id.goods_image6);

        title.requestFocus();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                select_photo();
//                take_photo();
                InputMethodManager imm =  (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                if(imm != null) {
                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
                }
                if(nowNum!=maxNum){
                    showPopup();
                }else{
                    Toast.makeText(getApplicationContext(),"只能添加6张图。",Toast.LENGTH_SHORT).show();
                }
            }
        });
        classifyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm =  (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                if(imm != null) {
                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
                }
                Intent intent = new Intent(PublishActivity.this, ClassifyActivity.class);
                startActivityForResult(intent, TO_CLASSIFY);
            }
        });
        moneyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm =  (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                if(imm != null) {
                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
                }
                showMoneyPopup();
            }
        });
        contactView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm =  (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                if(imm != null) {
                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
                }
                showContactPopup();
            }
        });
        publishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (title.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "标题不能为空。", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (context.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "物品描述不能为空。", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (classifyText.getText().toString().equals("分类")) {
                    Toast.makeText(getApplicationContext(), "请选择物品分类。", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (contactText.getText().toString().equals("联系方式")) {
                    Toast.makeText(getApplicationContext(), "请输入联系方式。", Toast.LENGTH_SHORT).show();
                    return;
                }
//                if(contactText.getText().toString().equals("联系方式")){
//                    Toast.makeText(getApplicationContext(),"请输入联系方式。",Toast.LENGTH_SHORT).show();
//                    return;
//                }

                GoodsBean goodsBean = new GoodsBean();
                goodsBean.setTitle(title.getText().toString());
                goodsBean.setDescription(context.getText().toString());
                if (group.getCheckedRadioButtonId() == sell.getId()) {
                    goodsBean.setType_way(1);
                    if (moneyText.getText().toString().equals("价钱")) {
                        Toast.makeText(getApplicationContext(), "请输入价钱。", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    goodsBean.setPrice(Integer.valueOf(moneyText.getText().toString()));
                } else if (group.getCheckedRadioButtonId() == exchange.getId()) {
                    goodsBean.setType_way(2);
                } else {
                    goodsBean.setType_way(3);
                }
                goodsBean.setCategory_id(classifyId);
                goodsBean.setTell(contactText.getText().toString());
                goodsBean.setUrl(list);
                publishPresenter.upload(goodsBean);
            }
        });
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i) {
                    case R.id.publish_radio_sell:
                        moneyView.setVisibility(View.VISIBLE);
                        break;
                    case R.id.publish_radio_exchange:
                        moneyView.setVisibility(View.GONE);
                        break;
                    case R.id.publish_radio_present:
                        moneyView.setVisibility(View.GONE);
                        break;
                }
                InputMethodManager imm =  (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                if(imm != null) {
                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if(requestCode==0){
//            if(resultCode==RESULT_OK){
//                classifyText.setText(data.getStringExtra("classify"));
//                classifyId = data.getIntExtra("id",0);
//            }
//        }
//    }

    @Override
    public void success(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void failure(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    private void showMoneyPopup() {
        View view = LayoutInflater.from(this).inflate(R.layout.comment_popup, null);
        WindowManager manager = this.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int width = (int)(outMetrics.widthPixels * 0.5);
        final PopupWindow popupWindow = new PopupWindow(view, width,LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        CoordinatorLayout layout = (CoordinatorLayout) findViewById(R.id.publish_layout);
        popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);
        final WindowManager.LayoutParams params = this.getWindow().getAttributes();
        params.alpha = 0.7f;
        this.getWindow().setAttributes(params);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                params.alpha = 1f;
                PublishActivity.this.getWindow().setAttributes(params);
            }
        });
        final EditText content = (EditText) view.findViewById(R.id.goods_content_text);
        content.setInputType(InputType.TYPE_CLASS_NUMBER);
        TextView unit = (TextView)view.findViewById(R.id.goods_unit_text);
        unit.setVisibility(View.VISIBLE);
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
                moneyText.setText(content.getText().toString());
                popupWindow.dismiss();
            }
        });
    }

    private void showContactPopup() {
        View view = LayoutInflater.from(this).inflate(R.layout.comment_popup, null);
        WindowManager manager = this.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int width = (int)(outMetrics.widthPixels * 0.75);
        final PopupWindow popupWindow = new PopupWindow(view, width,LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        CoordinatorLayout layout = (CoordinatorLayout) findViewById(R.id.publish_layout);
        popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);
        final WindowManager.LayoutParams params = this.getWindow().getAttributes();
        params.alpha = 0.7f;
        this.getWindow().setAttributes(params);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                params.alpha = 1f;
                PublishActivity.this.getWindow().setAttributes(params);
            }
        });
        final EditText content = (EditText) view.findViewById(R.id.goods_content_text);
        content.setInputType(InputType.TYPE_CLASS_NUMBER);
        content.setHint("请输入手机号码");
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
                contactText.setText(content.getText().toString());
                popupWindow.dismiss();
            }
        });
    }

    private void addImage(Bitmap bitmap){
        ImageView imageView = new ImageView(PublishActivity.this);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(imageView1.getWidth(), imageView1.getHeight()));
        imageView.setImageBitmap(bitmap);
        nowNum++;
        if(nowNum<=3){
            imageGroup1.addView(imageView,nowNum-1);
        }else if(nowNum==4){
            imageGroup1.addView(imageView,nowNum-1);
            imageGroup1.removeView(imageView1);
            imageGroup2.addView(imageView1);
        }else if(nowNum==5){
            imageGroup2.addView(imageView,0);
        }else if(nowNum==6){
            imageGroup2.addView(imageView,1);
        }
    }

    private void showPopup() {
        final PopupWindow popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        CoordinatorLayout layout = (CoordinatorLayout) findViewById(R.id.publish_layout);
        popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);
        final WindowManager.LayoutParams params = this.getWindow().getAttributes();
        params.alpha = 0.7f;
        this.getWindow().setAttributes(params);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                params.alpha = 1f;
                PublishActivity.this.getWindow().setAttributes(params);
            }
        });
        TextView takePhoto = (TextView)view.findViewById(R.id.popup_take_photo);
        TextView selectPhoto = (TextView)view.findViewById(R.id.popup_select_photo);
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                take_photo();
                popupWindow.dismiss();
            }
        });
        selectPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select_photo();
                popupWindow.dismiss();
            }
        });
    }

    private void take_photo() {
        ///+System.currentTimeMillis()+".jpg"
        File dir = new File(Environment.getExternalStorageDirectory().getPath() + "/" + getPackageName() + "/image");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        path = dir.getAbsolutePath() + "/" + System.currentTimeMillis() + ".jpg";
//        File file = new File(getExternalCacheDir(),"asdf.jpg");
        File file = new File(path);
        try {
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT >= 24) {
            imageUri = FileProvider.getUriForFile(this, "com.example.zjy.second.PublishActivity", file);
        } else {
            imageUri = Uri.fromFile(file);
        }
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, TAKE_PHONE);
    }

    private void select_photo() {
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
//        } else {
//            openAlbum();
//        }
        ImageSelectorUtils.openPhoto(PublishActivity.this, SELECT_PHONE, false, maxNum-nowNum);
    }

    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, SELECT_PHONE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TO_CLASSIFY:
                if (resultCode == RESULT_OK) {
                    classifyText.setText(data.getStringExtra("classify"));
                    classifyId = data.getIntExtra("id", 0);
                }
                break;
            case SELECT_PHONE:
                if(data!=null){
                    ArrayList<String> images = data.getStringArrayListExtra(ImageSelectorUtils.SELECT_RESULT);
                    for(int i = 0 ; i<images.size() ; i++){
                        addImage(BitmapFactory.decodeFile(images.get(i)));
                        list.add(images.get(i));
                    }
                    goPhoto();
                }
                break;
            case TAKE_PHONE:
                if (resultCode == RESULT_OK) {
                    try {
                        FileInputStream fileInputStream = new FileInputStream(path);
                        Bitmap bitmap = BitmapFactory.decodeStream(fileInputStream);
                        addImage(bitmap);
                        fileInputStream.close();
                        list.add(path);
                        goPhoto();
                        Uri uri = Uri.fromFile(new File(path));
                        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.i("PublishActivity", imageUri.getAuthority());
                }
                break;
            default:
                break;
        }
    }

    private void goPhoto(){
        if(nowNum<=3){
            for(int i = 0 ; i<imageGroup1.getChildCount()-1 ; i++){
                final int finalI = i;
                Log.i("PublishActivity111",finalI+"+++");
                imageGroup1.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(PublishActivity.this,PhotoActivity.class);
                        intent.putExtra("sum",list.size());
                        intent.putExtra("flag","t");
                        intent.putExtra("num", finalI);
                        for(int i = 0 ; i<list.size() ; i++){
                            intent.putExtra("path"+i,list.get(i));
                        }
                        startActivity(intent);
                    }
                });
            }
            return;
        }
        for(int i = 0 ; i<imageGroup1.getChildCount() ; i++){
            final int finalI = i;
            Log.i("PublishActivity111",finalI+"+++");
            imageGroup1.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(PublishActivity.this,PhotoActivity.class);
                    intent.putExtra("sum",list.size());
                    intent.putExtra("flag","t");
                    intent.putExtra("num", finalI);
                    for(int i = 0 ; i<list.size() ; i++){
                        intent.putExtra("path"+i,list.get(i));
                    }
                    startActivity(intent);
                }
            });
        }
        for(int i = 0 ; i<imageGroup2.getChildCount()-1 ; i++){
            final int finalI = i+4;
            Log.i("PublishActivity222",finalI+"+++");
            imageGroup2.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(PublishActivity.this,PhotoActivity.class);
                    intent.putExtra("sum",list.size());
                    intent.putExtra("flag","t");
                    intent.putExtra("num", finalI);
                    for(int i = 0 ; i<list.size() ; i++){
                        intent.putExtra("path"+i,list.get(i));
                    }
                    startActivity(intent);
                }
            });
        }
//        for(int i = 0 ; i<imageGroup2.getChildCount() ; i++){
//            final int finalI = i;
//            Log.i("PublishActivity222",finalI+"+++");
//            imageGroup2.getChildAt(i).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(PublishActivity.this,PhotoActivity.class);
//                    intent.putExtra("sum",list.size());
//                    intent.putExtra("flag","t");
//                    intent.putExtra("num", finalI);
//                    for(int i = list.size()-1 ; i>=0 ; i--){
//                        intent.putExtra("path"+i,list.get(i));
//                    }
//                    startActivity(intent);
//                }
//            });
//        }
//        for(int i = imageGroup2.getChildCount() ; i<imageGroup2.getChildCount()+imageGroup1.getChildCount()-1 ; i++){
//            final int finalI = i - imageGroup2.getChildCount();
//            Log.i("PublishActivity",finalI+"+++");
//            imageGroup1.getChildAt(i).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(PublishActivity.this,PhotoActivity.class);
//                    intent.putExtra("sum",list.size());
//                    intent.putExtra("flag","t");
//                    intent.putExtra("num", finalI);
//                    for(int i = list.size()-1 ; i>=0 ; i--){
//                        intent.putExtra("path"+i,list.get(i));
//                    }
//                    startActivity(intent);
//                }
//            });
//        }
    }

    /**
     * 4.4以下系统处理图片的方法
     */
    private void handleImageBeforeKitKat(Intent data) {

        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);

    }


    /**
     * 4.4及以上系统处理图片的方法
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void handleImgeOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this, uri)) {
            //如果是document类型的uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                //解析出数字格式的id
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            } else if ("content".equalsIgnoreCase(uri.getScheme())) {
                //如果是content类型的uri，则使用普通方式处理
                imagePath = getImagePath(uri, null);
            } else if ("file".equalsIgnoreCase(uri.getScheme())) {
                //如果是file类型的uri，直接获取图片路径即可
                imagePath = uri.getPath();
            }
            //根据图片路径显示图片
            displayImage(imagePath);
        }
    }

    /**
     * 根据图片路径显示图片的方法
     */
    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            Log.i("PublishActivity", imagePath);
            list.add(imagePath);
//            imageView.setImageBitmap(bitmap);
            addImage(bitmap);
        } else {
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 通过uri和selection来获取真实的图片路径
     */
    private String getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    Toast.makeText(this, "you need the permission", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    public class PublishBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            deleteImage(intent.getIntExtra("deleteNum",0));
        }
    }

    private void deleteImage(int deleteNum) {
        list.remove(deleteNum);
        nowNum = 0;
        imageGroup1.removeAllViews();
        imageGroup2.removeAllViews();
        imageGroup1.addView(imageView1);
        for(int i = 0 ; i<list.size() ; i++){
            addImage(BitmapFactory.decodeFile(list.get(i)));
        }
        goPhoto();
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(broadcastReceiver);
        super.onDestroy();
    }
}
