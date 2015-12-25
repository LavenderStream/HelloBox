package org.hellobox.view;

import org.hellobox.R;
import org.hellobox.application.Utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * 将BackGroundSquare10*10的画在背景上
 */
public class BackGroundView extends LinearLayout
{
	private LinearLayout mLinearLayout;
	private int num = Utils.MATRIXNUM;
	public static BackGroundSquare[][] myTextView;

	public BackGroundView(Context context, AttributeSet attributes)
	{
		super(context, attributes);
		setBackgroundResource(R.color.white);
		setOrientation(LinearLayout.VERTICAL);
		myTextView = new BackGroundSquare[num][num];

		for (int j = 0; j < num; j++)
		{
			this.mLinearLayout = new LinearLayout(context);
			mLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
			for (int i = 0; i < num; i++)
			{
				myTextView[j][i] = new BackGroundSquare(context);
				mLinearLayout.addView(myTextView[j][i]);
			}
			addView(mLinearLayout);
		}

	}
}
