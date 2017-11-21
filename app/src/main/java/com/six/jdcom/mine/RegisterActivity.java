package com.six.jdcom.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.six.jdcom.R;
import com.six.jdcom.mine.presenter.PresenterRegister;
import com.six.jdcom.mine.view.IViewRegister;

import org.w3c.dom.Text;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity implements IViewRegister {

    @InjectView(R.id.zc_user)
    EditText zcUser;
    @InjectView(R.id.zc_pass)
    EditText zcPass;
    @InjectView(R.id.queren)
    EditText queren;
    @InjectView(R.id.email)
    EditText email;
    @InjectView(R.id.zhuce)
    Button zhuce;
    @InjectView(R.id.activity_zhuce)
    RelativeLayout activityZhuce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.inject(this);
    }
    @Override
    public void successRegister(String code) {
        if ("0".equals(code)) {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
          //  finish();
            //  Toast.makeText(RegisterActivity.this,s,Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
        }
    }
    @OnClick(R.id.zhuce)
    public void onViewClicked() {
        String uname1 = zcUser.getText().toString().trim();
        String upass1 = zcPass.getText().toString().trim();
        if (TextUtils.isEmpty(uname1) || TextUtils.isEmpty(upass1)) {
            Toast.makeText(RegisterActivity.this, "不能为空哦~", Toast.LENGTH_SHORT).show();
        } else {
            PresenterRegister presenterRegister= new PresenterRegister(this);
            presenterRegister.setRegisterUrl(uname1, upass1);
        }
    }
}
