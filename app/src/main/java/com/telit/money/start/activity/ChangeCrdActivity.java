package com.telit.money.start.activity;

import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.gyf.immersionbar.ImmersionBar;
import com.telit.money.start.R;

/**
 * 如何解决例如：请求还在响应，界面已经退出，然后响应失败的界面吐司造成的内存泄露？
 */
public class ChangeCrdActivity extends AppCompatActivity {

    private ImageView back_img;


    private static final int Server_Error = 0;
    private static final int Error404 = 1;
    private static final int Operator_Success = 2;
    private static boolean isShow=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setContentView(R.layout.activity_time_table_activtity);

        //设置导航栏的颜色
     //   ImmersionBar.with(this).navigationBarColor(R.color.colorPrimary).init();

        back_img = findViewById(R.id.timetable_back);


    }

    @Override
    protected void onDestroy() {
        //防止内存泄露 ---放置于onDestroy()中


        super.onDestroy();
    }


}
