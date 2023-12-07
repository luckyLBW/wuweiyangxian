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
import com.wuweiyangxian.bean.StoreWaringBean;

import java.util.ArrayList;
import java.util.List;

public class StoreWarningAdapter extends RecyclerView.Adapter<StoreWarningAdapter.viewHolder> {

    private Context context;
    private List<StoreWaringBean> list;

    public StoreWarningAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public void setData(List<StoreWaringBean> lists) {
        list = lists;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_store_warning, null);
        return new viewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.name.setText(list.get(position).getName());
        holder.time.setText(list.get(position).getTime());
        holder.des.setText(list.get(position).getDes());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView des;
        private final TextView time;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            des = itemView.findViewById(R.id.tv_des);
            time = itemView.findViewById(R.id.tv_time);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int state, String title);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
