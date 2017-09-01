package com.aikaifa.bankcard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aikaifa.bankcard.model.AsyncMessage;
import com.aikaifa.bankcard.model.BackgroundMessage;
import com.aikaifa.bankcard.model.MainMessage;
import com.aikaifa.bankcard.model.PostingMessage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * 有问题请关注微信公众号：aikaifa
 * 有问必答
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnMain, btnBackground, btnAsync, btnPosting, btn1;
    private TextView tv_desc;

    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        initView();
        initClick();

    }

    /**
     * 按钮点击
     */
    private void initClick() {
        btnMain.setOnClickListener(this);
        btnBackground.setOnClickListener(this);
        btnAsync.setOnClickListener(this);
        btnPosting.setOnClickListener(this);
        btn1.setOnClickListener(this);
    }

    /**
     * 初始化View
     */
    private void initView() {

        btnMain = (Button) findViewById(R.id.btnMain);
        btnBackground = (Button) findViewById(R.id.btnBackground);
        btnAsync = (Button) findViewById(R.id.btnAsync);
        btnPosting = (Button) findViewById(R.id.btnPosting);

        btn1 = (Button) findViewById(R.id.btn1);
        tv_desc = (TextView) findViewById(R.id.tv_desc);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnMain:
                EventBus.getDefault().post(new MainMessage("MainMessage"));
                break;
            case R.id.btnBackground:
                EventBus.getDefault().post(new BackgroundMessage("BackgroundMessage"));
                break;
            case R.id.btnAsync:
                EventBus.getDefault().post(new AsyncMessage("AsyncMessage"));
                break;
            case R.id.btnPosting:
                EventBus.getDefault().post(new PostingMessage("PostingMessage"));
                break;
            case R.id.btn1:
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
                break;

        }
    }

    //主线程中执行
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainEventBus(MainMessage msg) {
        Log.e(TAG, msg.getMessage());
        tv_desc.setText(msg.getMessage());
    }

    //后台线程
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onBackgroundEventBus(BackgroundMessage msg) {
        Log.e(TAG, msg.getMessage());
    }

    //异步线程
    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onAsyncEventBus(AsyncMessage msg) {
        Log.e(TAG, msg.getMessage());

    }

    //默认情况，和发送事件在同一个线程
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onPostEventBus(PostingMessage msg) {

        Log.e(TAG, msg.getMessage());
    }
}
