package com.example.homeworkgame;

import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class TestSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private DrawThread drawThread;

    public TestSurfaceView (Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        drawThread = new DrawThread (getContext(), getHolder());
        drawThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        drawThread.requestStop();
        boolean retry = true;
        while (retry) {
            try {
                drawThread.join();
                retry = false;
            } catch (InterruptedException e) {
                //
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        drawThread.set(event.getX(), event.getY(), 50);
        return super.onTouchEvent(event);
    }
}

