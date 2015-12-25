package org.hellobox.activity;

import org.hellobox.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**程序的加载动画，延时执行，执行完毕之后跳转到主activity*/
public class SplashActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

	        new Handler().postDelayed(new Runnable()
	        {
	            @Override
	            public void run()
	            {
	                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
	                SplashActivity.this.startActivity(mainIntent);//跳转到MainActivity
	                SplashActivity.this.finish();//结束SplashActivity

	            }
	        }, 1800);//给postDelayed()方法传递延迟参数
	}
}
