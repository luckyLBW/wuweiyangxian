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
import com.wuweiyangxian.bean.MessageBean;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.viewHolder> {
    private Context context;
    private List<MessageBean> list;

    public MessageAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public void setData(List<MessageBean> lists) {
        list = lists;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_message, null);
        return new viewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, @SuppressLint("RecyclerView") int position) {
//        if (position == list.size() -1 ){
//            holder.tv_des.setVisibility(View.VISIBLE);
//        }else{
//            holder.tv_des.setVisibility(View.GONE);
//        }
        holder.tv_name.setText(list.get(position).getName());
        Glide.with(context).load(list.get(position).getImg()).into(holder.iv_head);
        holder.ll_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
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
        private final TextView tv_name;
        private final TextView tv_des;
        private final ImageView iv_head;
        private final LinearLayout ll_list;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            iv_head = itemView.findViewById(R.id.iv_head);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_des = itemView.findViewById(R.id.tv_des);
            ll_list = itemView.findViewById(R.id.ll_list);
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
