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
 * ��ҵ����ҳ��
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
	 * ���д����¼����������GestureDetector��onTouchEvent����
	*/
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return this.detector.onTouchEvent(event);
	}
	/***
	 * ����ComplanIntroductionActivity
	*/
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewflipper1);
		//ע��һ��GestureDetector
		detector = new GestureDetector(this);
		flipper = (ViewFlipper)findViewById(R.id.flipper1);
	}
	/***
	 * ����ʼ����ʱ����֪ͨһ�������¼�
	 * �¼����¼������ƥ�䣬ͬʱ�����������ÿ��Ϊ��λ��XY�Ử�����ٶ�
	 * 
	 * @param e1       ��ʼ����ʱ��һ�������˶��¼�
	 * @param e2       �ƶ����� �����ĵ�ǰ��onFling
	 * @param velocityX   ��X��ÿ�뻬��������ֵ           
	 * @param velocityY   ��Y��ÿ�뻬��������ֵ
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
	 * ����ʱ�������¼�
	 * 
	 * @param e  ��ʼ�ĳ����¼���ʼ
	 *          
	 */
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * ֪ͨʱ������������ʼ����motionevent�͵�ǰmotionevent�������뻹�ṩ���㡣
	 * 
	 * @param e1 ��ʼ�����ĵ�һ�������¼�
	 * @param e2 �ƶ�����������ǰ���¼�
	 * @param distanceX ��ʼ���������ֹͣ��X�Ử��������ֵ
	 * @param distanceY ��ʼ���������ֹͣ��Y�Ử��������ֵ
	 */
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * �û�ִ����һmotionevent��û�н����ƶ���
	 * ���¼�ͨ�������ṩ�Ӿ��������û���������֪�����ǵ��ж��ѱ�ȷ��
	 */
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * �����󴥷����¼�
	 * 
	 * @param e ������������¼�
	 */
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * ֪ͨʱ������ķ�������motionevent�������⽫��������ÿ���¼��������������¼�֮ǰ��
	 * 
	 * @param e ���µ��¼�
	 */
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

}
