package dd.surfit.test.applicationdemo.SlideMenu.example;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import dd.surfit.test.applicationdemo.R;


public class SlidingTitleBar extends BaseActivity {

	public SlidingTitleBar() {
		super(R.string.title_bar_slide);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// set the Above View
		setContentView(R.layout.content_frame);
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.content_frame, new SampleListFragment())
		.commit();
		
		setSlidingActionBarEnabled(true);
	}
	
}
