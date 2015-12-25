package org.hellobox.view;

import org.hellobox.R;
import org.hellobox.application.Utils;

import android.content.Context;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.widget.TextView;

/**
 * 在背景中的小方块，继承自textview + isEx这个属性
 */
public class BackGroundSquare extends TextView
{
	private int isEx = 0;
	
	public int getIsEx()
	{
		return isEx;
	}

	public void setIsEx(int isEx)
	{
		this.isEx = isEx;
	}

	public BackGroundSquare(Context context)
	{
		super(context);
		setHeight(Utils.BACK_WIGHT);
		setWidth(Utils.BACK_WIGHT);
		setBackgroundResource(R.drawable.button_border);
	}
	
	public void addAnimationBack(int time)
	{
		AnimationSet animation = new AnimationSet(true);
		AlphaAnimation alphaAnimation = new AlphaAnimation(1f, 0.2f);
		alphaAnimation.setDuration(time);
		animation.addAnimation(alphaAnimation);
		startAnimation(animation);
		new Handler().postDelayed(new Runnable()
		{
			public void run()
			{
				setBackgroundResource(R.drawable.button_border);
			}
		}, time);
		setIsEx(Utils.DISFILL);
	}
}
