package com.wuweiyangxian.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.UnderlineSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.wuweiyangxian.R;
import com.wuweiyangxian.bean.OrderBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.viewHolder> {

    private Context context;
    private Activity activity;
    private List<OrderBean> list;

    public OrderAdapter(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
        list = new ArrayList<>();
    }

    public void setData(List<OrderBean> lists) {
        list = lists;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_order_goods, null);
        return new viewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (list.get(position).getState() == 0) {
            holder.ll_goods_content.setVisibility(View.GONE);
            holder.tv_food_ok.setVisibility(View.GONE);
            holder.tv_delivery.setVisibility(View.GONE);
            holder.time.setVisibility(View.VISIBLE);
            holder.ll_top_content.setVisibility(View.VISIBLE);
            holder.ll_receiving_orders.setVisibility(View.VISIBLE);
            holder.ll_order_verification.setVisibility(View.GONE);
            holder.ll_delivery.setVisibility(View.GONE);
        } else {
            holder.ll_goods_content.setVisibility(View.VISIBLE);
            holder.ll_receiving_orders.setVisibility(View.GONE);
            holder.ll_order_verification.setVisibility(View.GONE);
            holder.ll_delivery.setVisibility(View.GONE);
            if (list.get(position).getState() == 1) {
                holder.tv_delivery.setVisibility(View.VISIBLE);
                holder.ll_top_content.setVisibility(View.VISIBLE);
                holder.tv_food_ok.setVisibility(View.VISIBLE);
                holder.time.setVisibility(View.GONE);

                SpannableString content = new SpannableString("商家配送");
                UnderlineSpan underlinespan = new UnderlineSpan();
                content.setSpan(underlinespan, 0, content.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                holder.tv_delivery.setText(content);
            } else if (list.get(position).getState() == 2) {
                holder.tv_delivery.setVisibility(View.GONE);
                holder.time.setVisibility(View.GONE);
                holder.tv_food_ok.setVisibility(View.GONE);
            } else if (list.get(position).getState() == 3) {
                holder.ll_top_content.setVisibility(View.GONE);
                holder.tv_food_ok.setVisibility(View.GONE);
                holder.ll_delivery.setVisibility(View.VISIBLE);
            } else {
                holder.ll_top_content.setVisibility(View.GONE);
                holder.tv_food_ok.setVisibility(View.GONE);
                holder.ll_order_verification.setVisibility(View.VISIBLE);
            }
        }
        holder.name.setText(list.get(position).getName());
        holder.title.setText(list.get(position).getTitle());
        if (list.get(position).getState() == 1) {
            holder.tv_goods_des.setText("待骑手取餐");
        } else {
            holder.tv_goods_des.setText(list.get(position).getDes());
        }
        holder.des.setText(list.get(position).getDes());

        holder.time.setText("剩余" + list.get(position).getTime());
        holder.number.setText("共" + list.get(position).getNumber() + "件");
        holder.money.setText("预计收入：¥" + list.get(position).getMoney());

        if (list.get(position).getTitle().equals("外卖")) {
            holder.title.setTextColor(context.getColor(R.color.red_e6));
            holder.title.setBackgroundColor(context.getColor(R.color.red_10_e6));
        } else if (list.get(position).getTitle().equals("自提")) {
            holder.title.setTextColor(context.getColor(R.color.yellow_ff_6b));
            holder.title.setBackgroundColor(context.getColor(R.color.yellow_10_ff_6b));
        }

        holder.more.setOnClickListener(new View.OnClickListener() {
            private PopupWindow popupWindow;

            @Override
            public void onClick(View view) {
                View popupWindow_view = activity.getLayoutInflater().inflate(R.layout.item_order_pop, null, false);
                // 创建PopupWindow实例,200,LayoutParams.MATCH_PARENT分别是宽度和高度
                popupWindow = new PopupWindow(popupWindow_view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                //设置可以获取焦点
                popupWindow.setFocusable(true);
                //设置可以触摸弹出框以外的区域
                popupWindow.setOutsideTouchable(true);
                //放在具体控件下方
                popupWindow.showAsDropDown(holder.more, Gravity.CENTER, Gravity.RIGHT);
                // 这里是位置显示方式,在屏幕的侧
//                popupWindow.showAtLocation(iv_screen, Gravity.BOTTOM, 0, 0);
                // 点击其他地方消失
                //！！重要注意-如果这里写完依旧无效！那么按照下面“常见问题”的第一条重新编写此处代码！！
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

        holder.tv_delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                View alert = activity.getLayoutInflater().inflate(R.layout.alert_order, null, false);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setView(alert);
                AlertDialog dialog = builder.create();
                builder.show();
                Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawableResource(R.color.transparent);
                Window window = dialog.getWindow();
                if (window != null) {
                    window.setBackgroundDrawableResource(android.R.color.white);//设置背景透明
                    WindowManager.LayoutParams lp = window.getAttributes();
                    lp.gravity = Gravity.CENTER;
                    lp.width = 900;//宽高可设置具体大小
                    dialog.getWindow().setAttributes(lp);
                }
            }
        });

        holder.rl_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(list.get(position).getState(), list.get(position).getTitle());
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
        private final TextView title;
        private final TextView des;
        private final TextView time;
        private final TextView number;
        private final TextView money;
        private final TextView tv_delivery;
        private final TextView tv_food_ok;
        private final TextView tv_goods_des;
        private final ImageView more;
        private final LinearLayout ll_goods_content;
        private final LinearLayout ll_top_content;
        private final LinearLayout ll_receiving_orders;
        private final LinearLayout ll_order_verification;
        private final LinearLayout ll_delivery;
        private final RecyclerView rv_content;
        private final RelativeLayout rl_content;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            title = itemView.findViewById(R.id.tv_title);
            tv_food_ok = itemView.findViewById(R.id.tv_food_ok);
            tv_delivery = itemView.findViewById(R.id.tv_delivery);
            tv_goods_des = itemView.findViewById(R.id.tv_goods_des);
            des = itemView.findViewById(R.id.tv_des);
            time = itemView.findViewById(R.id.tv_time);
            number = itemView.findViewById(R.id.tv_num);
            money = itemView.findViewById(R.id.tv_money);
            more = itemView.findViewById(R.id.iv_more);
            ll_goods_content = itemView.findViewById(R.id.ll_goods_content);
            ll_top_content = itemView.findViewById(R.id.ll_top_content);
            ll_receiving_orders = itemView.findViewById(R.id.ll_receiving_orders);
            ll_order_verification = itemView.findViewById(R.id.ll_order_verification);
            ll_delivery = itemView.findViewById(R.id.ll_delivery);
            rv_content = itemView.findViewById(R.id.rv_content);
            rl_content = itemView.findViewById(R.id.rl_content);
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
