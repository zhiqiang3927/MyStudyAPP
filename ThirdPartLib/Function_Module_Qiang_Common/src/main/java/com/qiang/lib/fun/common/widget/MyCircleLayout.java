package com.qiang.lib.fun.common.widget;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;

import com.qiang.lib.fun.common.R;


/**
 * <pre>
 *      Date            ： 2018/7/9 16:16
 *      Author          ： zhouzhiqiang
 *      orgURL          ： http://www.along.com.cn
 *      ModuleName      ：
 *      FunctionName    ：
 *      Deprecation     ： 圆形菜单
 * </pre>
 */
public class MyCircleLayout extends ViewGroup {


    //圆的半径
    private int radius;
    private int defaultSize = 250;
    //基础角度，后期的旋转都依靠这个
    private double baseAngle = 0;
    private int circleX, circleY;
    private Paint paint;
    private VelocityTracker velocityTracker;
    private float dX = 0, dY = 0, mX = 0, mY = 0, uX = 0, uY = 0;
    private float lastX = 0, lastY = 0;
    private boolean isFling = false;

    public MyCircleLayout(Context context) {
        this(context, null);
    }

    public MyCircleLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyCircleLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        paint = new Paint();
        paint.setColor(Color.parseColor("#000000"));
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        velocityTracker = VelocityTracker.obtain();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int measureWidth, measureHeight;
        if (widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY) {
            measureWidth = widthSize;
            measureHeight = heightSize;
        } else {
            measureHeight = dip2px(getContext(), defaultSize);
            measureWidth = dip2px(getContext(), defaultSize);

            if (widthMode == MeasureSpec.EXACTLY) {
                measureWidth = widthSize;
            }
            if (heightMode == MeasureSpec.EXACTLY) {
                measureHeight = dip2px(getContext(), defaultSize);
            }
        }

        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int size = Math.min(measureWidth, measureHeight);
        setRadius(size);
        setMeasuredDimension(size, size);
    }

    private void setRadius(int size) {
//        radius = (size / 3);
//        radius = (size / 3) - dip2px(getContext(), 10);
        radius = 200;
    }

    private int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int width = getMeasuredWidth(), height = getMeasuredHeight();
        int size = Math.min(width, height);
        circleX = size / 2;
        circleY = size / 2;
        int childCount = getChildCount();
        double angle = 2 * Math.PI / childCount;

        for (int i = 0; i < childCount; ++i) {
            int childX = 0, childY = 0;
            //将子控件平均分布
            View child = getChildAt(i);
            double tempAngle = i * angle + baseAngle;
            if (tempAngle < 0) {
//                tempAngle不能为负
                tempAngle += 10 * 2 * Math.PI;
            }
            double tempX = Math.sin(tempAngle) * radius;
            double tempY = Math.cos(tempAngle) * radius;
//            判断在哪个象限 //然后调整控件的位置
            double angleWhere = tempAngle % (2 * Math.PI);
            if (angleWhere < Math.PI / 2 && angleWhere >= 0) {
                //第一象限 x + , y -
                childX = (int) (circleX + Math.abs(tempX));
                childY = (int) (circleY - Math.abs(tempY));
            } else if (angleWhere >= Math.PI / 2 && angleWhere < Math.PI) {
                //第二象限 x + , y +
                childX = (int) (circleX + Math.abs(tempX));
                childY = (int) (circleY + Math.abs(tempY));
            } else if (angleWhere >= Math.PI && angleWhere < (Math.PI * 3 / 2)) {
                //第三象限 x - , y +
                childX = (int) (circleX - Math.abs(tempX));
                childY = (int) (circleY + Math.abs(tempY));
            } else if (angleWhere >= (Math.PI * 3 / 2) && angleWhere < (Math.PI * 2)) {
                //第四象限 x - , y -
                childX = (int) (circleX - Math.abs(tempX));
                childY = (int) (circleY - Math.abs(tempY));
            } else {
            }

            //细调控件位置，将位置指向控件中心
            childX -= child.getMeasuredWidth() / 2;
            childY -= child.getMeasuredHeight() / 2;
            child.layout(childX, childY, childX + child.getMeasuredWidth(), childY + child.getMeasuredHeight());
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawPath(canvas);

//        canvas.drawCircle(circleX, circleY, 15, paint);
        //虚线
        PathEffect effects = new DashPathEffect(new float[]{30,15},1);
        paint.setPathEffect(effects);
        paint.setARGB(90, Integer.parseInt("54",16), Integer.parseInt("b1",16), Integer.parseInt("9e",16));
//        paint.setColor(getResources().getColor(R.color.colorAccent));
        paint.setStrokeWidth(8);

        //中间圆
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.main_menu_center_image);
//        canvas.drawBitmap(bitmap, circleX - bitmap.getWidth() / 2, circleY - bitmap.getHeight() / 2, paint);
//        canvas.drawCircle(circleX, circleY, Math.min(getMeasuredHeight(), getMeasuredWidth()) / 2, paint);
        //大圈
        canvas.drawCircle(circleX, circleY, 300, paint);

    }

    private void drawPath(Canvas canvas) {

        int childCount = getChildCount();

        for (int i = 0; i < childCount; ++i) {
            View child = getChildAt(i);
//            int stopX = (int) (child.getLeft() + child.getMeasuredWidth() / 2);
//            int stopY = (int) (child.getTop() + child.getMeasuredHeight() / 2);
            int stopX = (int) (child.getLeft());
            int stopY = (int) (child.getTop());
//            int stopX = child.getLeft();
//            int stopY = child.getTop();
            //中间圆
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
            canvas.drawBitmap(bitmap, stopX - bitmap.getWidth() / 2, stopY - bitmap.getHeight() / 2, paint);

//            canvas.drawLine(circleX, circleY, stopX, stopY, paint);
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
//            Rect rect = new Rect();
//            rect.set(circleX, circleY, stopX, stopY);
//            canvas.drawBitmap(bitmap,stopX/2, stopY/2, paint);
//            canvas.drawBitmap(bitmap,rect, rect, paint);
        }
    }

    public void setBaseAngle(float angle) {
        this.baseAngle += angle;
        requestLayout();
    }

    public double getBaseAngle() {
        return baseAngle;
    }

    private double angle;
    private int quadrant;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        velocityTracker.addMovement(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                dX = event.getX();
                dY = event.getY();
                lastX = event.getX();
                lastY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                mX = event.getX();
                mY = event.getY();
                double start = getAngle(lastX, lastY);
                double end = getAngle(mX, mY);
                angle = end - start;
                quadrant = judgeQuadrant(mX, mY);
                if (quadrant == 1 || quadrant == 3) {
                    baseAngle -= angle;
                } else {
                    baseAngle += angle;
                }

                lastY = mY;
                lastX = mX;
                velocityTracker.computeCurrentVelocity(1);

                break;
            case MotionEvent.ACTION_UP:
                //在这里计算滑动速度
                final float speed = (float) Math.hypot(velocityTracker.getXVelocity(), velocityTracker.getYVelocity());
                break;

        }
        requestLayout();
        return true;
    }


    private FlingClass flingThread;

    class FlingClass implements Runnable {
        double angle;
        int quadrant;

        FlingClass(double angle, int quadrant) {
            this.angle = angle;
            this.quadrant = quadrant;
        }

        @Override
        public void run() {
            while (Math.abs(angle) > 0.2f && isFling) {
                Log.e("xhc", "angle " + angle);
                try {
                    Thread.sleep(5);
                } catch (Exception e) {

                }

                if (angle > 0) {
                    angle -= 0.15f;
                } else {
                    angle += 0.15f;
                }
                if (quadrant == 1 || quadrant == 3) {
                    angle *= -1;
                } /*else {
                    baseAngle += angle;
                }*/
                setBaseAngle((float) angle);
            }
        }
    }


    //判断在哪个象限
    private int judgeQuadrant(float x, float y) {
        x -= circleX;
        y -= circleY;
        if (y >= 0) {
            //是在数学坐标系的3，4象限范围 注意屏幕上的x，y不是数学上的x，y
            return x >= 0 ? 4 : 3;
        } else {
            return x >= 0 ? 1 : 2;
        }
    }

    private double getAngle(float x, float y) {
        x -= circleX;
        y -= circleY;
        y = Math.abs(y);
        x = Math.abs(x);
//        if(x == 0) return 0;
        return Math.atan(y / x);
    }


    private int getLineLengthCircle(int x, int y) {
        return (int) Math.sqrt(Math.pow((x - circleX), 2) + Math.pow((y - circleY), 2));
    }

    private int getLineLength(int x1, int y1, int x2, int y2) {

        return (int) Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
    }
}





















