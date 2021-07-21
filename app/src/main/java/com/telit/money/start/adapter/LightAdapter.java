package com.telit.money.start.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.telit.money.start.R;
import com.telit.money.start.bean.LightBean;
import com.telit.money.start.bean.ZhXiangBean;

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
        View view = View.inflate(context, R.layout.item_student_name, null);
        ViewHolder viewHolder=new ViewHolder(view);
        viewHolder.setIsRecyclable(false);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_device_close.setText(lightBeans.get(position).getClose());
        holder.tv_device_rebot.setText(lightBeans.get(position).getOpen());

        holder.tv_device_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listener!=null){
                    listener.onClick(position,"关灯");
                }

            }
        });

        holder.tv_device_rebot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.onClick(position,"开灯");
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return lightBeans.size();
    }
    protected class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView tv_device_close;
        private final TextView tv_device_rebot;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_device_close = itemView.findViewById(R.id.tv_device_close);
            tv_device_rebot = itemView.findViewById(R.id.tv_device_rebot);
        }
    }


    public interface onClickListerner  {
      void   onClick(int position,String type);
    }

    public void setOnCliclListener(onClickListerner listener){

        this.listener = listener;
    }


}
