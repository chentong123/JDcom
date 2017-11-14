package com.six.jdcom.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.six.jdcom.R;
import com.six.jdcom.activity.CaptureActivity;
import com.six.jdcom.activity.SearchActivity;
import com.six.jdcom.adpater.XRAdapter;
import com.six.jdcom.home.bean.HomeBean;
import com.six.jdcom.home.presenter.UserPresenter;
import com.six.jdcom.home.view.IUserView;
import com.six.jdcom.utils.Api;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by chentong on 2017/11/8.
 */

public class FragmentOne extends Fragment implements IUserView{
    UserPresenter userPresenter;
    @InjectView(R.id.erweima)
    ImageView erweima;
    @InjectView(R.id.search)
    EditText search;
    @InjectView(R.id.re)
    RelativeLayout re;
    @InjectView(R.id.xre_xrv)
    XRecyclerView xreXrv;
  //  private ArrayList<HomeBean.DataBean> list;
    private int curr;
    private List<String> list=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, null);

        userPresenter = new UserPresenter(this);
        userPresenter.setNews(Api.PRO_URL);

        ButterKnife.inject(this, view);
        //加布局管理器
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xreXrv.setLayoutManager(layoutManager);
        xreXrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {

            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.erweima, R.id.search, R.id.xre_xrv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.erweima:
                startActivityForResult(new Intent(getActivity(), CaptureActivity.class),0);
                break;
            case R.id.search:
                Intent intent=new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.xre_xrv:
                break;
        }
    }
    @Override
    public void getUser(HomeBean.DataBean list) {
        XRAdapter  mxradapter=new XRAdapter(getActivity(), list);
        xreXrv.setAdapter(mxradapter);
    }
}
