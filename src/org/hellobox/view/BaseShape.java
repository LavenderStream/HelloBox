package org.hellobox.view;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import org.hellobox.application.Utils;
import org.hellobox.utils.Tool;

/**
 * Created by Tiny on 5/6/2015.
 */
public class BaseShape extends View
{
    public interface onShowListener
    {
        void showListening(int i);
    }

    onShowListener mOnShowListener = null;

    public void setOnShowListening(onShowListener onShowListener)
    {
        mOnShowListener = onShowListener;
    }

    public BaseShape(Context context)
    {
        super(context);
    }

    public BaseShape(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public BaseShape(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    public void removeOneLine()
    {
        new Handler().postDelayed(new Runnable()
        {
            public void run()
            {
                Tool.checkLine();
            }
        }, Utils.ANIMATIONDELAY);
    }


}
