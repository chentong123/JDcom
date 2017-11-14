package com.six.jdcom.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.six.jdcom.R;

public class DianJi2Activity extends AppCompatActivity {
    private TextView web1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dian_ji2);
        web1= (TextView) findViewById(R.id.web1);
        Intent intent = getIntent();
        String b = intent.getStringExtra("b");
        web1.setText(b);
    }
}
