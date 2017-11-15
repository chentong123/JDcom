package com.six.jdcom.cart.presenter;

import com.six.jdcom.cart.bean.DateGridBean;
import com.six.jdcom.cart.bean.Datebeanitem;
import com.six.jdcom.cart.model.ThreeModel;
import com.six.jdcom.cart.view.ThreeView;

import java.util.List;

/**
 * Created by chentong on 2017/11/14.
 */

public class ThreePresenter implements ThreeModel.OnThreeFinish{
    private final ThreeView threeView;
    private final ThreeModel threeModel;

    public ThreePresenter(ThreeView threeView) {
        this.threeView = threeView;
        this.threeModel = new ThreeModel();
        threeModel.setOnFinish(this);
    }
    public void setNewr(String url)
    {
        threeModel.getThreeJson(url);
    }
    @Override
    public void OnFinishListener(List<DateGridBean.DatasBean.ClassListBean> list) {
        threeView.getThree(list);
    }
}
