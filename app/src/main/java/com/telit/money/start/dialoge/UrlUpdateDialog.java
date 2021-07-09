package com.telit.money.start.dialoge;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.hjq.toast.ToastUtils;
import com.telit.money.start.MyApplication;
import com.telit.money.start.R;
import com.telit.money.start.fragment.MyContentFragment;
import com.telit.money.start.utils.SharedPreferenceUtil;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * author: qzx
 * Date: 2019/7/3 9:54
 * <p>
 * 添加这个属性：setStyle(DialogFragment.STYLE_NO_FRAME, R.style.dialogForgetPwd);
 * 会导致TextInputLayout显示的颜色有问题
 */
public class UrlUpdateDialog extends DialogFragment {


    private EditText url_change_socket_ip;
    private EditText url_change_socket_port;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_url_update_layout, container, false);
        url_change_socket_ip = view.findViewById(R.id.url_change_socket_ip);
        url_change_socket_port = view.findViewById(R.id.url_change_socket_port);
       TextView url_change_cancel = view.findViewById(R.id.url_change_cancel);
       TextView url_change_confirm = view.findViewById(R.id.url_change_confirm);
        url_change_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        url_change_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String socketIp = url_change_socket_ip.getText().toString().trim();
                String socketPort = url_change_socket_port.getText().toString().trim();

                if (TextUtils.isEmpty(socketIp)){
                    ToastUtils.show("通信地址不能为空");
                    return;
                }
                if (TextUtils.isEmpty(socketPort)){
                    ToastUtils.show("通信端口不能为空");
                    return;
                }
                //把地址和端口保存到本地
                SharedPreferenceUtil.getInstance(MyApplication.getInstance()).setString("socketIp",socketIp);
                SharedPreferenceUtil.getInstance(MyApplication.getInstance()).setString("socketPort",socketPort);
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {

        super.onDestroyView();
    }
}
