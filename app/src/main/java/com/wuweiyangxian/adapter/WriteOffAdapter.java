package com.wuweiyangxian.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wuweiyangxian.R;
import com.wuweiyangxian.bean.OrderBean;

import java.util.ArrayList;
import java.util.List;

public class WriteOffAdapter extends RecyclerView.Adapter<WriteOffAdapter.viewHolder> {

    private Context context;
    private Activity activity;

    private List<String> list;

    public WriteOffAdapter(Context context,Activity activity) {
        this.context = context;
        list = new ArrayList<>();
    }

    public void setData(List<String> lists) {
        list = lists;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_write_off , null);
        return new WriteOffAdapter.viewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        holder.more.setOnClickListener(new View.OnClickListener() {
            private PopupWindow popupWindow;

            @Override
            public void onClick(View view) {
                View popupWindow_view = activity.getLayoutInflater().inflate(R.layout.item_order_pop, null, false);
                popupWindow = new PopupWindow(popupWindow_view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                popupWindow.setFocusable(true);
                popupWindow.setOutsideTouchable(true);
                popupWindow.showAsDropDown(holder.more, Gravity.CENTER, Gravity.RIGHT);
                popupWindow_view.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (popupWindow != null && popupWindow.isShowing()) {
                            popupWindow.dismiss();
                            popupWindow = null;
                        }
                        return false;
                    }
                });


            }
        });

        holder.ll_write_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null){
                    listener.onItemClick(5,"自提");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private final ImageView more;
        private final LinearLayout ll_write_off;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            more = itemView.findViewById(R.id.iv_more);
            ll_write_off = itemView.findViewById(R.id.ll_write_off);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int state, String title);
    }

    private OrderAdapter.OnItemClickListener listener;

    public void setOnItemClickListener(OrderAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }
}
