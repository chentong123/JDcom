package com.six.jdcom.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.six.jdcom.R;
import com.six.jdcom.home.adapter.LeftAdapter;
import com.six.jdcom.home.adapter.RightAdapter;
import com.six.jdcom.home.bean.KindBean;
import com.six.jdcom.home.bean.RightBean;
import com.six.jdcom.home.presenter.Kpresent;
import com.six.jdcom.home.view.Kview;
import com.six.jdcom.utils.Api;

import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ShopActivity extends AppCompatActivity implements Kview {


    @InjectView(R.id.left)
    RecyclerView left;
    @InjectView(R.id.right)
    RecyclerView right;
    private Kpresent kpresent;
    private LeftAdapter leftAdapter;
    private RightAdapter rightadapter;
    private int count = 1;
    private HashMap<String, String> rMap,rMap1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        ButterKnife.inject(this);
        kpresent=new Kpresent(this);
        kpresent.getpleft("");
        rMap = new HashMap<>();
//        rMap.put("cid","1");
//        kpresent.getpright(rMap,"product/getProductCatagory");
        rMap1 = new HashMap<>();
        rMap1.put("cid","1");
        kpresent.getpright(rMap1,"product/getProductCatagory");

    }



    @Override
    public void getleft(final List<KindBean.DataBean> data) {
        Toast.makeText(ShopActivity.this,data.get(0).getName(), Toast.LENGTH_SHORT).show();
        leftAdapter=new LeftAdapter(data,ShopActivity.this);
        left.setLayoutManager(new LinearLayoutManager(ShopActivity.this));
        left.setAdapter(leftAdapter);
        leftAdapter.setOnItemClicks(new LeftAdapter.OnItemClicks() {
            @Override
            public void itemclick(int position, View view) {

                rMap.clear();
                int cid = data.get(position).getCid();
                rMap.put("cid",cid+"");
                kpresent.getpright(rMap,"product/getProductCatagory");
              //  Toast.makeText(getActivity(),cid+"",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void getright(List<RightBean.DataBean> data) {
        rightadapter=new RightAdapter(data,ShopActivity.this);
        right.setLayoutManager(new LinearLayoutManager(ShopActivity.this));
        right.setAdapter(rightadapter);
    }
}
