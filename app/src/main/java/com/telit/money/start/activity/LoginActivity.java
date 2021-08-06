package com.telit.money.start.activity;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;
import com.gyf.immersionbar.ImmersionBar;
import com.hjq.toast.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.telit.money.start.MainActivity;
import com.telit.money.start.MyApplication;
import com.telit.money.start.R;
import com.telit.money.start.bean.LoginBean;
import com.telit.money.start.customview.CustomEditText;
import com.telit.money.start.fragment.CircleProgressDialogFragment;
import com.telit.money.start.net.Common;
import com.telit.money.start.utils.QZXTools;
import com.telit.money.start.utils.SharedPreferenceUtil;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * 以后可能需要回显用户名等
     */
    private SharedPreferences sp_student;

    private static final int Server_Error = 0;
    private static final int Error404 = 1;
    private static final int Operate_Success = 2;
    private static final int Other_Error = 3;
    private static final int Oauth_Result = 4;

    private static boolean isShow=false;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case Server_Error:
                    break;
                case Error404:
                    if (isShow) {
                    }

                    break;
                case Operate_Success:
                    break;
                case Other_Error:
                    if (isShow) {

                    }
                    break;
                case Oauth_Result:
                    if (isShow) {
                        break;
                    }
            }
        }
    };
    private CustomEditText username;
    private CustomEditText password;
    private CircleProgressDialogFragment circleProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //设置导航栏的颜色
        //ImmersionBar.with(this).navigationBarColor(R.color.colorPrimary).init();
        isShow=true;
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        QZXTools.logE("width=" + width + ";height=" + height, null);

        initView();
        initData();



    }

    private void initView() {
        //wifi
       ImageView img_wifi=(ImageView) findViewById(R.id.img_wifi);
       //登录
       ImageView login_btn=(ImageView) findViewById(R.id.login_btn);
        //用户名
        username = (CustomEditText) findViewById(R.id.login_username_edit);
        //密码
        password = (CustomEditText) findViewById(R.id.login_pwd_edit);
        login_btn.setOnClickListener(this);
        img_wifi.setOnClickListener(this);
    }

    private void initData() {
    }


    @Override
    protected void onDestroy() {
        QZXTools.setmToastNull();


        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_wifi:
                startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                break;
            case R.id.login_btn:
                if (TextUtils.isEmpty(username.getText().toString().trim())) {
                    QZXTools.popCommonToast(this, getResources().getString(R.string.tips_account), false);
                    return;
                } else if (TextUtils.isEmpty(password.getText().toString().trim())) {
                    QZXTools.popCommonToast(this, getResources().getString(R.string.tips_pwd), false);
                    return;
                }

                //是否有网络
                if (!QZXTools.isNetworkAvailable()) {
                    QZXTools.popCommonToast(this, "没有网络耶！", false);
                    return;
                }
                circleProgressDialog = new CircleProgressDialogFragment();
                circleProgressDialog.show(getSupportFragmentManager(),CircleProgressDialogFragment.class.getSimpleName());
                //开始调接口
                getAPIInfo(username.getText().toString().trim(),password.getText().toString().trim());

                break;
        }
    }



    private void getAPIInfo(String username, String password) {

        try {
            JSONObject jsonObject=new JSONObject();
           // jsonObject.put("loginName",username);
            jsonObject.put("loginName","jnrstest");
           // jsonObject.put("password",password);
            jsonObject.put("password","jnrstest321");
            String url= Common.loginApp;
            RequestBody body = RequestBody.create(Common.JSON, jsonObject.toString());
            OkGo.<String>post(url)
                    .tag(this)
                    .upRequestBody(body)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            if (circleProgressDialog!=null)circleProgressDialog.dismiss();
                            QZXTools.logD(response.body().toString());
                            String body = response.body().toString();
                            try {
                                LoginBean loginBean = JSON.parseObject(body, LoginBean.class);
                                int code = loginBean.getCode();
                                    if (code == 1000){
                                        LoginBean.DataBean data = loginBean.getData();
                                        String userId = data.getUserId();
                                        //把userid 保存到本地
                                        if (!TextUtils.isEmpty(userId)){
                                            SharedPreferenceUtil.getInstance(MyApplication.getInstance())
                                                    .setString("userId",userId);
                                            //把用户名和密码保存到本地
                                            SharedPreferenceUtil.getInstance(MyApplication.getInstance())
                                                    .setString("loginName","jnrstest");
                                            SharedPreferenceUtil.getInstance(MyApplication.getInstance())
                                                    .setString("password","jnrstest321");
                                            //进入主界面
                                            Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }else {
                                            ToastUtils.show("调接口失败");
                                        }

                                    }
                            }catch (Exception e){
                                ToastUtils.show("调接口失败");
                            }


                        }

                        @Override
                        public void onError(Response<String> response) {
                            super.onError(response);
                            QZXTools.logD(response.body().toString());
                            if (circleProgressDialog!=null)circleProgressDialog.dismiss();
                            ToastUtils.show("登录失败");
                        }
                    });
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
