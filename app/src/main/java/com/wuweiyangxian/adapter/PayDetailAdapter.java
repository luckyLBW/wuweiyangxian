package com.wuweiyangxian.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wuweiyangxian.R;
import com.wuweiyangxian.bean.PayDetailBean;

import java.util.ArrayList;
import java.util.List;

public class PayDetailAdapter extends RecyclerView.Adapter<PayDetailAdapter.viewHolder> {

    private Context context;
    private Activity activity;
    private List<PayDetailBean> list;

    public PayDetailAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public void setData(List<PayDetailBean> lists) {
        list = lists;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_pay_detail, null);
        return new viewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, @SuppressLint("RecyclerView") int position) {

        if (position == list.size() - 1) {
            holder.view_line.setVisibility(View.VISIBLE);
        } else {
            holder.view_line.setVisibility(View.GONE);
        }
        holder.tv_name.setText(list.get(position).getTime());
        PayChildAdapter adapter = new PayChildAdapter(context);
        holder.rv_child.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
        holder.rv_child.setAdapter(adapter);
        adapter.setData(list.get(position).getList());
        adapter.setOnItemClickListener(new PayChildAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
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
        private final View view_line;
        private final RecyclerView rv_child;
        private final TextView tv_name;
        private final TextView tv_expenditure;
        private final TextView tv_income;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            view_line = itemView.findViewById(R.id.view_line);
            rv_child = itemView.findViewById(R.id.rv_child);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_expenditure = itemView.findViewById(R.id.tv_expenditure);
            tv_income = itemView.findViewById(R.id.tv_income);
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
