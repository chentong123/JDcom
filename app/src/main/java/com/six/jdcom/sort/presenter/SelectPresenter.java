package com.six.jdcom.sort.presenter;

import com.six.jdcom.sort.bean.Select;
import com.six.jdcom.sort.model.SelectModel;
import com.six.jdcom.sort.view.ISelectView;

import java.util.List;
import java.util.Map;

/**
 * Created by chentong on 2017/11/17.
 */

public class SelectPresenter implements SelectModel.Setdate{
    ISelectView cartIView;
    SelectModel cartModel;
    public SelectPresenter(ISelectView cartIView) {
        this.cartIView = cartIView;
        this.cartModel = new SelectModel(this);
    }
    public void getdate(String uid){
        cartModel.GetCartDate(uid);
    };
    @Override
    public void getCartdate(Select cartGoods) {
        cartIView.GetCartDate(cartGoods);
    }
}
