package com.wuweiyangxian.fragment;

import static com.blankj.utilcode.util.ColorUtils.getColor;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wuweiyangxian.R;
import com.wuweiyangxian.activity.WebActivity;
import com.wuweiyangxian.adapter.MessageAdapter;
import com.wuweiyangxian.bean.MessageBean;
import com.wuweiyangxian.util.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MessageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MessageFragment extends Fragment {

    private RelativeLayout rl_top;
    private ImageView iv_back;
    private TextView tv_title;
    private RecyclerView rv_message;
    private List<MessageBean> list;

    public MessageFragment() {
        // Required empty public constructor
    }

    public static MessageFragment newInstance() {
        MessageFragment fragment = new MessageFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_message, container, false);
        rl_top = inflate.findViewById(R.id.rl_top);
        rl_top.setBackgroundColor(getContext().getColor(R.color.white));
        View view = inflate.findViewById(R.id.view);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = layoutParams.height + StatusBarUtil.getStatusBarHeight(getContext());
        view.setLayoutParams(layoutParams);
        iv_back = inflate.findViewById(R.id.iv_back);
        tv_title = inflate.findViewById(R.id.tv_title);
        tv_title.setText("消息");
        iv_back.setVisibility(View.GONE);
        tv_title.setTextColor(getColor(R.color.black));

        initList();
        rv_message = inflate.findViewById(R.id.rv_message);
        MessageAdapter adapter = new MessageAdapter(getContext());
        rv_message.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        rv_message.setAdapter(adapter);
        adapter.setData(list);

        adapter.setOnItemClickListener(new MessageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startActivity(new Intent(getActivity(), WebActivity.class).putExtra("showTitle",true).putExtra("url","https://kf.ruanzhongzi.com/appKefu/#/pages/public/chat").putExtra("name",list.get(position).getName()));
            }
        });
        return inflate;
    }

    private void initList() {
        list = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            MessageBean bean = new MessageBean();
            bean.setName("午未羊鲜官方客服");
            bean.setImg(getResources().getDrawable(R.mipmap.icon_message_ai_service));
            list.add(bean);
        }
    }
}