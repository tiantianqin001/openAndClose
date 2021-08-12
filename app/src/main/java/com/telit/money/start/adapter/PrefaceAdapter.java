package com.telit.money.start.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.greendao.dao.AdviceBeanDao;
import com.telit.money.start.MyApplication;
import com.telit.money.start.R;
import com.telit.money.start.bean.AdviceBean;
import com.telit.money.start.customview.SwitchView;
import com.telit.money.start.utils.QZXTools;

import java.util.List;
import java.util.Map;

public class PrefaceAdapter extends RecyclerView.Adapter<PrefaceAdapter.ViewHolder>{


    private Context context;
    private List<AdviceBean> datas;
    private String type;
    private onClickListener listener;
    private int position;

    public PrefaceAdapter(Context context, List<AdviceBean> adviceBeans, String type){

        this.context = context;
        this.datas = adviceBeans;
        this.type = type;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //recycleview item不能显示全部 todo  这个是为啥
        View view = LayoutInflater.from(context).inflate( R.layout.item_preface_name,parent,false );
      //  View view = View.inflate(context, R.layout.item_preface_name, parent);
        ViewHolder viewHolder=new ViewHolder(view);
        viewHolder.setIsRecyclable(false);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.tv_all_line.setText("一共"+datas.size()+"路");
        holder.tv_light_name.setText(datas.get(position).getName());
        //如果当前开关保存到本地要回显
        List<AdviceBean> adviceBeans = MyApplication.getInstance().getDaoSession().getAdviceBeanDao().loadAll();
        if (adviceBeans!=null & adviceBeans.size()>0){
            for (AdviceBean adviceBean : adviceBeans) {
                String typeId = adviceBean.getTypeId();
                if (typeId.equals(datas.get(position).getTypeId())){
                    holder.sv_open_and_close.setOpened(adviceBean.getIsOpen());
                }
            }
        }

        QZXTools.logE("qin998"+adviceBeans.toString(),null);

        holder.sv_open_and_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isOpen=false;
                AdviceBean adviceBean=null;
                AdviceBeanDao adviceBeanDao = MyApplication.getInstance().getDaoSession().getAdviceBeanDao();
                 adviceBean= adviceBeanDao.queryBuilder().where(AdviceBeanDao.Properties.TypeId
                        .eq(datas.get(position).getTypeId())).unique();
                if (adviceBean!=null){
                     isOpen = adviceBean.getIsOpen();
                    adviceBean.setOpen(!isOpen);
                    isOpen=!isOpen;
                }else {
                    adviceBean=datas.get(position);
                    adviceBean.setOpen(true);
                    isOpen=true;
                }
                //把要点击的开关保存到本地
                MyApplication.getInstance().getDaoSession().getAdviceBeanDao().insertOrReplace(adviceBean);
                //点击后状态的回显
                if (listener!=null){
                    /**
                     *
                     * @param position 这个主要是第几路
                     * @param type 休闲区  ，语音区，生态区  等
                     * @param isOpen 灯的开关
                     * @param adress 地址 要不是01   或 02
                     */
                    listener.onClick(Integer.valueOf(datas.get(position).getRoad()),
                            type,isOpen,datas.get(position).getAdress(),position);
                }
            }
        });

    }
    @Override
    public int getItemCount() {
        return datas.size();
    }
    protected class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_all_line;
        private TextView tv_light_name;
        private SwitchView sv_open_and_close;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_all_line=   itemView.findViewById(R.id.tv_all_line);
            tv_light_name=   itemView.findViewById(R.id.tv_light_name);
            sv_open_and_close=   itemView.findViewById(R.id.sv_open_and_close);

        }
    }

    public interface onClickListener{
        /**
         *
         * @param road 这个是第几路
         * @param type 休闲区  ，语音区，生态区  等
         * @param isOpen 灯的开关
         * @param adress 地址 要不是01   或 02
         * @param position 下表
         */
        void onClick(int road,String type,boolean isOpen,String adress,int position);
    }
    public void setonClickListener(onClickListener listener){

        this.listener = listener;
    }



}
