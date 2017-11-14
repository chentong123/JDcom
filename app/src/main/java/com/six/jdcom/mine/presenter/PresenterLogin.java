package com.six.jdcom.mine.presenter;


import com.six.jdcom.mine.model.ModelLogin;
import com.six.jdcom.mine.view.IViewLogin;

/**
 * Created by Dabin on 2017/11/10.
 */

public class PresenterLogin implements ModelLogin.OnLoginFinish{

    private final IViewLogin iViewLogin;
    private final ModelLogin modelLogin;

    public PresenterLogin(IViewLogin iViewLogin) {
        this.iViewLogin = iViewLogin;
        modelLogin = new ModelLogin();
        modelLogin.setOnLoginFinish(this);
    }

    public  void setLoginUrl(String uname,String upass){
        modelLogin.getLoginUrl(uname,upass);
    }


    @Override
    public void LoginFinish(String code) {
        iViewLogin.success(code);
    }
}
