package dd.surfit.test.applicationdemo.SlideMenu;

import android.graphics.Color;
import android.os.Bundle;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ArgbEvaluator;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;

import java.sql.Time;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import dd.surfit.test.applicationdemo.BaseActivity;
import dd.surfit.test.applicationdemo.R;

/**
 * Created by Administrator on 2015/3/31.
 * QQ式 带动画
 */
public class SlideMenu_3  extends BaseActivity
{
    private String TAG = "SlideMenu_3";
    private SlideMenu_3HorizontalScrollView mMenu;
    private TextView tvName;
    private RelativeLayout rlHome;
    private RelativeLayout rlSport;
    private ImageView showView;
    private ImageView hideView;
    private ImageView tempView;
    int alpha = 100;
    int green = 256;
    int red = 0;
    int blue = 0;
    int color;
    int hour;
    int oldStatu = 0;
    int nowStatu = 0;
    private  static int[] images = {
        R.drawable.ic_slide_menu_3_bg_morning_720 , R.drawable.ic_slide_menu_3_bg_noon_720,
        R.drawable.ic_slide_menu_3_bg_dusk_720 , R.drawable.ic_slide_menu_3_bg_night_720,};

    private boolean isChange = false;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_slide_menu_3);
        mMenu = (SlideMenu_3HorizontalScrollView) findViewById(R.id.id_menu);
        tvName = (TextView) findViewById(R.id.tv_name);
        showView = (ImageView) findViewById(R.id.iv_show);
        hideView = (ImageView) findViewById(R.id.iv_hide);
        rlHome = (RelativeLayout) findViewById(R.id.rl_home);
        rlSport = (RelativeLayout) findViewById(R.id.rl_sport);


        rlHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"动画启动");
                ValueAnimator colorAnim = ObjectAnimator.ofInt(rlHome, "backgroundColor", /*Red*/0xFFFF8080, /*Blue*/0xFF8080FF);
                colorAnim.setDuration(3000);//持续时间
                colorAnim.setEvaluator(new ArgbEvaluator());
                colorAnim.setRepeatCount(ValueAnimator.INFINITE);//重复次数
                colorAnim.setRepeatMode(ValueAnimator.REVERSE);//重复模式
                colorAnim.start();
            }
        });
        rlSport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nowStatu = nowStatu + 1;
                if(nowStatu == 4){
                    nowStatu = 0;
                }
                changeBackground();
            }
        });

    }

    public void toggleMenu(View view)
    {
        mMenu.toggle();
    }

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                red = (int)(Math.random() * 256);
                green = (int)(Math.random() * 256);
                blue = (int)(Math.random() * 256);
                color = Color.argb(alpha,red,green,blue);
                tvName.setTextColor(color);
                //Log.d(TAG,String.valueOf(color));
                if(oldStatu != nowStatu && !isChange) {
                    changeBackground();
                }
            }
            super.handleMessage(msg);
        };
    };
    Timer timer ;
    TimerTask task ;
    TimerTask task_changeColor;

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    @Override
    protected void onResume() {
        changeTime();
       startTimer();
        super.onResume();
    }

    @Override
    protected void onPause() {
        stopTimer();
        super.onPause();
    }

    private void stopTimer(){
       if(timer != null){
           timer.cancel();
           timer = null;
       }
       if(task != null){
           task.cancel();
           task = null;
       }
       if(task_changeColor != null){
           task_changeColor.cancel();
           task_changeColor = null;
       }
    }
    private void startTimer(){
        if(timer == null) {
            timer = new Timer();
            if(task == null) {
                task = new TimerTask() {
                    @Override
                    public void run() {
                        // 需要做的事:发送消息
                        Message message = new Message();
                        message.what = 1;
                        handler.sendMessage(message);
                    }
                };
            }
            if(task_changeColor == null) {
                task_changeColor = new TimerTask() {
                    @Override
                    public void run() {
                        // 需要做的事:发送消息
                        changeTime();
                    }
                };
            }
            timer.schedule(task, 1000, 1000); // 1s后执行task,经过1s再次执行
            timer.schedule(task_changeColor, 60000, 60000);
        }
    }

    private void changeTime(){
        Calendar c = Calendar.getInstance();

        hour = c.get(Calendar.HOUR_OF_DAY);
        if (hour > 4 && hour <= 10) {
            nowStatu = 0;
        } else if (hour > 10 && hour <= 14) {
            nowStatu = 1;
        } else if (hour > 14 && hour <= 17) {
            nowStatu = 2;
        } else {
            nowStatu = 3;
        }
        Log.d(TAG, "当前时间" + hour + " " + nowStatu);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public void changeBackground(){
        if(isChange){
            return;
        }
        isChange = true;
        Log.d(TAG,"changeBackground" + oldStatu + " " + nowStatu);
        oldStatu = nowStatu;
        Log.d(TAG,"hideView" + hideView.getVisibility() + " " + showView.getVisibility());
        hideView.setImageDrawable(getResources().getDrawable(images[nowStatu]));
        changeanimator_2();
    }

    public void changeanimator() {
        ValueAnimator animator1 = ObjectAnimator.ofFloat(showView, "alpha", 1, 0);//淡出效果
        animator1.setDuration(1000);
        animator1.setInterpolator(new AccelerateInterpolator());
        ValueAnimator animator2 = ObjectAnimator.ofFloat(showView, "x", showView.getX(), (showView.getX() - showView.getWidth()));//向左移动效果
        animator2.setDuration(1000);
        animator2.setInterpolator(new DecelerateInterpolator());

//  animatorSet.start();

        ValueAnimator animator3 = ObjectAnimator.ofFloat(hideView, "alpha", 0, 1);//淡入效果
        animator3.setDuration(1000);
        animator3.setInterpolator(new AccelerateInterpolator());
        ValueAnimator animator4 = ObjectAnimator.ofFloat(hideView, "x", hideView.getX() + hideView.getWidth(), hideView.getX());//从右边向左移动
        animator4.setDuration(1000);
        animator4.setInterpolator(new DecelerateInterpolator());

        AnimatorSet animatorSet1 = new AnimatorSet();
        animatorSet1.play(animator3).with(animator4);

        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.play(animator2).with(animator1);

        AnimatorSet set = new AnimatorSet();
        //set.playSequentially(animatorSet2,animatorSet1);//使用playSequentially方法测试效果
        set.play(animatorSet2).after(animatorSet1);
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                showView.setVisibility(View.VISIBLE);
                hideView.setVisibility(View.VISIBLE);
                Log.d(TAG,"hideView" + hideView.getVisibility() + " " + showView.getVisibility());
            }

            @Override
            public void onAnimationEnd(Animator animator) {

                showView.setVisibility(View.GONE);
                hideView.setVisibility(View.VISIBLE);
                showView.setX(hideView.getX());
                Log.d(TAG,"hideView" + hideView.getVisibility() + " " + showView.getVisibility());
                tempView = showView;
                showView = hideView;
                hideView = tempView;
                Log.d(TAG,"hideView" + hideView.getVisibility() + " " + showView.getVisibility());
                Log.d(TAG,"showView" + showView.getX() + " " + hideView.getX());



            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        set.start();
    }
    public void changeanimator_2() {
        ValueAnimator animator1 = ObjectAnimator.ofFloat(showView, "alpha", 1, 0);//淡出效果
        animator1.setDuration(1000);
        /**
         * Interpolator定义了动画变化的速率，在Animations框架当中定义了一下几种Interpolator
         ?         AccelerateDecelerateInterpolator：在动画开始与结束的地方速率改变比较慢，在中间的时候速率快。
         ?         AccelerateInterpolator：在动画开始的地方速率改变比较慢，然后开始加速
         ?         CycleInterpolator：动画循环播放特定的次数，速率改变沿着正弦曲线
         ?         DecelerateInterpolator：在动画开始的地方速率改变比较慢，然后开始减速
         ?         LinearInterpolator：动画以均匀的速率改变
         */
        animator1.setInterpolator(new AccelerateInterpolator());
        ValueAnimator animator2 = ObjectAnimator.ofFloat(hideView, "alpha", 0, 1);//淡入效果
        animator2.setDuration(1000);
        animator2.setInterpolator(new AccelerateInterpolator());

        AnimatorSet set = new AnimatorSet();
        //set.playSequentially(animatorSet2,animatorSet1);//使用playSequentially方法测试效果
        set.play(animator1).after(animator2);
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                showView.setVisibility(View.VISIBLE);
                hideView.setVisibility(View.VISIBLE);
                Log.d(TAG,"hideView" + hideView.getVisibility() + " " + showView.getVisibility());
            }

            @Override
            public void onAnimationEnd(Animator animator) {

                showView.setVisibility(View.GONE);
                hideView.setVisibility(View.VISIBLE);
                Log.d(TAG,"hideView" + hideView.getVisibility() + " " + showView.getVisibility());
                tempView = showView;
                showView = hideView;
                hideView = tempView;
                Log.d(TAG,"hideView" + hideView.getVisibility() + " " + showView.getVisibility());
                Log.d(TAG,"showView" + showView.getX() + " " + hideView.getX());
                isChange = false;


            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        set.start();
    }
}