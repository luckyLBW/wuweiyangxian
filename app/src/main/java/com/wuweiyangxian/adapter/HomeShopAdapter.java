package com.wuweiyangxian.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wuweiyangxian.R;
import com.wuweiyangxian.bean.HomeShopBean;

import java.util.ArrayList;
import java.util.List;

public class HomeShopAdapter extends RecyclerView.Adapter<HomeShopAdapter.viewHolder> {

    private Context context;
    private List<HomeShopBean> list;

    public HomeShopAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public void setData(List<HomeShopBean> lists) {
        list = lists;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_home_shop_adapter, null);
        return new viewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.time.setText(list.get(position).getTime());
        holder.number.setText(list.get(position).getNumber());
        holder.name.setText(list.get(position).getName());

        if (list.get(position).getTitle().equals("库存")) {
            holder.title.setBackgroundColor(context.getColor(R.color.blue_20_2e));
            holder.title.setTextColor(context.getColor(R.color.blue_02));
            holder.des.setText("剩余数量已不足");
            holder.unit.setText("份");
        } else if (list.get(position).getTitle().equals("超时")) {
            holder.title.setBackgroundColor(context.getColor(R.color.yellow_20_ff_69));
            holder.title.setTextColor(context.getColor(R.color.yellow_ff_69));
            holder.des.setText("剩余时间已不足 ");
            holder.unit.setText("分钟");
        } else if (list.get(position).getTitle().equals("食材")) {
            holder.title.setBackgroundColor(context.getColor(R.color.green_20_14));
            holder.title.setTextColor(context.getColor(R.color.green_00_a8));
            holder.des.setText("剩余时间已不足 ");
            holder.unit.setText("份");
        } else if (list.get(position).getTitle().equals("监控")) {
            holder.title.setBackgroundColor(context.getColor(R.color.yellow_20_97));
            holder.title.setTextColor(context.getColor(R.color.yellow_97));
            holder.des.setText("监控画面已处于");
            holder.unit.setText("状态");
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView time;
        private final TextView unit;
        private final TextView number;
        private final TextView des;
        private final TextView title;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            time = itemView.findViewById(R.id.tv_time);
            unit = itemView.findViewById(R.id.tv_unit);
            number = itemView.findViewById(R.id.tv_number);
            des = itemView.findViewById(R.id.tv_des);
            title = itemView.findViewById(R.id.tv_title);
        }
    }

}
