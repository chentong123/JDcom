package com.six.jdcom.mine;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.six.jdcom.R;
import com.six.jdcom.activity.SecondActivity;
import com.six.jdcom.home.activity.OrderActivity;
import com.six.jdcom.home.bean.User;
import com.six.jdcom.mine.bean.UserLogin;
import com.six.jdcom.sort.presenter.SelectPresenter;
import com.six.jdcom.utils.SharePresenters;

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
    private String uname;
    private Button tui;
    private TextView weifukuan;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_four, null);
        ButterKnife.inject(this, view);
        if (!EventBus.getDefault().isRegistered(this))
        {
            EventBus.getDefault().register(this);//注册事件
        }
        weifukuan=(TextView) view.findViewById(R.id.weifukuan);
        weifukuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), OrderActivity.class);
                startActivity(intent);
            }
        });
        tui=(Button) view.findViewById(R.id.tui);
        tui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                //设置对话框图标，可以使用自己的图片，Android本身也提供了一些图标供我们使用
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                //设置对话框标题
                builder.setTitle("提示框?");
                //设置对话框内的文本
                builder.setMessage("您确定退出登录吗！");
                //设置确定按钮，并给按钮设置一个点击侦听，注意这个OnClickListener使用的是DialogInterface类里的一个内部接口
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 执行点击确定按钮的业务逻辑
                    //    wei.setText("未登录");
                        SharePresenters.clear(getContext());
                    }
                });
                //设置取消按钮
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 执行点击取消按钮的业务逻辑
                    }
                });
                //使用builder创建出对话框对象
                AlertDialog dialog = builder.create();
                //显示对话框
                dialog.show();
            }
        });
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//取消注册
    }

    @Subscribe(threadMode = ThreadMode.POSTING,sticky = true)
    public void getData(UserLogin user){

        uname = user.getUname();
        wei.setText(uname);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick(R.id.wei)
    public void onViewClicked() {
        String state= wei.getText().toString().trim();
        Log.d("state++++++", "onViewClicked: "+state);
        if (state==uname){
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            //设置对话框图标，可以使用自己的图片，Android本身也提供了一些图标供我们使用
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            //设置对话框标题
            builder.setTitle("提示框?");
            //设置对话框内的文本
            builder.setMessage("您确定退出登录吗！");
            //设置确定按钮，并给按钮设置一个点击侦听，注意这个OnClickListener使用的是DialogInterface类里的一个内部接口
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // 执行点击确定按钮的业务逻辑
                    wei.setText("未登录");
                    SharePresenters.clear(getContext());
                }
            });
            //设置取消按钮
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // 执行点击取消按钮的业务逻辑
                }
            });
            //使用builder创建出对话框对象
            AlertDialog dialog = builder.create();
            //显示对话框
            dialog.show();

        }else {
            Intent intent = new Intent(getActivity(), LoginActivity.class);

            startActivity(intent);
        }


    }

}
