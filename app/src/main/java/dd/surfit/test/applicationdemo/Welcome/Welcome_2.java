package dd.surfit.test.applicationdemo.Welcome;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ImageView;

import dd.surfit.test.applicationdemo.R;

/**
 * Created by Administrator on 2015/4/1.
 */
public class Welcome_2 extends Activity {
    /** Viewpager对象 */
    private ViewPager viewPager;
    private ImageView imageView;
    /** 创建一个数组，用来存放每个页面要显示的View */
    private ArrayList<View> pageViews;
    /** 创建一个imageview类型的数组，用来表示导航小圆点 */
    private ImageView[] imageViews;
    /** 装显示图片的viewgroup */
    private ViewGroup viewPictures;
    /** 导航小圆点的viewgroup */
    private ViewGroup viewPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater inflater = getLayoutInflater();
        pageViews = new ArrayList<View>();
        pageViews.add(inflater.inflate(R.layout.viewpager_1_welcome_2, null));
        pageViews.add(inflater.inflate(R.layout.viewpager_2_welcome_2, null));
        pageViews.add(inflater.inflate(R.layout.viewpager_3_welcome_2, null));
        pageViews.add(inflater.inflate(R.layout.viewpager_4_welcome_2, null));
        pageViews.add(inflater.inflate(R.layout.viewpager_5_welcome_2, null));
        //pageViews.add(inflater.inflate(R.layout.viewpager_1_welcome_2, null));

        // 小圆点数组，大小是图片的个数
        imageViews = new ImageView[pageViews.size()];
        // 从指定的XML文件中加载视图
        viewPictures = (ViewGroup) inflater.inflate(R.layout.activity_welcome_2, null);

        viewPager = (ViewPager) viewPictures.findViewById(R.id.guidePagers);
        viewPoints = (ViewGroup) viewPictures.findViewById(R.id.viewPoints);

        // 添加小圆点导航的图片
        for (int i = 0; i < pageViews.size(); i++) {
            imageView = new ImageView(Welcome_2.this);
            imageView.setLayoutParams(new LayoutParams(20, 20));
            imageView.setPadding(5, 0, 5, 0);
            // 吧小圆点放进数组中
            imageViews[i] = imageView;
            // 默认选中的是第一张图片，此时第一个小圆点是选中状态，其他不是
            if (i == 0)
                imageViews[i].setImageDrawable(getResources().getDrawable(
                        R.drawable.ic_welcome_2_page_indicator_focused));
            else
                imageViews[i].setImageDrawable(getResources().getDrawable(
                        R.drawable.ic_welcome_2_page_indicator_unfocused));
            // 将imageviews添加到小圆点视图组
            viewPoints.addView(imageViews[i]);
        }

        setContentView(viewPictures);

        viewPager.setAdapter(new NavigationPageAdapter());
        // 为viewpager添加监听，当view发生变化时的响应
        viewPager.setOnPageChangeListener(new NavigationPageChangeListener());
    }

    // 导航图片view的适配器，必须要实现的是下面四个方法
    class NavigationPageAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return pageViews.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        // 初始化每个Item
        @Override
        public Object instantiateItem(View container, int position) {
            ((ViewPager) container).addView(pageViews.get(position));
            return pageViews.get(position);
        }

        // 销毁每个Item
        @Override
        public void destroyItem(View container, int position, Object object) {
            ((ViewPager) container).removeView(pageViews.get(position));
        }

    }

    // viewpager的监听器，主要是onPageSelected要实现
    class NavigationPageChangeListener implements OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int position) {
            // 循环主要是控制导航中每个小圆点的状态
            for (int i = 0; i < imageViews.length; i++) {
                // 当前view下设置小圆点为选中状态
                imageViews[i].setImageDrawable(getResources().getDrawable(
                        R.drawable.ic_welcome_2_page_indicator_focused));
                // 其余设置为飞选中状态
                if (position != i)
                    imageViews[i].setImageDrawable(getResources().getDrawable(
                            R.drawable.ic_welcome_2_page_indicator_unfocused));
            }
        }

    }

    // 开始按钮方法，开始按钮在XML文件中onClick属性设置；
    // 我试图把按钮在本activity中实例化并设置点击监听，但总是报错，使用这个方法后没有报错，原因没找到
    public void startbutton(View v) {
        Intent intent = new Intent(Welcome_2.this, Welcome_3.class);
        startActivity(intent);
        Welcome_2.this.finish();
    }

}
