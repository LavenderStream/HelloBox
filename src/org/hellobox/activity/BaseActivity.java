package org.hellobox.activity;

import org.hellobox.R;
import org.hellobox.application.Utils;
import org.hellobox.utils.Tool;
import org.hellobox.view.BaseShape;
import org.hellobox.view.BigMatts;
import org.hellobox.view.Linear;
import org.hellobox.view.LinearLong;
import org.hellobox.view.LinearShort;
import org.hellobox.view.Matts;
import org.hellobox.view.Plane;
import org.hellobox.view.PlaneLong;
import org.hellobox.view.PlaneShort;
import org.hellobox.view.Point;
import org.hellobox.view.SquareLU;
import org.hellobox.view.SquareRU;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**BaseActivity:用于按照随机数加载图形，并控制图形成功放置后
 * 加载下一个图形，检查每行每列是否放满，同时检查下一个图形是否能成功放置*/
public class BaseActivity extends Activity implements Point.onShowListener,
        Matts.onShowListener, Linear.onShowListener,
        LinearLong.onShowListener, SquareRU.onShowListener,
        SquareLU.onShowListener, Plane.onShowListener, PlaneLong.onShowListener,
        PlaneShort.onShowListener, LinearShort.onShowListener, BigMatts.onShowListener
{
    // 光线传感器的亮度等级
    private static final int LIGHTMODE_DRAK = 2;
    private static final int LIGHTMODE_LIGHT = 3;
    // 游戏结束的标记
    private static final int GAMESMODE_GAMEOVER = 1;
    // 可移动图形的布局
    private RelativeLayout r;
    // 用来存储可移动图形的map
    private SparseArray<BaseShape> mShapeSparse = new SparseArray<BaseShape>();
    // 加载各种图形
    private Matts draw;
    private Point point;
    private Linear linear;
    private LinearLong linearlong;
    private LinearShort linearShort;
    private SquareRU squareru;
    private SquareLU squarelu;
    private Plane plane;
    private PlaneLong planeLong;
    private PlaneShort planeShort;
    private BigMatts bigMatts;
    // 背景上的积分牌
    private TextView mBackTextView;
    // 背景view
    private View mMainView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // 设置背景格子
        setContentView(R.layout.button_but);
        this.mBackTextView = (TextView) findViewById(R.id.back_textView);
        this.mMainView = findViewById(R.id.main_layout);

        initShapeView();
        initList4ViewShape();
        randomShowView();
    }

    /**
     * 游戏中的handler，用于判断游戏运行状态和显示游戏运行信息
     */
    Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            switch (msg.arg1)
            {
                case LIGHTMODE_DRAK:
                    mMainView.setBackgroundColor(getResources().getColor(R.color.dark_light));
                break;
                case LIGHTMODE_LIGHT:
                    mMainView.setBackgroundColor((getResources().getColor(R.color.white)));
                    break;
                case GAMESMODE_GAMEOVER:
                    Intent mainIntent = new Intent(BaseActivity.this, GameActivity.class);
                    BaseActivity.this.startActivity(mainIntent);
                    overridePendingTransition(R.anim.jump_alpha, android.R.anim.fade_out);
                    break;
            }
            if (msg.arg2 != 0)
            {
                // 获得消去的行数或者列数，在背景中显示出来
                int nowTextViewNum = Integer.parseInt(String.valueOf(mBackTextView.getText()));

                int showNum = nowTextViewNum +
                        Integer.parseInt(String.valueOf(msg.arg2)) * 10;
                 mBackTextView.setText(showNum + "");
            }
        }
    };

    @Override
    protected void onResume()
    {
        super.onResume();
    }

    @Override
    public void showListening(int i)
    {
        //此时为成功放置了一个图形
        if (i == 1)
        {
            checkLine();
            // 随机显示一个图形
            randomShowView();
        }
    }

    /**
     * 随机的分配一个图形并且显示出来
     */
    private void randomShowView()
    {
        final int i = (int) (Math.random() * 11) + 1;
        // 检查之后的图形能不能放在背景上（在消去操作之后）
        checkWhole(i);
        controlViewshapeShow(i);
    }

    /**
     * 向主页面上添加所有的图形
     */
    private void initShapeView()
    {
        // 获取布局， 将一个可滑动的图形添加到这里，通过这里可控制图形的变化
        // 监听器可以监听可移动图像的数量0
        r = (RelativeLayout) findViewById(R.id.main_layout);
        draw = new Matts(this);
        r.addView(draw);
        draw.setOnShowListening(this);
        draw.setVisibility(View.GONE);

        point = new Point(this);
        r.addView(point);
        point.setOnShowListening(this);
        point.setVisibility(View.GONE);

        linear = new Linear(this);
        r.addView(linear);
        linear.setOnShowListening(this);
        linear.setVisibility(View.GONE);

        linearlong = new LinearLong(this);
        r.addView(linearlong);
        linearlong.setOnShowListening(this);
        linearlong.setVisibility(View.GONE);

        squareru = new SquareRU(this);
        r.addView(squareru);
        squareru.setOnShowListening(this);
        squareru.setVisibility(View.GONE);

        squarelu = new SquareLU(this);
        r.addView(squarelu);
        squarelu.setOnShowListening(this);
        squarelu.setVisibility(View.GONE);

        plane = new Plane(this);
        r.addView(plane);
        plane.setOnShowListening(this);
        plane.setVisibility(View.GONE);

        planeLong = new PlaneLong(this);
        r.addView(planeLong);
        planeLong.setOnShowListening(this);
        planeLong.setVisibility(View.GONE);

        planeShort = new PlaneShort(this);
        r.addView(planeShort);
        planeShort.setOnShowListening(this);
        planeShort.setVisibility(View.GONE);

        linearShort = new LinearShort(this);
        r.addView(linearShort);
        linearShort.setOnShowListening(this);
        linearShort.setVisibility(View.GONE);

        bigMatts = new BigMatts(this);
        r.addView(bigMatts);
        bigMatts.setOnShowListening(this);
        bigMatts.setVisibility(View.GONE);

    }

    /**
     * 检查待选的图形能不能放到背景的图案中去
     *
     * @param i 待选图形的索引
     */
    private void checkWhole(final int i)
    {
        //  检查能不能放上去
        new Handler().postDelayed(new Runnable()
        {
            public void run()
            {
                boolean f = Tool.checkWhole(i);
                if (!f)
                {
                    Message msg = handler.obtainMessage();
                    msg.arg1 = 1;
                    handler.sendMessage(msg);
                }
            }
        }, Utils.ANIMATIONDELAY);
    }

    /**
     * 检查一行或者一列是不是有排满的情况
     */
    private void checkLine()
    {
        // 看看有没有哪一行或者那一列排满的情况
        new Handler().postDelayed(new Runnable()
        {
            public void run()
            {
                int removeLineCount = Tool.checkLine();
                Message msg = handler.obtainMessage();
                msg.arg2 = removeLineCount;
                handler.sendMessage(msg);
            }
        }, Utils.ANIMATIONDELAY);
    }

    /**
     * 控制图形的显示，按照生成的随机数控制 图形的隐藏于显示
     * @param viewShapeSign
     */
    private void controlViewshapeShow(int viewShapeSign)
    {
        this.draw.setVisibility(View.GONE);
        this.point.setVisibility(View.GONE);
        this.linear.setVisibility(View.GONE);
        this.linearlong.setVisibility(View.GONE);
        this.squareru.setVisibility(View.GONE);
        this.squarelu.setVisibility(View.GONE);
        this.plane.setVisibility(View.GONE);
        this.planeLong.setVisibility(View.GONE);
        this.planeShort.setVisibility(View.GONE);
        this.linearShort.setVisibility(View.GONE);
        this.bigMatts.setVisibility(View.GONE);

        this.mShapeSparse.get(viewShapeSign).setVisibility(View.VISIBLE);
    }

    /**
     * 初始化Sparse list 存放生成的图形对象
     */
    private void initList4ViewShape()
    {
        this.mShapeSparse.put(Utils.Matts_Sign, this.draw);
        this.mShapeSparse.put(Utils.Point_Sign, this.point);
        this.mShapeSparse.put(Utils.Linear_Sign, this.linear);

        this.mShapeSparse.put(Utils.LinearLong_Sign, this.linearlong);
        this.mShapeSparse.put(Utils.SquareRU_Sign, this.squareru);
        this.mShapeSparse.put(Utils.SquareLU_Sign, this.squarelu);

        this.mShapeSparse.put(Utils.Plane_Sign, this.plane);
        this.mShapeSparse.put(Utils.PlaneLong, this.planeLong);
        this.mShapeSparse.put(Utils.PlaneShort_Sign, this.planeShort);
        this.mShapeSparse.put(Utils.LinearShort_Sign, this.linearShort);
        this.mShapeSparse.put(Utils.Big_Matts_Sign, this.bigMatts);

    }
}
