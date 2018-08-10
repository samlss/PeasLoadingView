package com.iigo.library;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import static android.view.View.MeasureSpec.AT_MOST;

/**
 * @author SamLeung
 * @e-mail 729717222@qq.com
 * @github https://github.com/samlss
 * @csdn https://blog.csdn.net/Samlss
 * @description The peas loading view
 */
public class PeasLoadingView extends View {
    private final static String TAG = "PeasLoadingView";

    //豆豆画笔
    // (the peas paint)
    private Paint peasPaint;

    //豆豆数量
    // (the peas count)
    private int peasCount = 6;

    //豆豆大小，半径
    // (the peas size, radius)
    private float peasRadius = 12f;

    //根据宽高而定的初始半径
    // (Initial radius based on width and height)
    private float initialRadius;

    //当前旋转中心点X
    // (the current rotation center point x)
    private int centerX;

    //当前旋转中心点Y
    // (the current rotation center point y)
    private int centerY;

    //豆豆围绕的圆的半径
    // (the radius of the circle surrounded by peas)
    private float trackRadius;

    //动画插值器
    // (Animated interpolator)
    private TimeInterpolator interpolator = new AccelerateDecelerateInterpolator();

    //动画
    // (animation)
    private ObjectAnimator animator;

    private long animatorPlayTime;

    //默认颜色数组
    // (the default color array)
    private static final int[] DEFAULT_COLOR_ARRAY = new int[]{
            Color.parseColor("#FF9600"),
            Color.parseColor("#02D1AC"),
            Color.parseColor("#FDD835"),
            Color.parseColor("#00C6FF"),
            Color.parseColor("#00E099"),
            Color.parseColor("#0288D1"),
    };

    //当前使用的颜色数组
    // (the currently used color array)
    private int[] peasColors = DEFAULT_COLOR_ARRAY;

    public PeasLoadingView(Context context) {
        super(context);

        init();
    }

    public PeasLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);

        parseAttrs(attrs);
        init();
    }

    public PeasLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        parseAttrs(attrs);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PeasLoadingView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        parseAttrs(attrs);
        init();
    }

    private void parseAttrs(AttributeSet attrs){
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.PeasLoadingView);
        peasCount = typedArray.getInteger(R.styleable.PeasLoadingView_peas_count, 6);
        peasRadius = typedArray.getFloat(R.styleable.PeasLoadingView_peas_radius, 12f);
        typedArray.recycle();
    }


    private void init(){
        peasPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int modeWidth  = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        //没有指定宽高,使用了wrap_content,则手动指定宽高为MATCH_PARENT
        // (No width or height is specified, wrap_content is used, and the width and height are manually specified as MATCH_PARENT)
        if (modeWidth == AT_MOST && modeHeight == AT_MOST){
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
            setLayoutParams(layoutParams);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        centerX = w / 2;
        centerY = h / 2;

        initialRadius = w > h ? h / 2 : w / 2;

        if (peasRadius > (initialRadius / 5f)){
            peasRadius = initialRadius / 5f;
        }

        trackRadius =  initialRadius - peasRadius * 2;

        setupAnimator(interpolator);
    }

    private void setupAnimator(TimeInterpolator value){
        stop();

        animator = ObjectAnimator.ofFloat(this,"rotation", 0f, 360f);
        animator.setDuration(1200);
        animator.setInterpolator(value);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float rotationAngle = (float) (2 * Math.PI / peasCount);

        for (int i=0; i < peasCount; i++){
            double angle = i * rotationAngle ;
            float peasX = (float) (trackRadius * Math.cos(angle) + centerX);
            float peasY = (float) (trackRadius * Math.sin(angle) + centerY);

            peasPaint.setColor(peasColors[i % peasColors.length]);
            canvas.drawCircle(peasX, peasY, peasRadius, peasPaint);
        }
    }

    public void setPeasCount(int peasCount) {
        this.peasCount = peasCount;

        invalidate();
    }

    public void setPeasColors(int ... peasColors) {
        if (peasColors == null
                || peasColors.length == 0){
            Log.w(TAG,"Cannot pass an empty color array.");
            return;
        }

        this.peasColors = peasColors;
        invalidate();
    }

    /**
     * 设置插值
     * (Set the interpolator)
     * */
    public void setInterpolator(TimeInterpolator value){
        interpolator = value;
        setupAnimator(interpolator);
    }

    /**
     * 开始动画
     *
     * (start animation)
     * */
    public void start(){
        if (animator != null && !animator.isRunning()){
            animator.setCurrentPlayTime(animatorPlayTime);
            animator.start();
        }
    }

    /**
     * 结束动画
     *
     * (stop animation)
     * */
    public void stop(){
        if (animator != null && animator.isRunning()){
            animatorPlayTime = animator.getCurrentPlayTime();
            animator.cancel();
        }
    }
}
