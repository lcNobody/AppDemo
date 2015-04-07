package dd.surfit.test.applicationdemo.Welcome;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import dd.surfit.test.applicationdemo.R;

/**
 * Created by Administrator on 2015/4/1.
 */
public class Welcome_1 extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_1);
        //延迟两秒后执行run方法中的页面跳转
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent intent = new Intent(Welcome_1.this, Welcome_2.class);
                startActivity(intent);
                Welcome_1.this.finish();
            }
        }, 2000);
    }
}
