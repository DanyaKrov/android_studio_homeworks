package com.example.a17homework;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class CircleView extends View {
    private final Paint paint = new Paint();
    public CircleView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(Color.BLUE);
        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
        paint.setColor(Color.RED);
        canvas.drawCircle((float)getWidth() / 2, (float)getHeight() / 2, 50, paint);
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
