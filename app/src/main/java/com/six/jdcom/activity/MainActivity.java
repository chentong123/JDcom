package com.six.jdcom.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hjm.bottomtabbar.BottomTabBar;
import com.six.jdcom.R;
import com.six.jdcom.cart.FragmentTwo;
import com.six.jdcom.home.FragmentOne;
import com.six.jdcom.mine.FragmentFour;
import com.six.jdcom.mine.LoginActivity;
import com.six.jdcom.mine.bean.Login;
import com.six.jdcom.sort.FragmentThree;
import com.six.jdcom.utils.SharePresenters;

public class MainActivity extends AppCompatActivity {

    private Fragment[] mFragments;
    private BottomTabBar bottomTabBar;
    boolean islogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        islogin =(boolean) SharePresenters.get("islogin", false);
    }
    private void init()
    {
        bottomTabBar = (BottomTabBar) findViewById(R.id.bottomTabBar);
//底部导航栏
        bottomTabBar.init(getSupportFragmentManager())
                .setImgSize(35, 35)
                .setFontSize(10)
                .setChangeColor(Color.RED, Color.GRAY)
                .addTabItem("首页", R.mipmap.ic_nav_home_press, R.mipmap.ic_nav_home, FragmentOne.class) //前面第一个是点击后的图片，第二个是点击前的图片
                .addTabItem("分类", R.mipmap.ic_nav_class_press, R.mipmap.ic_nav_class, FragmentTwo.class)
                .addTabItem("购物车", R.mipmap.ic_nav_cart_press, R.mipmap.ic_nav_cart, FragmentThree.class)
                .addTabItem("个人", R.mipmap.ic_nav_user_press, R.mipmap.ic_nav_user, FragmentFour.class)
                .isShowDivider(false);
                bottomTabBar.setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                     @Override
                     public void onTabChange(int position, String name) {
                         if (position==2||position==3){
                    if (!islogin){
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });

    }
}
