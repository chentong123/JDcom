package com.six.jdcom.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.six.jdcom.R;
import com.six.jdcom.activity.SecondActivity;
import com.six.jdcom.home.bean.User;
import com.six.jdcom.mine.bean.UserLogin;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by chentong on 2017/11/8.
 */

public class FragmentFour extends Fragment {
    @InjectView(R.id.yong)
    ImageView yong;
    @InjectView(R.id.wei)
    TextView wei;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_four, null);
        ButterKnife.inject(this, view);
        if (!EventBus.getDefault().isRegistered(this))
        {
            EventBus.getDefault().register(this);//注册事件
        }
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//取消注册
    }

    @Subscribe(threadMode = ThreadMode.POSTING,sticky = true)
    public void getData(UserLogin user){

        String uname = user.getUname();
        wei.setText(uname);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick(R.id.yong)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }
}
