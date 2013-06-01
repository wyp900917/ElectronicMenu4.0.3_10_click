package com.EleMenu403_click;

import com.EleMenu403_click.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;
/**
 * 企业介绍页面
 * 
 * @author ping
 *
 */
public class ComplanIntroductionActivity extends Activity implements OnGestureListener {
	private static final int FLING_MIN_DISTANCE = 100;
	private static final int FINAL_MIN_VELOCITY = 100;
	private ViewFlipper flipper;
	private GestureDetector detector;
	/***
	 * 当有触屏事件发生会调用GestureDetector的onTouchEvent方法
	*/
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return this.detector.onTouchEvent(event);
	}
	/***
	 * 创建ComplanIntroductionActivity
	*/
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewflipper1);
		//注册一个GestureDetector
		detector = new GestureDetector(this);
		flipper = (ViewFlipper)findViewById(R.id.flipper1);
	}
	/***
	 * 当开始触屏时，会通知一个滑动事件
	 * 事件和事件会进行匹配，同时会计算以像素每秒为单位沿XY轴滑动的速度
	 * 
	 * @param e1       开始滑动时第一个向下运动事件
	 * @param e2       移动动作 引发的当前的onFling
	 * @param velocityX   沿X轴每秒滑过的像素值           
	 * @param velocityY   沿Y轴每秒滑过的像素值
	 */
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		if(e1.getX() - e2.getX() > FLING_MIN_DISTANCE && Math.abs(velocityX) > FINAL_MIN_VELOCITY) {
			this.flipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.in_right_left));
			this.flipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.out_right_left));
			Intent intent = getIntent();
			intent.setClass(ComplanIntroductionActivity.this, MainActivity.class);
			startActivity(intent);
			return true;
		}
		return false;
	}

	/**
	 * 长按时触发的事件
	 * 
	 * @param e  初始的长按事件开始
	 *          
	 */
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * 通知时，滚动发生初始向下motionevent和当前motionevent。距离与还提供方便。
	 * 
	 * @param e1 开始滚动的第一个触发事件
	 * @param e2 移动动作触发当前的事件
	 * @param distanceX 开始滚动到最后停止沿X轴滑过的像素值
	 * @param distanceY 开始滚动到最后停止沿Y轴滑过的像素值
	 */
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * 用户执行了一motionevent并没有进行移动。
	 * 此事件通常用于提供视觉反馈给用户，让他们知道他们的行动已被确认
	 */
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * 单击后触发的事件
	 * 
	 * @param e 单击后提起的事件
	 */
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * 通知时，点击的发生与下motionevent触发。这将立即触发每个事件。在所有其他事件之前。
	 * 
	 * @param e 按下的事件
	 */
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

}
