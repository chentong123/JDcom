package com.six.jdcom.home.presenter;


import com.six.jdcom.home.bean.KindBean;
import com.six.jdcom.home.bean.RightBean;
import com.six.jdcom.home.model.Kmodel;
import com.six.jdcom.home.view.Kview;

import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/11/14.
 */

public class Kpresent implements Kmodel.onFinshkind,Kmodel.onRightkind {
    private final Kmodel kmodel;
    private final Kview kview;

    public Kpresent( Kview kview) {
        this.kmodel = new Kmodel();
        this.kview = kview;
    }
    public void getpleft(String str){
        kmodel.Setkind(this);
        kmodel.getKind(str);
    }
    public void getpright(Map<String,String> map,String str){
        kmodel.SetRightkind(this);
        kmodel.getKindrig(map,str);
    }

    @Override
    public void Finshkind(List<KindBean.DataBean> data) {
            kview.getleft(data);
    }

    @Override
    public void Rigthkind(List<RightBean.DataBean> data) {
        kview.getright(data);
    }


    // kview.getleft(data);



       // kview.getright(data);

}
