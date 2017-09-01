package com.aikaifa.bankcard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.aikaifa.bankcard.model.MainMessage;

import org.greenrobot.eventbus.EventBus;

/**
 * 有问题请关注微信公众号：aikaifa
 * 有问必答
 */
public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
  private  Button btnMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        btnMain = (Button) findViewById(R.id.btnMain);
        btnMain.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnMain:
                EventBus.getDefault().post(new MainMessage("传递信息：aikaifa"));
                finish();
                break;
        }
    }

}
