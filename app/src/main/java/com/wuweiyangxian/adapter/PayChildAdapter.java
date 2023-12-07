package com.wuweiyangxian.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wuweiyangxian.R;
import com.wuweiyangxian.bean.PayDetailBean;

import java.util.ArrayList;
import java.util.List;

public class PayChildAdapter extends RecyclerView.Adapter<PayChildAdapter.viewHolder> {

    private Context context;
    private Activity activity;
    private List<PayDetailBean.PayChildBean> list;

    public PayChildAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public void setData(List<PayDetailBean.PayChildBean> lists) {
        list = lists;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_pay_detail_child, null);
        return new viewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tv_name.setText(list.get(position).getName());
        holder.tv_price.setText(list.get(position).getPrice());
        holder.tv_time.setText(list.get(position).getTime());
        holder.rl_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener!= null){
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
        private final RelativeLayout rl_top;
        private final TextView tv_name;
        private final TextView tv_price;
        private final TextView tv_time;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            rl_top = itemView.findViewById(R.id.rl_top);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_time = itemView.findViewById(R.id.tv_time);
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
