package com.telit.money.start.fragment;
import com.telit.money.start.R;




/**
 * *****************************************************************
 * author: Administrator
 * time: 2021/1/13 14:03
 * name;
 * overview:
 * usage: 云盘微课
 * ******************************************************************
 */
public class ExhibitionItemFragment extends Fragment  {


    private int TOTAL_COUNTER;
    private static int mCurrentCounter = 0;

    private int pageNo;
    private int pageSize = 10;



    /**
     * 创建新实例
     *
     * @return
     */
    public static ExhibitionItemFragment newInstance() {
       ExhibitionItemFragment fragment = new ExhibitionItemFragment();
        return fragment;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_cloudmicrolecture;
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

}
