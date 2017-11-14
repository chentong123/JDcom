package com.six.jdcom.cart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.six.jdcom.R;
import com.six.jdcom.cart.adpater.MyAdapter_left;
import com.six.jdcom.cart.adpater.MyAdapter_right;
import com.six.jdcom.cart.bean.DataleftBean;
import com.six.jdcom.cart.bean.DatarightBean;
import com.six.jdcom.cart.presenter.CartPresenter;
import com.six.jdcom.cart.presenter.RightPresenter;
import com.six.jdcom.cart.view.ICartView;
import com.six.jdcom.cart.view.IRightView;
import com.six.jdcom.utils.Api;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by chentong on 2017/11/8.
 */

public class FragmentTwo extends Fragment implements ICartView,IRightView{


    @InjectView(R.id.type_rvleft)
    RecyclerView typeRvleft;
    @InjectView(R.id.type_rvright)
    RecyclerView typeRvright;
    CartPresenter cartPresenter;
    MyAdapter_left myAdapter_left;
    RightPresenter rightPresenter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, null);

        ButterKnife.inject(this, view);
         cartPresenter = new CartPresenter(this);
        cartPresenter.setNew(Api.TYPE_PATH);
         rightPresenter = new RightPresenter(this);
        rightPresenter.setNewr(Api.TYPE_PATH);
        //得到RecyclerView布局管理器

        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


    @Override
    public void getCart(List<DataleftBean.DatasBean.ClassListBean> list) {
        myAdapter_left= new MyAdapter_left(getActivity(), list);
        typeRvleft.setAdapter(myAdapter_left);
        typeRvleft.setLayoutManager(new LinearLayoutManager(getActivity()));
        //子条目点击监听
        myAdapter_left.setRecycleViewItemClickListener(new MyAdapter_left.OnRecycleViewItemClickListener() {
            @Override
            public void recycleViewItemClickListener(int position, View view, RecyclerView.ViewHolder viewHolder) {
                myAdapter_left.setTagPosition(position);
                myAdapter_left.notifyDataSetChanged();

            }
        });
    }

    @Override
    public void getRight(List<DatarightBean.DatasBean.ClassListBean> right) {
        MyAdapter_right myAdapter_right = new MyAdapter_right(getActivity(),right);
        typeRvright.setAdapter(myAdapter_right);
        typeRvright.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
