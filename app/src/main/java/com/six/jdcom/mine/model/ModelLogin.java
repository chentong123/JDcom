package com.six.jdcom.mine.model;


import com.six.jdcom.mine.bean.Login;
import com.six.jdcom.utils.RetorFactoryLogin;
import com.six.jdcom.utils.RetroFactory;
import com.six.jdcom.utils.SharePresenters;

import java.util.HashMap;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Dabin on 2017/11/10.
 */

public class ModelLogin implements IModelLogin{

    private String code;
    private OnLoginFinish onLoginFinish;
    public interface OnLoginFinish{
        void LoginFinish(String code);
    }
    public void setOnLoginFinish(OnLoginFinish onLoginFinish){
        this.onLoginFinish = onLoginFinish;

    }


    @Override
    public void getLoginUrl(String uname,String upass) {
        HashMap<String,String> map = new HashMap<>();
        map.put("mobile",uname);
        map.put("password",upass);

        RetorFactoryLogin.getInstance().getLogin("user/login",map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Login>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(Login loginBase) {
                SharePresenters.put("isLogin",true);
                code = loginBase.getCode();
                if (code=="0")
                {

                    SharePresenters.put("yonghu",loginBase.getData().getUid());
                }
                onLoginFinish.LoginFinish(code);
            }
        });
    }
}
