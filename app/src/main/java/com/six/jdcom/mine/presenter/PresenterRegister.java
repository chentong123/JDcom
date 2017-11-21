package com.six.jdcom.mine.presenter;

import com.six.jdcom.mine.model.ModelRegister;
import com.six.jdcom.mine.view.IViewRegister;

/**
 * Created by chentong on 2017/11/13.
 */

public class PresenterRegister implements ModelRegister.OnRegisterFinish{
   private final IViewRegister iViewRegister;
    private final ModelRegister modelRegister;

    public PresenterRegister(IViewRegister iViewRegister) {
        this.iViewRegister = iViewRegister;
        modelRegister = new ModelRegister();
        modelRegister.setOnRegisterFinish(this);
    }
    public void setRegisterUrl(String uname1, String upass1)
    {
        modelRegister.getRegisterUrl(uname1,upass1);
    }
    @Override
    public void RegisterFinish(String code) {
            iViewRegister.successRegister(code);
    }
}
