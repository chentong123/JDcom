package com.six.jdcom.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.six.jdcom.R;
import com.six.jdcom.activity.MainActivity;
import com.six.jdcom.mine.bean.UserLogin;
import com.six.jdcom.mine.presenter.PresenterLogin;
import com.six.jdcom.mine.view.IViewLogin;
import com.six.jdcom.utils.SharePresenters;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements IViewLogin {

    @InjectView(R.id.login_img)
    ImageView loginImg;
    @InjectView(R.id.username)
    EditText username;
    @InjectView(R.id.password)
    EditText password;
    @InjectView(R.id.sanfang)
    TextView sanfang;
    @InjectView(R.id.xin)
    TextView xin;
    @InjectView(R.id.di)
    LinearLayout di;
    @InjectView(R.id.login)
    Button login;
    @InjectView(R.id.activity_login)
    RelativeLayout activityLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);

    }
    @OnClick({R.id.login_img, R.id.username, R.id.password, R.id.sanfang, R.id.xin, R.id.di})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_img:

                break;
            case R.id.username:
                break;
            case R.id.password:
                break;
            case R.id.sanfang:
                break;
            case R.id.xin:
                Intent intent2 = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent2);
                break;
            case R.id.di:
                break;
        }
    }

    @OnClick(R.id.login)
    public void onViewClicked() {
        String uname = username.getText().toString().trim();
        String upass = password.getText().toString().trim();
        //String path=url + "?mobile=" + uname + "&password=" + upass;
        if ("".equals(uname) || "".equals(upass)) {
            Toast.makeText(LoginActivity.this, "不能为空哦~", Toast.LENGTH_SHORT).show();
        } else {
            new PresenterLogin(this).setLoginUrl(uname, upass);
                   /* Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);*/
            EventBus.getDefault().postSticky(new UserLogin(uname));

        }
    }

    @Override
    public void success(String code) {
        if("0".equals(code)){
            SharePresenters.put("islogin",true);

           // finish();
           startActivity(new Intent(LoginActivity.this,MainActivity.class));
        }else{
            Toast.makeText(LoginActivity.this, "账号或密码错误~", Toast.LENGTH_SHORT).show();
        }
    }
}
