package org.hellobox.activity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * 毕业设计小游戏， 这个游戏来源于一个叫1010!的小游戏，模仿它做的，并不是原创，
 * 承诺不向外发布， 并向1010团队所做的游戏表示致敬
 */
public class MainActivity extends BaseActivity implements SensorEventListener
{
    private SensorManager mSensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        initSensor();
    }


    @Override
    public void onSensorChanged(final SensorEvent event)
    {
        // 获得设备的光线传感器，当光线过暗的时候自动的改变背景的颜色
        if (Sensor.TYPE_LIGHT == event.sensor.getType())
        {
            if (event.values[0] <= 1.0f)
            {
                sendLightMessages(2);
            }
            if (event.values[0] >= 2.0f)
            {
               sendLightMessages(3);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {

    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        mSensorManager.unregisterListener(this);
    }

    private void initSensor()
    {
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void sendLightMessages(final int flag)
    {
        new Handler().postDelayed(new Runnable()
        {
            public void run()
            {
                Message msg = handler.obtainMessage();
                msg.arg1 = flag;
                handler.sendMessage(msg);
            }
        }, 0);
    }
}
