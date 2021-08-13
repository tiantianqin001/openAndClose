package com.telit.money.start.fragment;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.telit.money.start.R;
import com.telit.money.start.utils.QZXTools;

/**
 * *****************************************************************
 * author: Administrator
 * time: 2021/1/13 14:03
 * name;
 * overview:
 * usage: 本地微课
 * ******************************************************************
 */
public class ExhibitionFragment extends Fragment implements View.OnClickListener {

    private TextView tv_show_check_one;
    private TextView tv_show_check_two;
    private TextView tv_show_check_three;
    private TextView tv_show_check_fore;
    private TextView tv_show_check_five;
    private TextView tv_show_check_six;
    private TextView tv_show_seven;
    private TextView tv_preface;
    private TextView tv_visual;
    private TextView tv_voice;
    private TextView tv_ecology;
    private TextView tv_tail;
    private TextView tv_casual;
    private TextView tv_out;
    private FrameLayout fl_content_light;

    private int lastClickPosition = 0;

    /**
     * 创建新实例
     *
     * @return
     */
    public static ExhibitionFragment newInstance() {
        ExhibitionFragment fragment = new ExhibitionFragment();
        return fragment;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_localmicrolecture;
    }


    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        //序厅
        LinearLayout ll_preface = root.findViewById(R.id.ll_preface);
        tv_preface = root.findViewById(R.id.tv_preface);
        tv_show_check_one = root.findViewById(R.id.tv_show_check_one);
        ll_preface.setOnClickListener(this);
        //视觉区
        LinearLayout ll_visual = root.findViewById(R.id.ll_visual);
        tv_visual = root.findViewById(R.id.tv_visual);
        tv_show_check_two = root.findViewById(R.id.tv_show_check_two);
        ll_visual.setOnClickListener(this);
        //语音区
        LinearLayout ll_voice = root.findViewById(R.id.ll_voice);
        tv_voice = root.findViewById(R.id.tv_voice);
        tv_show_check_three = root.findViewById(R.id.tv_show_check_three);
        ll_voice.setOnClickListener(this);
        //生态区
        LinearLayout ll_ecology = root.findViewById(R.id.ll_ecology);
        tv_ecology = root.findViewById(R.id.tv_ecology);
        tv_show_check_fore = root.findViewById(R.id.tv_show_check_fore);
        ll_ecology.setOnClickListener(this);
        //尾厅
        LinearLayout ll_tail = root.findViewById(R.id.ll_tail);
        tv_tail = root.findViewById(R.id.tv_tail);
        tv_show_check_five = root.findViewById(R.id.tv_show_check_five);
        ll_tail.setOnClickListener(this);
        //休闲区
        LinearLayout ll_casual = root.findViewById(R.id.ll_casual);
        tv_casual = root.findViewById(R.id.tv_casual);
        tv_show_check_six = root.findViewById(R.id.tv_show_check_six);
        ll_casual.setOnClickListener(this);
        //外立面
        LinearLayout ll_out = root.findViewById(R.id.ll_out);
        tv_out = root.findViewById(R.id.tv_out);
        tv_show_seven = root.findViewById(R.id.tv_show_seven);
        ll_out.setOnClickListener(this);

