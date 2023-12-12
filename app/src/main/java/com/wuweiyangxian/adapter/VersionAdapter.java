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
import com.wuweiyangxian.bean.VersionBean;

import java.util.ArrayList;
import java.util.List;

public class VersionAdapter extends RecyclerView.Adapter<VersionAdapter.viewHolder> {

    private Context context;
    private List<VersionBean> list;

    public VersionAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public void setData(List<VersionBean> lists) {
        list = lists;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_order_detail_food, null);
        return new viewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tv_version.setText(list.get(position).getVersion());
        holder.tv_time.setText(list.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_version;
        private final TextView tv_time;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            tv_version = itemView.findViewById(R.id.tv_version);
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
