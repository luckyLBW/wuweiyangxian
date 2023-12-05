package com.wuweiyangxian.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wuweiyangxian.R;
import com.wuweiyangxian.bean.MonitorBean;

import java.util.ArrayList;
import java.util.List;

public class MonitorAdapter extends RecyclerView.Adapter<MonitorAdapter.viewHolder> {

    private Context context;
    private List<MonitorBean> list;

    public MonitorAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public void setData(List<MonitorBean> lists) {
        list = lists;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_monitor, null);
        return new viewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (list.get(position).getState() == 1) {
            holder.state.setText("在线");
            holder.state.setCompoundDrawablesRelativeWithIntrinsicBounds(context.getDrawable(R.mipmap.icon_monitor_green), null, null, null);
        } else {
            holder.state.setText("离线");
            holder.state.setCompoundDrawablesRelativeWithIntrinsicBounds(context.getDrawable(R.mipmap.icon_monitor_grey), null, null, null);
        }

        Glide.with(context).load(list.get(position).getImg()).into(holder.iv_monitor);

        holder.ll_monitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null){
                    listener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private final TextView state;
        private final ImageView iv_monitor;
        private final LinearLayout ll_monitor;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            state = itemView.findViewById(R.id.tv_state);
            iv_monitor = itemView.findViewById(R.id.iv_monitor);
            ll_monitor = itemView.findViewById(R.id.ll_monitor);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
