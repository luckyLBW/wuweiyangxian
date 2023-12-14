package com.wuweiyangxian.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.wuweiyangxian.R;
import com.wuweiyangxian.activity.FeedbackActivity;
import com.wuweiyangxian.activity.RuleActivity;
import com.wuweiyangxian.activity.SettingActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MineFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MineFragment extends Fragment implements View.OnClickListener {

    private TextView rule;
    private TextView tv_feedback;
    private TextView tv_setting;

    public MineFragment() {
        // Required empty public constructor
    }

    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_mine, container, false);
        rule = inflate.findViewById(R.id.tv_rule);
        tv_feedback = inflate.findViewById(R.id.tv_feedback);
        tv_setting = inflate.findViewById(R.id.tv_setting);

        rule.setOnClickListener(this);
        tv_feedback.setOnClickListener(this);
        tv_setting.setOnClickListener(this);
        return inflate;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_rule:
                startActivity(new Intent(getActivity(), RuleActivity.class));
                break;
            case R.id.tv_feedback:
                startActivity(new Intent(getActivity(), FeedbackActivity.class));
                break;
            case R.id.tv_setting:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
        }
    }
}