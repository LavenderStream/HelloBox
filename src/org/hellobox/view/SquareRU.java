package org.hellobox.view;


import org.hellobox.application.Utils;

import org.hellobox.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;

@SuppressLint({ "DrawAllocation", "ClickableViewAccessibility" })
public class SquareRU extends BaseShape
{
	private int wight  = Utils.WIGHT;
	public int currentx = Utils.INITCOOTX;
	public int currenty = Utils.INITCOOTY;
	public int oldCurrentx = Utils.INITCOOTX;
	public int oldCurrenty = Utils.INITCOOTY;
	private int color = Color.parseColor("#64c887");

	public interface onShowListener
	{
		void showListening(int i);
	}

	onShowListener mOnShowListener = null;

	public void setOnShowListening(onShowListener onShowListener)
	{
		mOnShowListener = onShowListener;
	}
	
	public SquareRU(Context context)
	{
		super(context);
	}
	
	public SquareRU(Context context, AttributeSet attributeSet)
	{
		super(context, attributeSet);
	}


	public SquareRU(Context context, int x , int y)
	{
		super(context);
		this.currentx = x;
		this.currenty = y;
		this.oldCurrentx = currentx;
		this.oldCurrenty = currenty;
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		// 创建画笔 ;
		Paint p = new Paint();
		p.setColor(color);
		// 绘制一个可以移动的正方向田字格
		RectF rect = new RectF(currentx, currenty, currentx + wight,
				currenty + wight);
		// 2,2
		RectF rect1 = new RectF(currentx + wight + Utils.SPACE, currenty
				+ wight + Utils.SPACE, currentx + wight
				+ Utils.SPACE + wight, currenty + wight
				+ Utils.SPACE + wight);
		// 2,1
		RectF rect3 = new RectF(currentx, currenty + wight + Utils.SPACE,
				currentx + wight, currenty + wight + Utils.SPACE
						+ wight);
		canvas.drawRoundRect(rect,8f,8f, p);
		canvas.drawRoundRect(rect1, 8f,8f,p);
		canvas.drawRoundRect(rect3, 8f,8f,p);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		boolean flag = super.onTouchEvent(event);
		switch (event.getAction())
		{
		// 这里仅仅是正方形的判断
		case MotionEvent.ACTION_UP:
			this.wight = Utils.WIGHT;
			// 得到手指在触屏上的点， 在加上移动图案的偏移量
			int coorX = (int) event.getX() + Utils.SCOPEX;
			int coorY = (int) event.getY() + Utils.SCOPEY;
			// 背景textview的位置寄存器
			int[] location = new int[2];

			 
			// 正方形不能放在最后
			for (int j = 0; j < Utils.MATRIXNUM - 1; j++)
			{
				for (int i = 0; i < Utils.MATRIXNUM - 1; i++)
				{
					BackGroundSquare b;
					b = BackGroundView.myTextView[j][i];
					b.getLocationOnScreen(location);
					int x = location[0];
					int y = location[1];

					 
					// 测量正方形左上角第一个方块在背景textview的什么位置
					if (Math.abs(coorX - x) <= Utils.ACCURACY
							&& Math.abs(coorY - y) <= Utils.ACCURACY)
					{
						 
						// 若符合的要求的精确度，随后确定第一个正方形他的右，下右下三个方形背景textview
						// 的isex属性是否被占用， 注意，b是背景的textview的对象
						if (b.getIsEx() == Utils.DISFILL
								&& BackGroundView.myTextView[j + 1][i].getIsEx() == Utils.DISFILL
								&& BackGroundView.myTextView[j + 1][i + 1].getIsEx() == Utils.DISFILL)
						{
							// 若可以放在这个区域，就将这个区域变化2颜色，并且将背景的isEx属性设为1，表示被占用
							b.setBackgroundResource(R.drawable.square_ru_done);
							BackGroundView.myTextView[j + 1][i]
									.setBackgroundResource(R.drawable.square_ru_done);
							BackGroundView.myTextView[j + 1][i + 1]
									.setBackgroundResource(R.drawable.square_ru_done);
							b.setIsEx(Utils.FILL);
							BackGroundView.myTextView[j + 1][i].setIsEx(Utils.FILL);
							BackGroundView.myTextView[j + 1][i + 1]
									.setIsEx(Utils.FILL);
							// 这里可以加上监听， 有图像被放置在背景上， 就像mainActivity发出一个命令
							// 表示可以将这个图像的显示部分隐藏View.gone
							if (mOnShowListener != null)
							{
								mOnShowListener.showListening(1);
							}
						}

						else
						{
							if (mOnShowListener != null)
							{
								mOnShowListener.showListening(0);
							}
						}

					}
				}
			}
			currentx = this.oldCurrentx;
			currenty = this.oldCurrenty;
			invalidate();
            flag = super.onTouchEvent(event);
			break;
		case MotionEvent.ACTION_MOVE:
			this.currentx = (int) event.getX() + Utils.SCOPEX;
			this.currenty = (int) event.getY() + Utils.SCOPEY;
			invalidate();
			flag = true;
			break;
			
		case MotionEvent.ACTION_DOWN:
			this.wight = Utils.DOWN_WIGHT;
			if (Math.abs(Utils.INITCOOTX - event.getX()) < Utils.ACCURACYSCOPE
					&& Math.abs(Utils.INITCOOTY - event.getY()) < Utils.ACCURACYSCOPE)
			{
				this.currentx = (int) event.getX() + Utils.SCOPEX;
				this.currenty = (int) event.getY() + Utils.SCOPEY;
				invalidate();
				flag = true;
			}
			break;
		}
		return flag;
	}
	
}