        //右边的内容
        fl_content_light = root.findViewById(R.id.fl_content_light);

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_content_light, new PrefaceFragment()).commit();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_preface:
                // 序厅区
                showCheckView(0);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fl_content_light, new PrefaceFragment()).commit();
                break;
            case R.id.ll_visual:
                // 视觉区
                showCheckView(1);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fl_content_light, new VisualFragment()).commit();
                break;
            case R.id.ll_voice:
                // 语音区
                showCheckView(2);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fl_content_light, new VoiceFragment()).commit();
                break;
            case R.id.ll_ecology:
                // 生态区
                showCheckView(3);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fl_content_light, new EcologyFragment()).commit();
                break;
            case R.id.ll_tail:
                // 尾厅区
                showCheckView(4);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fl_content_light, new TailFragment()).commit();
                break;
            case R.id.ll_casual:
                // 休闲区
                showCheckView(5);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fl_content_light, new CasualFragment()).commit();
                break;
            case R.id.ll_out:
                // 外立面
                showCheckView(6);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fl_content_light, new OutFragment()).commit();
                break;
        }

    }


    public void sendMessage() {
        QZXTools.logD("wobei diaoyong ke ...." + lastClickPosition);
        switch (lastClickPosition) {
            case 0:
                showCheckView(0);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fl_content_light, new PrefaceFragment()).commit();
                break;
            case 1:
                showCheckView(1);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fl_content_light, new VisualFragment()).commit();
                break;
            case 2:
                showCheckView(2);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fl_content_light, new VoiceFragment()).commit();
                break;
            case 3:
                showCheckView(3);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fl_content_light, new EcologyFragment()).commit();
                break;
            case 4:
                showCheckView(4);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fl_content_light, new TailFragment()).commit();
                break;
            case 5:
                showCheckView(5);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fl_content_light, new CasualFragment()).commit();
                break;
            case 6:
                showCheckView(6);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fl_content_light, new OutFragment()).commit();
                break;
        }
    }


    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void initData() {
        super.initData();


    }


    @Override
    protected void initListener() {
        super.initListener();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    private void showCheckView(int pos) {
        lastClickPosition = pos;
        switch (pos) {
            case 0:
                tv_show_check_one.setVisibility(View.VISIBLE);
                tv_show_check_two.setVisibility(View.INVISIBLE);
                tv_show_check_three.setVisibility(View.INVISIBLE);
                tv_show_check_fore.setVisibility(View.INVISIBLE);
                tv_show_check_five.setVisibility(View.INVISIBLE);
                tv_show_check_six.setVisibility(View.INVISIBLE);
                tv_show_seven.setVisibility(View.INVISIBLE);
                //设置颜色
                tv_preface.setTextColor(getResources().getColor(R.color.color_afff));
                tv_visual.setTextColor(getResources().getColor(R.color.dialog_bg));
                tv_voice.setTextColor(getResources().getColor(R.color.dialog_bg));
                tv_ecology.setTextColor(getResources().getColor(R.color.dialog_bg));
                tv_tail.setTextColor(getResources().getColor(R.color.dialog_bg));
                tv_casual.setTextColor(getResources().getColor(R.color.dialog_bg));
                tv_out.setTextColor(getResources().getColor(R.color.dialog_bg));
                break;
            case 1:
                tv_show_check_one.setVisibility(View.INVISIBLE);
                tv_show_check_two.setVisibility(View.VISIBLE);
                tv_show_check_three.setVisibility(View.INVISIBLE);
                tv_show_check_fore.setVisibility(View.INVISIBLE);
                tv_show_check_five.setVisibility(View.INVISIBLE);
                tv_show_check_six.setVisibility(View.INVISIBLE);
                tv_show_seven.setVisibility(View.INVISIBLE);
                //设置颜色
                tv_preface.setTextColor(getResources().getColor(R.color.dialog_bg));
                tv_visual.setTextColor(getResources().getColor(R.color.color_afff));
                tv_voice.setTextColor(getResources().getColor(R.color.dialog_bg));
                tv_ecology.setTextColor(getResources().getColor(R.color.dialog_bg));
                tv_tail.setTextColor(getResources().getColor(R.color.dialog_bg));
                tv_casual.setTextColor(getResources().getColor(R.color.dialog_bg));
                tv_out.setTextColor(getResources().getColor(R.color.dialog_bg));
                break;
            case 2:
                tv_show_check_one.setVisibility(View.INVISIBLE);
                tv_show_check_two.setVisibility(View.INVISIBLE);
                tv_show_check_three.setVisibility(View.VISIBLE);
                tv_show_check_fore.setVisibility(View.INVISIBLE);
                tv_show_check_five.setVisibility(View.INVISIBLE);
                tv_show_check_six.setVisibility(View.INVISIBLE);
                tv_show_seven.setVisibility(View.INVISIBLE);
                //设置颜色
                tv_preface.setTextColor(getResources().getColor(R.color.dialog_bg));
                tv_visual.setTextColor(getResources().getColor(R.color.dialog_bg));
                tv_voice.setTextColor(getResources().getColor(R.color.color_afff));
                tv_ecology.setTextColor(getResources().getColor(R.color.dialog_bg));
                tv_tail.setTextColor(getResources().getColor(R.color.dialog_bg));
                tv_casual.setTextColor(getResources().getColor(R.color.dialog_bg));
                tv_out.setTextColor(getResources().getColor(R.color.dialog_bg));
                break;
            case 3:
                tv_show_check_one.setVisibility(View.INVISIBLE);
                tv_show_check_two.setVisibility(View.INVISIBLE);
                tv_show_check_three.setVisibility(View.INVISIBLE);
                tv_show_check_fore.setVisibility(View.VISIBLE);
                tv_show_check_five.setVisibility(View.INVISIBLE);
                tv_show_check_six.setVisibility(View.INVISIBLE);
                tv_show_seven.setVisibility(View.INVISIBLE);
                //设置颜色
                tv_preface.setTextColor(getResources().getColor(R.color.dialog_bg));
                tv_visual.setTextColor(getResources().getColor(R.color.dialog_bg));
                tv_voice.setTextColor(getResources().getColor(R.color.dialog_bg));
                tv_ecology.setTextColor(getResources().getColor(R.color.color_afff));
                tv_tail.setTextColor(getResources().getColor(R.color.dialog_bg));
                tv_casual.setTextColor(getResources().getColor(R.color.dialog_bg));
                tv_out.setTextColor(getResources().getColor(R.color.dialog_bg));
                break;
            case 4:
                tv_show_check_one.setVisibility(View.INVISIBLE);
                tv_show_check_two.setVisibility(View.INVISIBLE);
                tv_show_check_three.setVisibility(View.INVISIBLE);
                tv_show_check_fore.setVisibility(View.INVISIBLE);
                tv_show_check_five.setVisibility(View.VISIBLE);
                tv_show_check_six.setVisibility(View.INVISIBLE);
                tv_show_seven.setVisibility(View.INVISIBLE);
                //设置颜色
                tv_preface.setTextColor(getResources().getColor(R.color.dialog_bg));
                tv_visual.setTextColor(getResources().getColor(R.color.dialog_bg));
                tv_voice.setTextColor(getResources().getColor(R.color.dialog_bg));
                tv_ecology.setTextColor(getResources().getColor(R.color.dialog_bg));
                tv_tail.setTextColor(getResources().getColor(R.color.color_afff));
                tv_casual.setTextColor(getResources().getColor(R.color.dialog_bg));
                tv_out.setTextColor(getResources().getColor(R.color.dialog_bg));
                break;
            case 5:
                tv_show_check_one.setVisibility(View.INVISIBLE);
                tv_show_check_two.setVisibility(View.INVISIBLE);
                tv_show_check_three.setVisibility(View.INVISIBLE);
                tv_show_check_fore.setVisibility(View.INVISIBLE);
                tv_show_check_five.setVisibility(View.INVISIBLE);
                tv_show_check_six.setVisibility(View.VISIBLE);
                tv_show_seven.setVisibility(View.INVISIBLE);
                //设置颜色
                tv_preface.setTextColor(getResources().getColor(R.color.dialog_bg));
                tv_visual.setTextColor(getResources().getColor(R.color.dialog_bg));
                tv_voice.setTextColor(getResources().getColor(R.color.dialog_bg));
                tv_ecology.setTextColor(getResources().getColor(R.color.dialog_bg));
                tv_tail.setTextColor(getResources().getColor(R.color.dialog_bg));
                tv_casual.setTextColor(getResources().getColor(R.color.color_afff));
                tv_out.setTextColor(getResources().getColor(R.color.dialog_bg));
                break;
            case 6:
                tv_show_check_one.setVisibility(View.INVISIBLE);
                tv_show_check_two.setVisibility(View.INVISIBLE);
                tv_show_check_three.setVisibility(View.INVISIBLE);
                tv_show_check_fore.setVisibility(View.INVISIBLE);
                tv_show_check_five.setVisibility(View.INVISIBLE);
                tv_show_check_six.setVisibility(View.INVISIBLE);
                tv_show_seven.setVisibility(View.VISIBLE);
                //设置颜色
                tv_preface.setTextColor(getResources().getColor(R.color.dialog_bg));
                tv_visual.setTextColor(getResources().getColor(R.color.dialog_bg));
                tv_voice.setTextColor(getResources().getColor(R.color.dialog_bg));
                tv_ecology.setTextColor(getResources().getColor(R.color.dialog_bg));
                tv_tail.setTextColor(getResources().getColor(R.color.dialog_bg));
                tv_casual.setTextColor(getResources().getColor(R.color.dialog_bg));
                tv_out.setTextColor(getResources().getColor(R.color.color_afff));
                break;


        }
    }





}
