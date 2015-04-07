package dd.surfit.test.applicationdemo.SlideMenu;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.Window;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;

import dd.surfit.test.applicationdemo.BaseActivity;
import dd.surfit.test.applicationdemo.R;

/**
 * Created by Administrator on 2015/3/30.
 * 覆盖式
 */
public class SlideMenu_1 extends BaseActivity implements OnTouchListener,
        GestureDetector.OnGestureListener {
    //设置宽度标识
    private boolean hasMeasured = false;// 是否Measured.
    private LinearLayout layout_left;
    private LinearLayout layout_right;
    private ImageView iv_set;
    private ListView lv_set;

    /**
     * 每次自动展开/收缩的范围
     */
    private int MAX_WIDTH = 0;
    /**
     * 每次自动展开/收缩的速度
     */
    private final static int SPEED = 60;

    private GestureDetector mGestureDetector;// 手势
    private boolean isScrolling = false;
    private float mScrollX; // 滑块滑动距离
    private int window_width;// 屏幕的宽度

    private final String TAG = "SlideMenu_1";

    private String title[] = {"待发送队列", "同步分享设置", "编辑我的资料", "找朋友", "告诉朋友",
            "节省流量", "推送设置", "版本更新", "意见反馈", "积分兑换", "精品应用", "常见问题", "退出当前帐号"};

    /**
     * 初始化view
     */
    void InitView() {
        layout_left = (LinearLayout) findViewById(R.id.layout_left);
        layout_right = (LinearLayout) findViewById(R.id.layout_right);
        iv_set = (ImageView) findViewById(R.id.iv_set);
        lv_set = (ListView) findViewById(R.id.lv_set);
        lv_set.setAdapter(new ArrayAdapter<String>(this, R.layout.item_adapter_root_list,
                R.id.list_name, title));
        lv_set.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(SlideMenu_1.this, title[position], Toast.LENGTH_SHORT).show();
            }
        });
        layout_left.setOnTouchListener(this);
        iv_set.setOnTouchListener(this);
        mGestureDetector = new GestureDetector(this);
        // 禁用长按监听
        mGestureDetector.setIsLongpressEnabled(false);
        getMAX_WIDTH();
    }

    /**
     * 获取移动距离 移动的距离其实就是layout_left的宽度
     */
    void getMAX_WIDTH() {
        ViewTreeObserver viewTreeObserver = layout_left.getViewTreeObserver();
        // 获取控件宽度
        viewTreeObserver.addOnPreDrawListener(new OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                if (!hasMeasured) {
                    window_width = getWindowManager().getDefaultDisplay()
                            .getWidth();
                    RelativeLayout.LayoutParams layoutParams1 = (RelativeLayout.LayoutParams) layout_left
                            .getLayoutParams();
                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(window_width,layout_left.getHeight());

                    // layoutParams.width = window_width;
                    layout_left.setLayoutParams(layoutParams);
                    MAX_WIDTH = layout_right.getWidth();
                    Log.v(TAG, "MAX_WIDTH=" + MAX_WIDTH + "width="
                            + window_width);
                    hasMeasured = true;
                }
                return true;
            }
        });

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_slide_menu_1);
        InitView();

    }

    // 返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode && event.getRepeatCount() == 0) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) layout_left
                    .getLayoutParams();
            if (layoutParams.leftMargin < 0) {
                new AsynMove().execute(SPEED);
                return false;
            }
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.d(TAG,"onTouch");
        // 松开的时候要判断，如果不到半屏幕位子则缩回去，
        if (MotionEvent.ACTION_UP == event.getAction() && isScrolling == true) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) layout_left
                    .getLayoutParams();
            // 缩回去
            if (layoutParams.leftMargin < -window_width / 2) {
                new AsynMove().execute(-SPEED);
            } else {
                new AsynMove().execute(SPEED);
            }
        }

        return mGestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        mScrollX = 0;
        isScrolling = false;
        // 将之改为true，不然事件不会向下传递.
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    /**
     * 点击松开执行 点击
     */
    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.d(TAG,"onSingleTapUp");
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) layout_left
                .getLayoutParams();
        // 左移动
        if (layoutParams.leftMargin >= 0) {
            new AsynMove().execute(-SPEED);
        } else {
            // 右移动
            new AsynMove().execute(SPEED);
        }

        return true;
    }

    /**
     * e1 是起点，e2是终点，如果distanceX=e1.x-e2.x>0说明向左滑动。反之亦如此.
     */
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
        isScrolling = true;
        mScrollX += distanceX;// distanceX:向左为正，右为负
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) layout_left
                .getLayoutParams();
        layoutParams.leftMargin -= mScrollX;
        if (layoutParams.leftMargin >= 0) {
            isScrolling = false;// 拖过头了不需要再执行AsynMove了
            layoutParams.leftMargin = 0;

        } else if (layoutParams.leftMargin <= -MAX_WIDTH) {
            // 拖过头了不需要再执行AsynMove了
            isScrolling = false;
            layoutParams.leftMargin = -MAX_WIDTH;
        }
        layout_left.setLayoutParams(layoutParams);
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {
        return false;
    }

    class AsynMove extends AsyncTask<Integer, Integer, Void> {

        @Override
        protected Void doInBackground(Integer... params) {
            int times = 0;
            if (MAX_WIDTH % Math.abs(params[0]) == 0)// 整除
                times = MAX_WIDTH / Math.abs(params[0]);
            else
                times = MAX_WIDTH / Math.abs(params[0]) + 1;// 有余数

            for (int i = 0; i < times; i++) {
                publishProgress(params[0]);
                try {
                    Thread.sleep(Math.abs(params[0]));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        /**
         * update UI
         */
        @Override
        protected void onProgressUpdate(Integer... values) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) layout_left
                    .getLayoutParams();
            // 右移动
            if (values[0] > 0) {
                layoutParams.leftMargin = Math.min(layoutParams.leftMargin
                        + values[0], 0);
                //Log.v(TAG, "移动右" + layoutParams.rightMargin);
            } else {
                // 左移动
                layoutParams.leftMargin = Math.max(layoutParams.leftMargin
                        + values[0], -MAX_WIDTH);
                //Log.v(TAG, "移动左" + layoutParams.rightMargin);
            }
            layout_left.setLayoutParams(layoutParams);

        }

    }

}

