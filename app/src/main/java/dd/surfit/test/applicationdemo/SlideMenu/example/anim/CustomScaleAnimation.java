package dd.surfit.test.applicationdemo.SlideMenu.example.anim;

import android.graphics.Canvas;

import dd.surfit.test.applicationdemo.R;
import dd.surfit.test.applicationdemo.SlideMenu.example.lib.SlidingMenu;


public class CustomScaleAnimation extends CustomAnimation {

	public CustomScaleAnimation() {
		super(R.string.anim_scale, new SlidingMenu.CanvasTransformer() {
			@Override
			public void transformCanvas(Canvas canvas, float percentOpen) {
				canvas.scale(percentOpen, 1, 0, 0);
			}			
		});
	}

}
