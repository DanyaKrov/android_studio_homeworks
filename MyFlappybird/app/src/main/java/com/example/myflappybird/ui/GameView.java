package com.example.myflappybird.ui;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.myflappybird.R;
import com.example.myflappybird.interface_elements.RestartButton;
import com.example.myflappybird.interface_elements.ScoreText;
import com.example.myflappybird.model.Bird;
import com.example.myflappybird.model.Pipe;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private static final int FPS = 50;
    private final Bitmap background;
    private SurfaceHolder surfaceHolder;
    private DrawThread drawThread;
    private Bird bird;
    private Pipe pipe;
    private ScoreText score;
    private Paint paint;
    private RestartButton restartButton;


    public GameView(Context context) {
        super(context);
        background = BitmapFactory.decodeResource(context.getResources(), R.drawable.back);
        drawThread = new DrawThread();
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(100);
        getHolder().addCallback(this);

    }

    private void init() {
        bird = new Bird(getContext(), 200, getHeight() / 2f);
        pipe = new Pipe(getContext(), getHeight(), getWidth());
        score = new ScoreText(getWidth() - 100, 100);
        restartButton = new RestartButton(getContext(), getWidth() / 2f, getHeight() / 2f, 200, 200);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
        init();
        drawThread.start();
    }

    private void drawFrames(Canvas canvas) {
        Rect backgroundRect = new Rect(0, 0, getWidth(), getHeight());
        canvas.drawBitmap(background, null, backgroundRect, null);
        canvas.drawBitmap(bird.getSprite(), bird.x, bird.y, null);
        canvas.drawBitmap(pipe.getTopPipe(), pipe.x, 0, null);
        canvas.drawBitmap(pipe.getBottomPipe(), pipe.x, getHeight() - pipe.getBottomPipe().getHeight(), null);
        canvas.drawText(Integer.toString(score.getScoreCount()), score.x, score.y, paint);
    }
    private void drawRestartButton(Canvas canvas){
        canvas.drawBitmap(restartButton.getButton_surface(), restartButton.x, restartButton.y, null);
    }

    private void update() {
        bird.update();
        pipe.update();
        if (pipe.isCollision(bird) || bird.y <= 0 || bird.y >= getHeight()) {
            drawThread.running = false;
            score.setDefault();
        }
        if (pipe.isPassed(bird)){
            score.changeText();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        bird.fly();
        if (!drawThread.running) {
            if (restartButton.isClicked(event.getX(), event.getY())){
                drawThread = new DrawThread();
                init();
                drawThread.start();
            }
        }
        return super.onTouchEvent(event);
    }

    private class DrawThread extends Thread {
        private volatile boolean running = true;

        @Override
        public void run() {
            Canvas canvas;
            while (running) {
                canvas = surfaceHolder.lockCanvas();
                try {
                    sleep(1000 / FPS);
                    drawFrames(canvas);
                    update();
                } catch (Exception e) {
                    Log.e("RRR", "run: ", e);
                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
            canvas = surfaceHolder.lockCanvas();
            drawRestartButton(canvas);
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }


    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
    }
}