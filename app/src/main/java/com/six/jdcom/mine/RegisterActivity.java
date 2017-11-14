package com.six.jdcom.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.six.jdcom.R;
import com.six.jdcom.mine.presenter.PresenterLogin;
import com.six.jdcom.mine.presenter.PresenterRegister;
import com.six.jdcom.mine.view.IViewRegister;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity implements IViewRegister{

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

    @OnClick({R.id.zc_user, R.id.zc_pass, R.id.queren, R.id.email, R.id.zhuce, R.id.activity_zhuce})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zc_user:
                break;
            case R.id.zc_pass:
                break;
            case R.id.queren:
                break;
            case R.id.email:
                break;
            case R.id.zhuce:
                String uname1 = zcUser.getText().toString().trim();
                String upass1 =zcPass.getText().toString().trim();
                String queren1=queren.getText().toString().trim();
                String email1=email.getText().toString().trim();
                if (uname1!=null&&upass1!=null&&queren1!=null&&email1!=null)
                {
                    new PresenterRegister(this).setRegisterUrl(uname1, upass1);
                }else{
                    Toast.makeText(RegisterActivity.this, "不能为空哦~", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.activity_zhuce:
                break;
        }
    }

    @Override
    public void successRegister(String code) {
        if ("0".equals(code))
        {
            Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
            startActivity(intent);
          //  Toast.makeText(RegisterActivity.this,s,Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(RegisterActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
        }
    }
}
