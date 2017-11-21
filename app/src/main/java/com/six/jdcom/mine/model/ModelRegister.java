package com.six.jdcom.mine.model;

import android.util.Log;

import com.six.jdcom.mine.bean.Login;
import com.six.jdcom.mine.bean.Register;
import com.six.jdcom.mine.presenter.PresenterRegister;
import com.six.jdcom.utils.RetorFactoryLogin;
import com.six.jdcom.utils.SharePresenters;

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
        Log.d("11111",map.toString());
        RetorFactoryLogin.getInstance().getRegister("user/reg",map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Register>() {
            @Override
            public void onCompleted() {
            }
            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }
                    @Override
                    public void onNext(Register login) {
                        code=login.getCode();
                        Log.d("1111111111",login.getMsg());
                        onRegisterFinish.RegisterFinish(code);
                    }
                });
    }


}
