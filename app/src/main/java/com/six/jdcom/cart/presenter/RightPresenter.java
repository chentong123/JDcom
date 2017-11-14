package com.six.jdcom.cart.presenter;

import com.six.jdcom.cart.bean.DatarightBean;
import com.six.jdcom.cart.model.RightModel;
import com.six.jdcom.cart.view.IRightView;

import java.util.List;

/**
 * Created by chentong on 2017/11/14.
 */

public class RightPresenter implements RightModel.OnRightFinish{
    private IRightView iRightView;
    private RightModel rightModel;

    public RightPresenter(IRightView iRightView) {
        this.iRightView = iRightView;
        this.rightModel = new RightModel();
        rightModel.setOnFinish(this);
    }
    public void setNewr(String url){
        rightModel.getRight(url);
    }
    @Override
    public void OnFinishListener(List<DatarightBean.DatasBean.ClassListBean> right) {
        iRightView.getRight(right);
    }
}
