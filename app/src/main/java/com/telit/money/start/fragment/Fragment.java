package com.telit.money.start.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.telit.money.start.bean.XmlBean;
import com.telit.money.start.utils.NumUtil;
import com.telit.money.start.utils.QZXTools;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public abstract class Fragment extends androidx.fragment.app.Fragment {

    protected View mRoot;

    //这个是获取电脑的地址方便关机用的
    protected List<XmlBean> addressList = new ArrayList<>();
    // 标示是否第一次初始化数据
    protected boolean mIsFirstInitData = true;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // 初始化参数
        initArgs(getArguments());


        //解析数据
        try {
            InputStream inputStream =getContext(). getAssets().open("address.xml");
            List<XmlBean> xmlBeans = NumUtil.getUrls(inputStream);
            QZXTools.logE(xmlBeans.toString(),null);
            addressInfo(xmlBeans);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRoot == null) {
            int layId = getContentLayoutId();
            // 初始化当前的跟布局，但是不在创建时就添加到container里边
            View root = inflater.inflate(layId, container, false);
            initWidget(root);
            mRoot = root;
        } else {
            if (mRoot.getParent() != null) {
                // 把当前Root从其父控件中移除
                ((ViewGroup) mRoot.getParent()).removeView(mRoot);
            }
        }

        return mRoot;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (mIsFirstInitData) {
            // 触发一次以后就不会触发
            mIsFirstInitData = false;
            // 触发
            onFirstInit();
        }
        initView();

        // 当View创建完成后初始化数据
        initData();

        initListener();
    }

    protected  void initListener(){};

    protected void initView() {
    }

    /**
     * 初始化相关参数
     */
    protected void initArgs(Bundle bundle) {

    }

    /**
     * 第一次加载数据
     */
    protected void onFirstInit() {

    }


    /**
     * 得到当前界面的资源文件Id
     *
     * @return 资源文件Id
     */
    @LayoutRes
    protected abstract   int getContentLayoutId();


    /**
     * 初始化控件
     */
    protected void initWidget(View root) {

    }

    protected void addressInfo(List<XmlBean> xmlBeans){

    }

    /**
     * 初始化数据
     */
    protected void initData() {

    }

    protected  void sendMessage(){};
}
