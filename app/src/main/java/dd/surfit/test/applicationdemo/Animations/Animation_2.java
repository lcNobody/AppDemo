package dd.surfit.test.applicationdemo.Animations;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import dd.surfit.test.applicationdemo.BaseActivity;
import dd.surfit.test.applicationdemo.R;

/**
 * Created by Administrator on 2015/4/1.
 * xml中设置动画
 */
public class Animation_2 extends BaseActivity {
    private Button rotateButton = null;
    private Button scaleButton = null;
    private Button alphaButton = null;
    private Button translateButton = null;
    private Button doubleButton = null;
    private ImageView image = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_1);

        rotateButton = (Button) findViewById(R.id.rotateButton);
        scaleButton = (Button) findViewById(R.id.scaleButton);
        alphaButton = (Button) findViewById(R.id.alphaButton);
        translateButton = (Button) findViewById(R.id.translateButton);
        doubleButton = (Button) findViewById(R.id.doubleButton);
        image = (ImageView) findViewById(R.id.image);

        rotateButton.setOnClickListener(new RotateButtonListener());
        scaleButton.setOnClickListener(new ScaleButtonListener());
        alphaButton.setOnClickListener(new AlphaButtonListener());
        translateButton.setOnClickListener(new TranslateButtonListener());
        doubleButton.setOnClickListener(new DoubleButtonListener());
    }

    class AlphaButtonListener implements OnClickListener {
        public void onClick(View v) {
            // 使用AnimationUtils装载动画配置文件
            Animation animation = AnimationUtils.loadAnimation(
                    Animation_2.this, R.anim.alpha);
            // 启动动画
            image.startAnimation(animation);
        }
    }

    class RotateButtonListener implements OnClickListener {
        public void onClick(View v) {
            Animation animation = AnimationUtils.loadAnimation(
                    Animation_2.this, R.anim.rotate);
            image.startAnimation(animation);
        }
    }

    class ScaleButtonListener implements OnClickListener {
        public void onClick(View v) {
            Animation animation = AnimationUtils.loadAnimation(
                    Animation_2.this, R.anim.scale);
            image.startAnimation(animation);
        }
    }

    class TranslateButtonListener implements OnClickListener {
        public void onClick(View v) {
            Animation animation = AnimationUtils.loadAnimation(Animation_2.this, R.anim.translate);
            image.startAnimation(animation);
        }
    }

    class DoubleButtonListener implements OnClickListener {
        public void onClick(View v) {
            // 使用AnimationUtils装载动画配置文件
            Animation animation = AnimationUtils.loadAnimation(
                    Animation_2.this, R.anim.double_anim);
            // 启动动画
            image.startAnimation(animation);
        }
    }
}
