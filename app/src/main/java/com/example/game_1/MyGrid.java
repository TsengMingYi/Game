package com.example.game_1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

public class MyGrid extends View {

    private Paint paint = new Paint();

    public static final int NULL_TYPE = -1;
    public static final int CIRCLE_TYPE = 0;
    public static final int CROSS_TYPE = 1;
    private int currentType = NULL_TYPE;


    public MyGrid(Context context) {
        super(context);
        initial();
    }

    public MyGrid(Context context, AttributeSet attrs) {
        super(context, attrs);
        initial();
    }

    public MyGrid(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initial();
    }

    public int getCurrentType(){
        return currentType;
    }

    public void reset(){
        currentType = NULL_TYPE;
        invalidate();
    }

    public void setCurrentType(int type) {
        currentType = type;
        invalidate();
    }

    private void initial(){
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#0000ff"));
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            switch (currentType) {
                case CIRCLE_TYPE:
                    int radius = (int) (Math.min(getWidth(), getHeight()) / 2 * 0.8);
                    canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius, paint);
                    break;
                case CROSS_TYPE:
                    int dw = getWidth() / 4;
                    int dh = getHeight() / 4;
                    canvas.drawLine(dw, dh, getWidth() - dw, getHeight() - dh, paint);
                    canvas.drawLine(getWidth() - dw, dh, dw, getHeight() - dh, paint);
                    break;
                default:
                    break;
            }

    }
}
