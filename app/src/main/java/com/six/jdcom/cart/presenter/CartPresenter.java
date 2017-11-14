package com.six.jdcom.cart.presenter;

import com.six.jdcom.cart.bean.DataleftBean;
import com.six.jdcom.cart.model.CartModel;
import com.six.jdcom.cart.view.ICartView;

import java.util.List;

/**
 * Created by chentong on 2017/11/14.
 */

public class CartPresenter implements CartModel.OnLeftFinish{
    private final ICartView cartView;
    private final CartModel cartModel;

    public CartPresenter(ICartView cartView) {
        this.cartView = cartView;
        this.cartModel = new CartModel();
        cartModel.setOnFinish(this);
    }
    public void setNew(String url)
    {
        cartModel.getJson(url);
    }
    @Override
    public void OnFinishListener(List<DataleftBean.DatasBean.ClassListBean> list) {
        cartView.getCart(list);
    }
}
