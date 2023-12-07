package com.wuweiyangxian.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wuweiyangxian.R;
import com.wuweiyangxian.bean.OrderTitleBean;

import java.util.ArrayList;
import java.util.List;

public class OrderTitleAdapter extends RecyclerView.Adapter<OrderTitleAdapter.viewHolder> {

    private Context context;
    private List<OrderTitleBean> list;

    public OrderTitleAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public void setData(List<OrderTitleBean> lists) {
        list = lists;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_order_title, null);
        return new viewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.name.setText(list.get(position).getName());

        if (list.get(position).isSelect()) {
            holder.name.setTextSize(18);
            holder.name.setTextColor(context.getColor(R.color.black_33));
            holder.line.setVisibility(View.VISIBLE);
        } else {
            holder.name.setTextSize(15);
            holder.name.setTextColor(context.getColor(R.color.black_99));
            holder.line.setVisibility(View.GONE);
        }

        holder.name.setOnClickListener(new View.OnClickListener() {
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
        private final TextView name;
        private final View line;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            line = itemView.findViewById(R.id.view_line);
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
