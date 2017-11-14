package com.six.jdcom.home.presenter;

import com.six.jdcom.home.bean.HomeBean;
import com.six.jdcom.home.model.UserMode;
import com.six.jdcom.home.view.IUserView;

import java.util.ArrayList;

/**
 * Created by chentong on 2017/11/8.
 */

public class UserPresenter implements UserMode.OnFinish{

    private final IUserView userView;
    private final UserMode userModel;

    public UserPresenter(IUserView userView) {
        this.userView = userView;
        this.userModel = new UserMode();
        userModel.setOnFinish(this);
    }

    public void setNews(String url){
        userModel.getJson(url);
    }

    @Override
    public void OnFinishListener(HomeBean.DataBean list) {
        userView.getUser(list);
    }
}
