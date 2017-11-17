package com.six.jdcom.home.view;


import com.six.jdcom.home.bean.KindBean;
import com.six.jdcom.home.bean.RightBean;

import java.util.List;

/**
 * Created by admin on 2017/11/14.
 */

public interface Kview {
    void getleft(List<KindBean.DataBean> data);
    void getright(List<RightBean.DataBean> data);
}
