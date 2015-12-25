package org.hellobox.application;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * 整个程序的application，用与存放游戏中一些固定的值
 */
public class Utils extends Application
{
    // 每个图形分配以个它代表的索引值
	public static final int Point_Sign = 1;
	public static final int Matts_Sign = 2;
	public static final int Linear_Sign = 3;
	public static final int LinearLong_Sign = 4;
	public static final int SquareRU_Sign = 5;
	public static final int SquareLU_Sign = 6;
	public static final int Plane_Sign = 7;
	public static final int PlaneLong = 8;
	public static final int PlaneShort_Sign = 9;
	public static final int LinearShort_Sign = 10;
    public static final int Big_Matts_Sign = 11;

    /*配置信息*/
    // 矩阵个数
    public static int MATRIXNUM = 10;
    // 精确程度
    public static int ACCURACY = 30;
    // 初始化位置
    public static int INITCOOTX = 330;
    public static int INITCOOTY = 1100;
    // x轴偏移量
    public static int SCOPEX = 0;
    // y轴偏移量
    public static int SCOPEY = -220;
    // 可移动方形的宽度
    public static int WIGHT = 40;
    // 点击之后放大的宽度
    public static int DOWN_WIGHT = 55;
    // 背景方形的宽度
    public static int BACK_WIGHT = 60;
    //可移动方形的间隙
    public static int SPACE = 2;
    // 已被填充
    public static int FILL = 1;
    // 违背填充
    public static int DISFILL = 0;
    public static int ANIMATIONDELAY = 100;
    // 点击放大的精确范围
    public static int ACCURACYSCOPE = 130;
	
	@Override	
	public void onCreate()
	{
		super.onCreate();
		DisplayMetrics dm = new DisplayMetrics();
		  WindowManager windowMgr = (WindowManager)getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
		  windowMgr.getDefaultDisplay().getMetrics(dm);
		  int width = dm.widthPixels;
		  int height = dm.heightPixels;
		  //Toast.makeText(getApplicationContext(), "手机屏幕分辨率："+width+"*"+height, Toast.LENGTH_LONG).show();

          if(width == 480)
          {
              init480();
          }

          if (width == 1080)
          {
              init1080();
          }

          if (width == 800)
          {
              init800();
          }


    }

    private void init1080()
    {
        ACCURACY = 28;
        INITCOOTX = 475;
        INITCOOTY = 1600;
        SCOPEX = -70;
        SCOPEY = -220;
        WIGHT = 60;
        DOWN_WIGHT = 75;
        BACK_WIGHT = 80;
        SPACE = 2;
        FILL = 1;
        DISFILL = 0;
        ANIMATIONDELAY = 200;
        ACCURACYSCOPE = 200;
    }

    private void init480()
    {
        BACK_WIGHT = 35;
        // 初始化位置
        INITCOOTX = 200;
        INITCOOTY = 600;
        WIGHT = 30;
        SPACE = 1;
        SCOPEY = -120;
        DOWN_WIGHT = 35;
    }

    private void init800()
    {
        INITCOOTX = 370;
    }

}
