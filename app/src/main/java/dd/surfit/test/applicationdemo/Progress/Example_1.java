package dd.surfit.test.applicationdemo.Progress;

import java.util.Random;
import java.util.logging.LogRecord;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import dd.surfit.test.applicationdemo.R;

/**
 * Created by Administrator on 2015/4/1.
 */
public class Example_1 extends Activity {
    int progress = 0;
    private TextView textView;
    private SpringProgressView progressView;
    private Random random = new Random(System.currentTimeMillis());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_example_1);
        textView = (TextView) findViewById(R.id.textview);
        progressView = (SpringProgressView) findViewById(R.id.spring_progress_view);
        progressView.setMaxCount(1000.0f);
        findViewById(R.id.click).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        while(progress <= 1000){
                            // 需要做的事:发送消息
                            Message message = new Message();
                            message.what = 1;
                            handler.sendMessage(message);
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }).start();
            }
        });

    }
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                if(progress <= 1000) {
                    progress += 10;
                    progressView.setCurrentCount(progress);
                }
            }
            super.handleMessage(msg);
        };

    };
}

