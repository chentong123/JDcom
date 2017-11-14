package com.six.jdcom.mine.model;

import com.six.jdcom.mine.bean.Login;
import com.six.jdcom.mine.presenter.PresenterRegister;
import com.six.jdcom.utils.RetorFactoryLogin;

import java.util.HashMap;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by chentong on 2017/11/13.
 */

public class ModelRegister implements IModelRegister{
    private String code;
    private OnRegisterFinish onRegisterFinish;



    public interface OnRegisterFinish{
        void RegisterFinish(String code);
    }

    public void setOnRegisterFinish(OnRegisterFinish onRegisterFinish) {
        this.onRegisterFinish = onRegisterFinish;
    }
    @Override
    public void getRegisterUrl(String uname1, String upass1) {
        HashMap<String,String> map=new HashMap<>();
        map.put("mobile",uname1);
        map.put("password",upass1);
        RetorFactoryLogin.getInstance().getRegister("user/reg",map)
        .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Login>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Login login) {
                        code=login.getCode();
                        onRegisterFinish.RegisterFinish(code);
                    }
                });
    }


}
