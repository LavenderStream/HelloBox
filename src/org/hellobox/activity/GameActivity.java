package org.hellobox.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import org.hellobox.R;
import org.hellobox.application.Utils;
import org.hellobox.view.BackGroundView;

import java.io.InputStream;

import master.flame.danmaku.controller.DrawHandler;
import master.flame.danmaku.controller.IDanmakuView;
import master.flame.danmaku.danmaku.loader.ILoader;
import master.flame.danmaku.danmaku.loader.IllegalDataException;
import master.flame.danmaku.danmaku.loader.android.DanmakuLoaderFactory;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.android.DanmakuGlobalConfig;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.danmaku.parser.IDataSource;
import master.flame.danmaku.danmaku.parser.android.BiliDanmukuParser;

public class GameActivity extends Activity
{
    private IDanmakuView mDanmakuView;
    private BaseDanmakuParser mParser;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        this.mDanmakuView = (IDanmakuView) findViewById(R.id.danmu_view);

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {

            this.mDanmakuView.removeAllDanmakus();
            removeBackGroundMatt();
            finish();
            overridePendingTransition(R.anim.jump_alpha, R.anim.jump_alpha_out);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    private BaseDanmakuParser createParser(InputStream stream) {

        if(stream==null){
            return new BaseDanmakuParser() {

                @Override
                protected Danmakus parse() {
                    return new Danmakus();
                }
            };
        }


        ILoader loader = DanmakuLoaderFactory.create(DanmakuLoaderFactory.TAG_BILI);

        try {
            loader.load(stream);
        } catch (IllegalDataException e) {
            e.printStackTrace();
        }
        BaseDanmakuParser parser = new BiliDanmukuParser();
        IDataSource<?> dataSource = loader.getDataSource();
        parser.load(dataSource);
        return parser;
    }


    private void removeBackGroundMatt()
    {
        for (int i = 0; i < Utils.MATRIXNUM;i++)
        {
            for (int j = 0; j < Utils.MATRIXNUM; j++)
            {
                BackGroundView.myTextView[i][j].setBackgroundResource(R.drawable.button_border);
                BackGroundView.myTextView[i][j].setIsEx(0);
            }
        }
    }

    @Override
    protected void onResume()
    {

        DanmakuGlobalConfig.DEFAULT.setDanmakuStyle(DanmakuGlobalConfig.DANMAKU_STYLE_STROKEN, 3).setDuplicateMergingEnabled(false)
        .setScrollSpeedFactor(1.65f);
        if (mDanmakuView != null) {
            mParser = createParser(this.getResources().openRawResource(R.raw.comments));
            mDanmakuView.setCallback(new DrawHandler.Callback() {

                @Override
                public void updateTimer(DanmakuTimer timer) {

                }

                @Override
                public void prepared() {
                    mDanmakuView.start();
                }
            });
            mDanmakuView.prepare(mParser);
        }

        super.onResume();
    }
}
