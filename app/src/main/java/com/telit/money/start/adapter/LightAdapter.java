package com.telit.money.start.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.telit.money.start.R;
import com.telit.money.start.bean.LightBean;
import com.telit.money.start.bean.SaveLight;
import com.telit.money.start.bean.ZhXiangBean;

import org.litepal.LitePal;

import java.util.List;

public class LightAdapter extends RecyclerView.Adapter<LightAdapter.ViewHolder>{

    private Context context;
    private List<LightBean> lightBeans;
    private onClickListerner listener;

    public LightAdapter(Context context, List<LightBean> lightBeans){

        this.context = context;
        this.lightBeans = lightBeans;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_light_name, null);
        ViewHolder viewHolder=new ViewHolder(view);
        viewHolder.setIsRecyclable(false);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_device_close.setText(lightBeans.get(position).getClose());
        holder.tv_device_rebot.setText(lightBeans.get(position).getOpen());
        holder.tv_device_adress.setText(lightBeans.get(position).getAddress());



        holder.tv_device_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listener!=null){
                    listener.onClick(holder.tv_device_close,position,"关灯");
                }

            }
        });

        holder.tv_device_rebot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.onClick( holder.tv_device_rebot,position,"开灯");
                }
            }
        });

        holder.tv_device_adress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.onClick( holder.tv_device_adress,position,"地址");
                }
            }
        });


        List<SaveLight> all = LitePal.findAll(SaveLight.class);
        for (SaveLight saveLight : all) {
            int index = saveLight.getIndex();
            if (position == index){
                holder.tv_device_adress.setText(saveLight.getWord());
            }
        }
    }
    @Override
    public int getItemCount() {
        return lightBeans.size();
    }
    protected class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView tv_device_close;
        private final TextView tv_device_rebot;
        private final TextView tv_device_adress;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_device_close = itemView.findViewById(R.id.tv_device_close);
            tv_device_rebot = itemView.findViewById(R.id.tv_device_rebot);
            tv_device_adress = itemView.findViewById(R.id.tv_device_adress);
        }
    }


    public interface onClickListerner  {
      void   onClick(TextView view, int position,String type);
    }

    public void setOnCliclListener(onClickListerner listener){

        this.listener = listener;
    }


}
