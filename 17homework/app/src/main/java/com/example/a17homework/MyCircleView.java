package com.example.a17homework;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class MyCircleView extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder holder;
    private final DrawThread drawThread;
    private MyCircle circle;

    public MyCircleView(Context context) {
        super(context);
        getHolder().addCallback(this);
        drawThread = new DrawThread();
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        holder = surfaceHolder;
        circle = new MyCircle(getWidth() / 2, getHeight() / 2);
        drawThread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        circle.setDest(event.getX(), event.getY());
        return super.onTouchEvent(event);
    }

    class DrawThread extends Thread{
        private volatile boolean running = true;
        Canvas canvas;
        @Override
        public void run() {
            while (running){
                try {
                    canvas = holder.lockCanvas();
                    canvas.drawColor(Color.rgb(75, 190, 255));
                    circle.draw(canvas);
                    sleep(100);
                    circle.update();
                } catch (Exception e){
                }
                finally {
                    holder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }


    class MyCircle {
        private float x, y, radius, dest_x, dest_y, speed, speed_x, speed_y;
        private int direction_x, direction_y, count;
        Paint paint;
        public MyCircle(float x, float y){
            this.x = x;
            dest_x = x;
            dest_y = y;
            this.y = y;
            radius = 50;
            speed = 20;
            paint = new Paint();
            paint.setColor(Color.RED);
        }

        public void setDest(float dest_x, float dest_y) {
            this.dest_x = dest_x;
            this.dest_y = dest_y;
            direction_x =  dest_x >= x ? 1 : -1;
            direction_y =  dest_y >= y ? 1 : -1;
            speed_x = (float) (((dest_x - x) * direction_x) / (Math.sqrt((dest_x - x) * direction_x * (dest_x - x) *
                    direction_x + (dest_y - y) * direction_y * (dest_y - y) * direction_y) / speed));
            speed_y = (float) (((dest_y - y) * direction_y) / (Math.sqrt((dest_x - x) * direction_x * (dest_x - x) *
                    direction_x + (dest_y - y) * direction_y * (dest_y - y) * direction_y) / speed));
            count = (int) (((dest_x - x) * direction_x) / speed_x);
        }

        void draw(Canvas canvas){
            canvas.drawCircle(x, y, radius, paint);
        }
        void update(){
            if (count != 0){
                x += speed_x * direction_x;
                y += speed_y * direction_y;
                count--;
            }
        }
    }
}
