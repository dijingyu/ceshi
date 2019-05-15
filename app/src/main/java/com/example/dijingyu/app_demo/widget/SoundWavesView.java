package com.example.dijingyu.app_demo.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.dijingyu.app_demo.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 语音通话的声波控件
 * Created by Mr.LongFace on 2017/9/16.
 */
public class SoundWavesView extends View {

    private int mMini; // 最短值
    private int mMax; // 最大值
    private int mLineWidth; // 每条声波的宽度
    private int mSoundNum = 5; // 声波的数量
    private int mSpac; // 每条声波的中点
    private int mWidth , mHeight; // 控件宽高
    private boolean isRun = false;

    private Paint mPaint;
    private RectF mRectF;
    private List<SoundLine> mSoundList = new ArrayList<>();
    private Handler mHandler = new Handler();
    private Runnable mInvalidateRun = new Runnable() {
        @Override
        public void run() {
            postInvalidate();
        }
    };

    public SoundWavesView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(getResources().getColor(R.color.c_fa6a13));
        mPaint.setStyle(Paint.Style.FILL);
        mRectF = new RectF();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (widthMeasureSpec > 0 && heightMeasureSpec > 0) {
            initParam();
        }
    }

    private void initParam() {
        mWidth = getWidth();
        mHeight = getHeight();
        mMini = (int) (mHeight * 0.3f);
        mMax = mHeight;
        initLines();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < mSoundNum; i++) {
            SoundLine sound = mSoundList.get(i);
            mRectF.left = sound.left;
            mRectF.right = sound.right;
            mRectF.top = sound.top;
            mRectF.bottom = sound.bottom;
            canvas.drawRoundRect(mRectF , mLineWidth / 2 , mLineWidth / 2 , mPaint);
        }
        if (isRun) {
            mHandler.postDelayed(mInvalidateRun, 10);
        }
    }

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (isRun) {
            if (visibility == VISIBLE) {
                if (mWidth == 0) {
                    initParam();
                }
                if (mSoundList != null && mSoundList.size() > 0) {
                    for (SoundLine soundLine : mSoundList) {
                        soundLine.start();
                    }
                }
            }else{
                if (mSoundList != null && mSoundList.size() > 0) {
                    for (SoundLine soundLine : mSoundList) {
                        soundLine.stop();
                    }
                }
            }
        }
    }

    public void start() {
        if (!isRun) {
            isRun = true;
            for (SoundLine sound : mSoundList) {
                sound.start();
            }
            postInvalidate();
        }
    }

    public void stop(){
        if (isRun) {
            isRun = false;
            for (SoundLine sound : mSoundList) {
                sound.stop();
            }
        }
    }

    private void initLines() {
        mLineWidth = (int) (mWidth / mSoundNum * 0.7f);
        mSpac = mWidth / (mSoundNum - 1);
        mSoundList.clear();
        chaos();
    }

    /**
     * 生成凌乱的
     */
    private void chaos() {
        for (int i = 0; i < mSoundNum; i++) {
            int left = i * mSpac - mLineWidth / 2;
            int right = i * mSpac + mLineWidth / 2;
            SoundLine s = new SoundLine(left , right , 0 , mHeight);
            s.setMode(SoundLine.SPEED_RAN);
            s.setBorder(mMini , mMax);
            mSoundList.add(s);
        }
    }

    /**
     * 生成波浪的
     */
    private void wave(){
        // TODO 防止UI抽风
    }

    /**
     * 生成有序的
     */
    private void order(){
        // TODO 防止UI抽风
    }

    /**
     * 语音音频波纹的单个音波属性
     * Created by Mr.LongFace on 2017/9/16.
     */
    public class SoundLine implements ValueAnimator.AnimatorUpdateListener{

        // 低 中 高 随机 4挡
        public static final int SPEED_LOW = 500;
        public static final int SPEED_MID = 200;
        public static final int SPEED_HEI = 0;
        public static final int SPEED_RAN = 0;

        private Random mRandom;
        private ValueAnimator mAnim;

        public int left , right , top , bottom;
        private int min , max;

        public SoundLine(int left , int right , int top , int bottom){
            this.left = left;
            this.right = right;
            this.top = top;
            this.bottom = bottom;
            mRandom = new Random();
            initAnim();
        }

        private void initAnim() {
            mAnim = ValueAnimator.ofFloat(0.0f , 1.0f);
            setMode(SPEED_MID);
            mAnim.setRepeatCount(-1);
            mAnim.setRepeatMode(ValueAnimator.REVERSE);
            mAnim.addUpdateListener(this);
        }

        public void setMode(int mode){
            if (mode == SPEED_RAN) {
                mode = mRandom.nextInt(400);
            }
            mAnim.setDuration(300 + mode);
        }

        public void start(){
            if (mAnim.isRunning()){
                mAnim.end();
            }
            mAnim.start();
        }

        @Override
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float f = (float) valueAnimator.getAnimatedValue();
            top = (int) (f * (max - min) / 2);
            bottom = max - top;
        }

        public void setBorder(int min, int max) {
            this.min = min;
            this.max = max;
        }

        public void stop() {
            mAnim.end();
            mAnim.cancel();
        }
    }

}
